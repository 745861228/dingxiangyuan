package com.me.dingxiangyuan.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;


/**
 * author by LiKe on 2016/12/27.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static RequestQueue requestQueue;




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


        //初始化xUTILS
        x.Ext.init(this);
        x.Ext.setDebug(true);
        // 集成QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        UMShareAPI.get(this);
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
