package com.me.dingxiangyuan.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.interfaces.OnMediaPlayerListener;
import com.me.dingxiangyuan.utils.LogUtils;

import java.net.URLEncoder;

public class MyMediaPlayService extends Service {

    private static OnMediaPlayerListener onMediaPlayerListener;
    private ImageView frameAnimation_img;
    private MediaPlayer mediaPlayer;
    private AnimationDrawable animationDrawable;
    private boolean isPlaying = false;

    public MyMediaPlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    /**
     * 开始播放
     *
     * @param path
     */
    public void play(String path) {
        try {
            //设置播放资源
            mediaPlayer.setDataSource(path);
            ////预加载音乐
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始播放
        mediaPlayer.start();
        isPlaying = true;
        frameAnimation_img.setVisibility(View.VISIBLE);
        //开启帧动画
        frameAnimation_img.setImageResource(R.drawable.mediaplay_animation);
        animationDrawable = (AnimationDrawable) frameAnimation_img.getDrawable();
        animationDrawable.start();
        //获取音乐总时长
        final int duration = mediaPlayer.getDuration();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (onMediaPlayerListener!=null){
                        onMediaPlayerListener.setDuration(duration);
                        onMediaPlayerListener.setCurrentPosition(mediaPlayer.getCurrentPosition());
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!mediaPlayer.isPlaying()){
                        break;
                    }
                }

            }
        }.start();
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            animationDrawable.stop();
            frameAnimation_img.setVisibility(View.GONE);
        }
    }

    /**
     * 拖动seekBar设置mediaplayer播放进度
     */
    public void setMediaPlayer(int  mediaPlayerPosition){
        mediaPlayer.seekTo(mediaPlayerPosition);
    }


    /**
     * 创建中间人
     */
    public class MyBinder extends Binder {
        private String musicTitle;

        /**
         * 播放
         *
         * @param path
         */
        public void helpPlay(String path) {
            play(path);
        }

        /**
         * 暂停音乐
         */
        public void helpPause() {
            pause();
        }

        /**
         * 传递帧动画
         */
        public void setImageViewAnimation(ImageView frameAnimation_img) {
            MyMediaPlayService.this.frameAnimation_img = frameAnimation_img;
        }

        /**
         * 判断是否正在播放
         */
        public boolean getIsPlaying() {
            return isPlaying;
        }

        //设置接口回调
        public void setMediaPlayerListener(OnMediaPlayerListener onMediaPlayerListener) {
            MyMediaPlayService.onMediaPlayerListener = onMediaPlayerListener;
        }

        public void setMediaPlayerPosition(int mediaPlayerPosition) {
            setMediaPlayer(mediaPlayerPosition);
        }

        public void setMusicTitle(String musicTitle) {
            this.musicTitle = musicTitle;
        }

        public String getMusicTitle(){
            return this.musicTitle;
        }
    }

}
