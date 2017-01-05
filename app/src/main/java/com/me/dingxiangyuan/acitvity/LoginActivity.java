package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.LoginBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.mingle.widget.LoadingView;
import com.zhy.autolayout.AutoLinearLayout;

import static com.me.dingxiangyuan.R.id.Success_item;
import static com.me.dingxiangyuan.R.id.loadView;
import static com.me.dingxiangyuan.R.id.swipeRefreshLayout;
import static com.me.dingxiangyuan.R.id.tv_forget_password;
import static com.me.dingxiangyuan.R.id.view;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private AppCompatEditText login_et_phone;
    private AppCompatEditText login_et_password;
    private TextView login_tv_login;
    private CheckBox checkBox;
    private AutoLinearLayout big_frame;
    private RelativeLayout animation;
    private LoadingView loadView;
    private TextView Success_item;
    private TextView tv_forget_password;


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
        // 获得用户手机号输入框
        login_et_phone = (AppCompatEditText) findViewById(R.id.login_et_phone);
        // 获得密码输入框
        login_et_password = (AppCompatEditText) findViewById(R.id.login_et_password);
        // 获得登录TextView
        login_tv_login = (TextView) findViewById(R.id.login_tv_login);
        // 监听登录按钮
        login_tv_login.setOnClickListener(this);
        // 获得是否显示密码的chebox
        checkBox = (CheckBox) findViewById(R.id.imageButton);
        // 是否显示密码的chebox设置点击事件
        checkBox.setOnClickListener(this);
        // 找到大的框架ID
        big_frame = (AutoLinearLayout) findViewById(R.id.big_frame);
        //找到登录成功的动画
        animation = (RelativeLayout) findViewById(R.id.animation);
        //动画页面
        loadView = (LoadingView) findViewById(R.id.loadView);
        // 登录成功TextView
        Success_item = (TextView) findViewById(R.id.Success_item);
        // 忘记密码id
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        // 设置监听
        tv_forget_password.setOnClickListener(this);

    }

    /**
     * 监听事件
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 注册监听
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.register_in, R.anim.login_out);
                break;
            // 登录监听
            case R.id.login_tv_login:
                // 获得用户手机号
                String phone = login_et_phone.getText().toString().trim();
                // 获得用户密码
                String password = login_et_password.getText().toString().trim();
                new BaseData() {

                    @Override
                    public void setResultData(String response) {
                        Gson gson = new Gson();
                        final LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                        String status = loginBean.getStatus();
                        switch (status) {
                            case "ok":
                                big_frame.setVisibility(View.GONE);
                                animation.setVisibility(View.VISIBLE);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        try {
                                            sleep(3000);
                                            CommonUtils.runOnMainThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    loadView.setVisibility(View.GONE);
                                                    Success_item.setVisibility(View.VISIBLE);
                                                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent1);
                                                    finish();
                                                }
                                            });
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                                CommonUtils.saveBolean("isLand", true);
                                break;
                            case "error":
                                big_frame.setVisibility(View.GONE);
                                animation.setVisibility(View.VISIBLE);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        try {
                                            sleep(3000);
                                            CommonUtils.runOnMainThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    animation.setVisibility(View.GONE);
                                                    big_frame.setVisibility(View.VISIBLE);
                                                    Toast.makeText(LoginActivity.this, loginBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                                break;
                        }
                    }
                }.getData("http://114.112.104.151:8203/LvScore_Service/visit/user_login.do?telNum=" + phone + "&password=" + password, BaseData.NOTIME, "", 0);
                break;
            // 是否显示密码的chebox
            case R.id.imageButton:
                if (checkBox.isChecked()) {
                    login_et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    login_et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            // 忘记密码监听
            case R.id.tv_forget_password:
                Intent intent1 = new Intent(this, ForgetpassActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.register_in, R.anim.login_out);
                break;
        }
    }
}
