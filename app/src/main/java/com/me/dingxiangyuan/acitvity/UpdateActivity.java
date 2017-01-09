package com.me.dingxiangyuan.acitvity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.view.Conf;
import com.me.dingxiangyuan.view.UpdateEntity;
import com.me.dingxiangyuan.view.UpdateTools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateActivity extends BaseActivity {
    private AlertDialog dialog;

    private UpdateEntity updateEntity;

    private TextView tv_name;
    /**
     * 消息机制
     */
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    dialog.setMessage(updateEntity.getDescription());
                    dialog.show();
                    break;

                case 1:
                    downLoadApk();
                    break;
            }


        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dialog = new AlertDialog.Builder(UpdateActivity.this).
                setTitle("升级提醒").
                setIcon(R.drawable.ic_launcher).
                setPositiveButton("在线升级", onclick).
                setNegativeButton("不想升级", null).
                create();



        //开启线程
        new Thread(new CheckVersionTask()).start();
    }
    DialogInterface.OnClickListener  onclick = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            handler.sendEmptyMessage(1);
        }

    };

    /*
     * 从服务器中下载APK
     */
    protected void downLoadApk() {
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file =  getFileFromServer(updateEntity.getUrl(), pd);
                    sleep(3000);

                    UpdateTools tools = new UpdateTools();
                    //安装APk
                    tools.installApk(file,UpdateActivity.this);
                    pd.dismiss(); //结束掉进度条对话框

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }}.start();
    }

    /*
     * 从服务器获取xml解析并进行比对版本号
     */
    public class CheckVersionTask implements Runnable{

        public void run() {
            try {
                //从资源文件获取服务器 地址
                String path = getResources().getString(R.string.serverurl);
                //包装成url的对象
                URL url = new URL(path);
                HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                InputStream is =conn.getInputStream();
                updateEntity =  UpdateTools.getUpdataInfo(is);

                int versionCode = UpdateActivity.this.getPackageManager().getPackageInfo(UpdateActivity.this.getPackageName(), 0).versionCode;

                if(Integer.parseInt(updateEntity.getVersion()) <= versionCode){
                    Log.i("xxx","版本号相同无需升级");
                }else{
                    Log.i("xxxx","版本号不同 ,提示用户升级 ");
                    handler.sendEmptyMessage(0);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载方法
     *
     * @param path
     * @param pd
     * @return
     * @throws Exception
     */
    public File getFileFromServer(String path, ProgressDialog pd)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(),
                    "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    @Override
    protected void onPause() {
        Log.w(Conf.TAG, "Activity1.onPause()");
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.w(Conf.TAG, "Activity1.onResume()");
        // TODO Auto-generated method stub
        super.onResume();

    }


}
