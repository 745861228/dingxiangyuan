package com.me.dingxiangyuan.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhy.autolayout.config.AutoLayoutConifg;


/**
 * author by LiKe on 2016/12/27.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static RequestQueue requestQueue;


    /**
     * 设置默认为非登陆状态
     */
    public static boolean isLand = true;


    @Override
    public void onCreate() {
        super.onCreate();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程号
        mainThreadId = Process.myTid();

        //设置autoLayout
        AutoLayoutConifg.getInstance().useDeviceSize();

    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 创建RequestQueue对象
     */
    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getContext());
        }
        return requestQueue;
    }

}
