package com.me.dingxiangyuan.acitvity;


import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.RegisterBean;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back_login;
    private AppCompatEditText register_et_phone;
    private AppCompatEditText register_et_password;
    private AppCompatCheckBox register_checkbox;
    private TextView register_tv_register;
    private AppCompatEditText register_VerificationCode;
    private Button btn_get_VerificationCode;
    private CardView cardView;
    private TimeCount time;
    private String phone;
    private CheckBox register_checkbox_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 查找控件
        initView();
        //初始化按钮计时
        time = new TimeCount(60000, 1000);
    }

    /**
     * 查找控件
     */
    private void initView() {
        // 登录textview
        tv_back_login = (TextView) findViewById(R.id.tv_back_login);
        // 设置 登录textview 点击事件
        tv_back_login.setOnClickListener(this);
        // 获得用户手机输入框
        register_et_phone = (AppCompatEditText) findViewById(R.id.register_et_phone);
        //获得用户输入验证码输入框
        register_VerificationCode = (AppCompatEditText) findViewById(R.id.register_VerificationCode);
        // 获得用户输入密码输入框
        register_et_password = (AppCompatEditText) findViewById(R.id.register_et_password);
        // 获得是否阅读用户协议chebox
        register_checkbox = (AppCompatCheckBox) findViewById(R.id.register_checkbox);
        // 获得注册按钮的TextView
        register_tv_register = (TextView) findViewById(R.id.register_tv_register);
        //设置注册按钮监听
        register_tv_register.setOnClickListener(this);
        // 获得获取验证码按钮
        btn_get_VerificationCode = (Button) findViewById(R.id.btn_get_VerificationCode);
        // 设置点击事件
        btn_get_VerificationCode.setOnClickListener(this);
        //获得cardView的id
        cardView = (CardView) findViewById(R.id.cardView_get_VerificationCode);
        // 获得是否显示密码的chebox
        register_checkbox_show = (CheckBox) findViewById(R.id.register_checkbox_show);
        // 显示密码的chebox设置监听
        register_checkbox_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register_checkbox_show.isChecked()){
                    register_et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    register_et_password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_login:
                // 点击返回登录页面
                finish();
                overridePendingTransition(R.anim.register_in, R.anim.login_out);
                break;
            case R.id.register_tv_register:
                //点击进行注册
                // 获得验证码
                String code = register_VerificationCode.getText().toString().trim();
                // 将字符串转换为int
                int VerificationCode = Integer.parseInt(code);
                //获得密码
                final String password = register_et_password.getText().toString().trim();
                new BaseData() {
                    @Override
                    public void setResultData(String response) {
                        Gson gson = new Gson();
                        RegisterBean registerBean = gson.fromJson(response, RegisterBean.class);
                        String status = registerBean.getStatus();
                        switch (status) {
                            case "ok":
                                if (register_checkbox.isChecked()) {
                                    // 一切准备就绪
                                    new BaseData() {
                                        @Override
                                        public void setResultData(String response) {
                                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }.getData("http://114.112.104.151:8203/LvScore_Service/visit/user_register.do?telNum=" + phone + "&name=godboy&password=" + password, BaseData.NOTIME, "", 0);

                                } else {
                                    Toast.makeText(RegisterActivity.this, "请阅读用户协议!", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "error":
                                Toast.makeText(RegisterActivity.this, registerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }.getData("http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do?telNum=" + phone + "&verCode=" + VerificationCode, BaseData.NOTIME, "", 0);
                break;
            case R.id.btn_get_VerificationCode:
                //获取手机号
                phone = register_et_phone.getText().toString().trim();
                // 监听获取验证码按钮
                time.start();
                new BaseData() {
                    @Override
                    public void setResultData(String response) {
                        Gson gson = new Gson();
                        // 解析字符串
                        RegisterBean registerBean = gson.fromJson(response, RegisterBean.class);
                        // 拿出状态值进行判断
                        String status = registerBean.getStatus();
                        switch (status) {
                            case "ok":
                                Toast.makeText(RegisterActivity.this, "我们已将短信发送至您的手机，请注意查收!", Toast.LENGTH_SHORT).show();
                                break;
                            case "error":
                                Toast.makeText(RegisterActivity.this, "手机号输入不正确,请重新输入", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }.getData("http://114.112.104.151:8203/LvScore_Service/visit/user_getverificationcode.do?telNum=" + phone, BaseData.NOTIME, "", 0);
                break;
        }
    }

    /**
     * 验证码按钮倒计时
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_get_VerificationCode.setClickable(false);
            btn_get_VerificationCode.setText(millisUntilFinished / 1000 + "秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btn_get_VerificationCode.setText("重新获取验证码");
            btn_get_VerificationCode.setClickable(true);
            btn_get_VerificationCode.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.GRAY);
        }
    }
}
