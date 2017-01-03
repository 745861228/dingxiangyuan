package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.utils.DataClearManager;
import com.me.dingxiangyuan.view.SelfDialog;

import java.io.File;

public class SettingActivity extends BaseActivity {
    private RelativeLayout clearcache;
    private TextView set_clear;
    private SelfDialog selfDialog;
    private TextView message;
    private ImageView img_return;
    //更新
    private RelativeLayout update_gx;

    //缓存
    private String cacheSize = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //初始化控件
        initView();

    }

    //初始化控件
    private void initView() {
        clearcache = (RelativeLayout) findViewById(R.id.clearCache);
        set_clear = (TextView) findViewById(R.id.set_clear);
        message = (TextView) findViewById(R.id.message);
        img_return = (ImageView) findViewById(R.id.imag_return);
        update_gx=(RelativeLayout) findViewById(R.id.update_gx);
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

        try {
            cacheSize = DataClearManager.getCacheSize(cacheDir);
            set_clear.setText("已缓存" + cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
//清理缓存
        clearcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfDialog = new SelfDialog(SettingActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("是否要清理" + cacheSize + "缓存");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        DataClearManager.cleanApplicationData(SettingActivity.this);

                        try {

                            cacheSize = DataClearManager.getCacheSize(cacheDir);
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
        update_gx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });

    }

}
