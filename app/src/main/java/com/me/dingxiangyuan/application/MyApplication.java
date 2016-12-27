package com.me.dingxiangyuan.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;


/**
 * author by LiKe on 2016/12/27.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static int mainThreadId1;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
       //获取主线程号
        mainThreadId1 = Process.myTid();

    }

    public static int getMainThreadId(){
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread(){
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
}
