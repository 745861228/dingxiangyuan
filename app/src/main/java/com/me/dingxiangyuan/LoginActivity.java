package com.me.dingxiangyuan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 查找控件
        initView();
    }

    private void initView() {
        // 注册textview
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);

    }

    /**
     * 监听事件
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 注册监听
            case R.id.tv_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.register_in,R.anim.login_out);
                break;
        }
    }
}
