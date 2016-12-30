package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.me.dingxiangyuan.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 查找控件
        initView();
    }

    /**
     * 查找控件
     */
    private void initView() {
        // 登录textview
        tv_back_login = (TextView) findViewById(R.id.tv_back_login);
        // 设置 登录textview 点击事件
        tv_back_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_login:
                // 点击返回登录页面
                finish();
                overridePendingTransition(R.anim.register_in,R.anim.login_out);
                break;
        }
    }
}
