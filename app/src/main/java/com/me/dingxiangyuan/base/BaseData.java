package com.me.dingxiangyuan.base;

import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.MD5Encoder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * author by LiKe on 2016/12/27.
 */

public abstract class BaseData {

    private final File fileDir;
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;

    public BaseData() {
        //获取缓存路径
        File cacheDir = CommonUtils.getContext().getCacheDir();
        fileDir = new File(cacheDir, "dingxiangyuan");
        //判断该文件夹是否存在，如果不存在则创建
        if (!fileDir.exists()) {
            //创建文件夹
            fileDir.mkdirs();
        }
    }

    /**
     * @param path      请求地址
     * @param args      请求参数
     * @param index     页码索引
     * @param validTime 有效时间
     */
    public void getData(String path, String args, int index, int validTime) {
        //首先判断有效时间
        if (validTime == 0) {
            //直接请求网络，获取最新数据
            getDataFromNet(path, args, index, validTime);
        } else {
            //首先从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (!data.isEmpty()) {
                setResultData(data);
            } else {
                getDataFromNet(path, args, index, validTime);
            }
        }
    }

    private String getDataFromLocal(String path, int index, int validTime) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            //创建流读取数据
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            //想将第一行时间读取出来
            String s = bufferedReader.readLine();
            long time = Long.parseLong(s);
            if (System.currentTimeMillis() - time < validTime) {
                //将数据读取出来
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }else {
                //当前数据已经过期
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从网络获取数据
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     */
    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String data = response.body().string();
                //设置数据
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        setResultData(data);
                    }
                });

                //将数据写入本地中
                writeDataToLocal(path, args, index, validTime, data);
            }
        });
    }

    /**
     * 写入本地中
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     * @param data
     */
    private void writeDataToLocal(String path, String args, int index, int validTime, String data) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            //创建流将文件写入
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            //将当前的时间写入
            bufferedWriter.write(System.currentTimeMillis() + validTime + "\r\n");
            //在将数据写入
            bufferedWriter.write(data);
            //关闭流
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void setResultData(String data);
}
