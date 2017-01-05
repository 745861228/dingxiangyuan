package com.me.dingxiangyuan.acitvity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.bean.CarouselfigureBean;
import com.me.dingxiangyuan.interfaces.OnMediaPlayerListener;
import com.me.dingxiangyuan.service.MyMediaPlayService;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;
import com.zhy.autolayout.utils.L;

import java.text.SimpleDateFormat;

import static android.R.attr.duration;
import static android.R.attr.format;
import static android.content.Context.BIND_AUTO_CREATE;

public class MediaPlayActivity extends BaseActivity implements View.OnClickListener, OnMediaPlayerListener {

    private ImageView frameAnimation_img;
    private ImageView mediaplay_img;
    private ImageView playclick3x;
    private ImageView button_pause;
    private ImageView button_play;
    private TextView current_position_tv;
    private SeekBar mediaPlay_seekBar;
    private TextView duration_tv;
    private MyMediaPlayService.MyBinder myBinder;
    public static CarouselfigureBean.DataBean dataBean;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取binder对象
            myBinder = (MyMediaPlayService.MyBinder) service;
            setDataResource();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);
        initView();     //初始化控件
        initViewListener();
        //获取数据
        dataBean = (CarouselfigureBean.DataBean) getIntent().getSerializableExtra("dataBean");

        //开启服播放音乐
        Intent intent = new Intent(MediaPlayActivity.this, MyMediaPlayService.class);
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    /**
     * 控件监听事件
     */
    private void initViewListener() {
        playclick3x.setOnClickListener(this);       //中间点击暂停
        button_pause.setOnClickListener(this);      //暂停
        button_play.setOnClickListener(this);       //左下角的播放按钮

    }

    /**
     * 进来页面直接加载资源
     */
    private void setDataResource() {
        Glide.with(MediaPlayActivity.this).load(dataBean.img).into(mediaplay_img);
        mediaplay_img.setScaleType(ImageView.ScaleType.FIT_XY);
        myBinder.setImageViewAnimation(frameAnimation_img);
        myBinder.helpPlay(dataBean.url);
        myBinder.setMediaPlayerListener(MediaPlayActivity.this);

        if (myBinder.getIsPlaying()) {
            playclick3x.setAlpha(0);
            button_play.setVisibility(View.VISIBLE);
            button_pause.setVisibility(View.GONE);
            myBinder.setMusicTitle(dataBean.title);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frameAnimation_img = (ImageView) findViewById(R.id.frameAnimation_img);          //帧动画
        mediaplay_img = (ImageView) findViewById(R.id.mediaplay_img);                   //大图，背景图
        playclick3x = (ImageView) findViewById(R.id.playclick3x);                       //中间播放图片id
        button_pause = (ImageView) findViewById(R.id.button_pause);                     //暂停按钮监听
        button_play = (ImageView) findViewById(R.id.button_play);                       //开始播放按钮监听
        current_position_tv = (TextView) findViewById(R.id.current_position_tv);        //当前播放进度控件
        mediaPlay_seekBar = (SeekBar) findViewById(R.id.mediaPlay_seekBar);             //进度条
        duration_tv = (TextView) findViewById(R.id.duration_tv);                        //歌曲总时长

        //设置seekBar监听事件
        mediaPlay_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    myBinder.setMediaPlayerPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //中间暂停按钮
            case R.id.playclick3x:
                //button_pause  左下角的暂停按钮
            case R.id.button_pause:
                //button_play  左下角的播放按钮
            case R.id.button_play:
                setViewVisibility();
                break;
        }
    }

    private void setViewVisibility() {
        if (myBinder.getIsPlaying()) {
            myBinder.helpPause();
            playclick3x.setAlpha(200);
            button_pause.setVisibility(View.VISIBLE);
            button_play.setVisibility(View.GONE);
        } else {
            myBinder.helpPlay(dataBean.url);
            playclick3x.setAlpha(0);
            button_pause.setVisibility(View.GONE);
            button_play.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
    }

    /**
     * 设置进度条总时长
     *
     * @param duration+
     */
    @Override
    public void setDuration(final int duration) {
        mediaPlay_seekBar.setMax(duration);
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                duration_tv.setText(format.format(duration));
            }
        });
    }

    /**
     * 设置当前播放时长
     *
     * @param currentPosition
     */
    @Override
    public void setCurrentPosition(int currentPosition) {
        mediaPlay_seekBar.setProgress(currentPosition);
        MediaPlayActivity.this.currentPosition = currentPosition;
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                current_position_tv.setText(format.format(MediaPlayActivity.this.currentPosition));
            }
        });
    }


}
