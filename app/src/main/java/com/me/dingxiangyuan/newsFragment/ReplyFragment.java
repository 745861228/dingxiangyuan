package com.me.dingxiangyuan.newsFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;

/**
 * Created by Administrator on 2017/1/4.
 */

public class ReplyFragment extends Fragment {
    private SwipeRefreshLayout sw;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //刷新
                case 0:
                    //停止刷新
                    sw.setRefreshing(false);
                    //展示数据
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=View.inflate(getActivity(), R.layout.reply_layout,null);
         sw = (SwipeRefreshLayout) view.findViewById(R.id.sw);
         return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置进度的颜色
        sw.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //设置是否刷新---直接一进来就刷新
        sw.post(new Runnable() {
            @Override
            public void run() {
                //一上来先去做刷新的逻辑操作
                sw.setRefreshing(true);
                //请求数据
                refreshData();
            }


        });
    }
    private void refreshData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //把数据发送给主线程
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
}
