package com.me.dingxiangyuan.acitvity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.me.dingxiangyuan.bean.LoginBean;
import com.me.dingxiangyuan.bean.RegisterBean;

import static com.me.dingxiangyuan.R.id.btn_get_VerificationCode;
import static com.me.dingxiangyuan.R.id.swipeRefreshLayout;
import static com.me.dingxiangyuan.R.id.time;

public class ForgetpassActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText forgetpass_phone;
    private AppCompatEditText forgetpass_VerificationCode;
    private AppCompatEditText forgetpass_password;
    private Button forgetpass_btn_get_VerificationCode;
    private CheckBox forgetpass_checkbox_show;
    private TextView forgetpass_tv_commit;
    private CardView cardView;
    private MyTimeCount time;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        // 查找控件
        initView();
        // 初始化计时
        time = new MyTimeCount(60000, 1000);
    }

    private void initView() {
        // 查找用户手机号输入框
        forgetpass_phone = (AppCompatEditText) findViewById(R.id.forgetpass_phone);
        //查找用户验证码输入框
        forgetpass_VerificationCode = (AppCompatEditText) findViewById(R.id.forgetpass_VerificationCode);
        // 查找用户密码输入框
        forgetpass_password = (AppCompatEditText) findViewById(R.id.forgetpass_password);
        // 获得得到验证码按钮
        forgetpass_btn_get_VerificationCode = (Button) findViewById(R.id.forgetpass_btn_get_VerificationCode);
        // 设置监听
        forgetpass_btn_get_VerificationCode.setOnClickListener(this);
        // 获得是否显示密码chebox
        forgetpass_checkbox_show = (CheckBox) findViewById(R.id.forgetpass_checkbox_show);
        // 设置监听
        forgetpass_checkbox_show.setOnClickListener(this);
        // 获得提交用户信息按钮
        forgetpass_tv_commit = (TextView) findViewById(R.id.forgetpass_tv_commit);
        // 设置监听
        forgetpass_tv_commit.setOnClickListener(this);
        // 得到CardView
        cardView = (CardView) findViewById(R.id.for_cardView_get_VerificationCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 得到获取验证码按钮监听
            case R.id.forgetpass_btn_get_VerificationCode:
                // 获得用户手机号
                phone = forgetpass_phone.getText().toString().trim();
                time.start();
                new BaseData() {
                    @Override
                    public void setResultData(String response) {
                        Gson gson = new Gson();
                        RegisterBean registerBean = gson.fromJson(response, RegisterBean.class);
                        String status = registerBean.getStatus();
                        switch (status) {
                            case "ok":
                                Toast.makeText(ForgetpassActivity.this, "我们已经短信发至您的手机,请注意查收", Toast.LENGTH_SHORT).show();
                                break;
                            case "error":
                                Toast.makeText(ForgetpassActivity.this, registerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }.getData("http://114.112.104.151:8203/LvScore_Service/visit/user_getverificationcode.do?telNum=" + phone, BaseData.NOTIME, "", 0);
                break;
            // 得到是否显示密码监听
            case R.id.forgetpass_checkbox_show:
                if (forgetpass_checkbox_show.isChecked()) {
                    forgetpass_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    forgetpass_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            // 得到用户提交按钮监听
            case R.id.forgetpass_tv_commit:
                //得到用户密码
                String password = forgetpass_password.getText().toString().trim();
                new BaseData() {
                    @Override
                    public void setResultData(String response) {
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                        String status = loginBean.getStatus();
                        switch (status) {
                            case "ok":
                                finish();
                                Toast.makeText(ForgetpassActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                break;
                            case "error":
                                Toast.makeText(ForgetpassActivity.this, loginBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }.getData("http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do?telNum=" + phone + "&password=" + password, BaseData.NOTIME, "", 0);


                break;

        }
    }

    /**
     * 验证码按钮倒计时
     */
    class MyTimeCount extends CountDownTimer {

        public MyTimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            forgetpass_btn_get_VerificationCode.setClickable(false);
            forgetpass_btn_get_VerificationCode.setText(millisUntilFinished / 1000 + "秒后可重新发送");
        }

        @Override
        public void onFinish() {
            forgetpass_btn_get_VerificationCode.setText("重新获取验证码");
            forgetpass_btn_get_VerificationCode.setClickable(true);
            forgetpass_btn_get_VerificationCode.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.GRAY);
        }
    }
}
