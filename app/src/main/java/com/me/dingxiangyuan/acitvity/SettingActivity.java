package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    private ImageView img_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //初始化控件
        initView();
    }

    private void initView() {
        img_return=(ImageView)findViewById(R.id.imag_return);


        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
overridePendingTransition(R.anim.leftin,R.anim.leftout);            }
        });
    }
}
