package com.me.dingxiangyuan.acitvity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.VersionInfo;
import com.me.dingxiangyuan.utils.DataClearManager;
import com.me.dingxiangyuan.view.SelfDialog;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;


public class SettingActivity extends BaseActivity {
    private ImageView img_return;
    private RelativeLayout clearcache;
    private RelativeLayout update_gx;
    private TextView set_clear;
    private SelfDialog selfDialog;
    private RelativeLayout about;
    private RelativeLayout up_password;
    private RelativeLayout invitation;
    private String urlPath = "http://169.254.239.3:8080/versioninfo.txt";
    private ImageView red_dynamic;
    private AlertDialog alertDialog;
    private boolean isLatest = false;
    private VersionInfo versionInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //初始化控件
        initView();

        //判断当前是否为最新版本
        initFromNetVersion();
    }

    private void initView() {
        img_return = (ImageView) findViewById(R.id.imag_return);
        clearcache = (RelativeLayout) findViewById(R.id.clearCache);
        set_clear = (TextView) findViewById(R.id.set_clear);
        //更新
        update_gx = (RelativeLayout) findViewById(R.id.update_gx);
        red_dynamic = (ImageView) findViewById(R.id.red_dynamic);
        //关于我们
        about = (RelativeLayout) findViewById(R.id.about);
        //修改密码
        up_password = (RelativeLayout) findViewById(R.id.up_password);
        //邀请好友
        invitation = (RelativeLayout) findViewById(R.id.invitation);
        invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, InvitationActivity.class);
                startActivity(intent);
            }
        });
        up_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, UpasswordActivity.class);
                startActivity(intent);
            }
        });

        //关于我们
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }
        });


//缓存
        final File cacheDir = this.getCacheDir();
        String cacheSize = null;
        try {
            cacheSize = DataClearManager.getCacheSize(cacheDir);
            set_clear.setText("已缓存" + cacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //清除缓存
        clearcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfDialog = new SelfDialog(SettingActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("是否要清理缓存?");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(SettingActivity.this, "点击了--确定--按钮", Toast.LENGTH_LONG).show();
                        DataClearManager.cleanApplicationData(SettingActivity.this);

                        try {
                            String cachesize = DataClearManager.getCacheSize(cacheDir);
                            Toast.makeText(SettingActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                            set_clear.setText("已缓存0Mb");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(SettingActivity.this, "点击了--取消--按钮", Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }
        });
        //更新
        update_gx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断服务器上版本信息
                if (isLatest) {          //已经是最新版本
                    Toast.makeText(SettingActivity.this, "您已经是最新版本！", Toast.LENGTH_SHORT).show();
                } else {                 //不是最新版本
                    showWindow();
                }
            }
        });
    }

    /**
     * 判断服务器上版本信息
     */
    private void initFromNetVersion() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                // Volley在有时候请求数据时会出现乱码
                //在这里我们需要将乱码转换为UTF-8格式,从而将问题解决
                try {
                    response = new String(response.getBytes("ISO-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                versionInfo = gson.fromJson(response, VersionInfo.class);
                String versionName = versionInfo.versionName;
                //判断版本是否一致
                if (getVersionName().equals(versionName)) {
                    red_dynamic.setVisibility(View.GONE);
                    isLatest = true;
                } else {
                    red_dynamic.setVisibility(View.VISIBLE);
                    isLatest = false;
                }
            }
        }.getData(urlPath, BaseData.NOTIME, null, 0);
    }

    /**
     * 弹出弹出框
     */
    private void showWindow() {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

        //    设置Title的内容
        builder.setTitle("软件更新");
        //    设置Content来显示一个信息
        builder.setMessage("是否更新？");
        //    设置一个NegativeButton
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        //    设置一个PositiveButton
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downNewVersion();
            }
        });


        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 下载最新版本
     */
    private void downNewVersion() {

        //点击按钮--先判断当前是否是最新版本
        if (!isLatest) {
            String downloadUrl = versionInfo.downloadUrl;
            RequestParams requestParams = new RequestParams(downloadUrl);

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            x.http().get(requestParams, new Callback.ProgressCallback<File>() {
                @Override
                public void onSuccess(File file) {
                    //下载成功
                    Toast.makeText(SettingActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    //安装apk界面打开
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");

                    intent.setDataAndType(
                            Uri.fromFile(file),
                            "application/vnd.android.package-archive");
                    startActivity(intent);
                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public void onWaiting() {

                }

                @Override
                public void onStarted() {
                }

                //正在下载
                @Override
                public void onLoading(long total, long current, boolean b) {
                    progressDialog.setMax((int) total);
                    progressDialog.setProgress((int) current);
                }
            });

        }
    }


    /**
     * 获取当前版本名称
     *
     * @return
     */
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        //包名
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
