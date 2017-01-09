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
    private ImageView img_return;
    private RelativeLayout clearcache;
    private RelativeLayout update_gx;
    private TextView set_clear;
    private SelfDialog selfDialog;
    private RelativeLayout about;
    private RelativeLayout up_password;
    private RelativeLayout invitation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //初始化控件
        initView();
    }

    private void initView() {
        img_return=(ImageView)findViewById(R.id.imag_return);
        clearcache=(RelativeLayout)findViewById(R.id.clearCache) ;
        set_clear=(TextView)findViewById(R.id.set_clear) ;
        //更新
        update_gx=(RelativeLayout)findViewById(R.id.update_gx);
        //关于我们
        about=(RelativeLayout)findViewById(R.id.about);
        //修改密码
        up_password=(RelativeLayout)findViewById(R.id.up_password);
        //邀请好友
        invitation=(RelativeLayout)findViewById(R.id.invitation);
        invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,InvitationActivity.class);
                startActivity(intent);
            }
        });
        up_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UpasswordActivity.class);
                startActivity(intent);
            }
        });

        //关于我们
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
        overridePendingTransition(R.anim.leftin,R.anim.leftout);            }
        });



//缓存
        final File cacheDir=this.getCacheDir();
        String cacheSize= null;
        try {
            cacheSize = DataClearManager.getCacheSize(cacheDir);
            set_clear.setText("已缓存"+cacheSize);

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
                        Toast.makeText(SettingActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        DataClearManager.cleanApplicationData(SettingActivity.this);

                        try {
                            String cachesize=DataClearManager.getCacheSize(cacheDir);
                            Toast.makeText(SettingActivity.this,"已清除",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SettingActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
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
                Intent intent=new Intent(SettingActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
    }

}
