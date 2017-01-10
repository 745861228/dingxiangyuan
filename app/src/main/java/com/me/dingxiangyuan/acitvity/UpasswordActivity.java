package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.LoginBean;
import com.me.dingxiangyuan.utils.CommonUtils;

public class UpasswordActivity extends BaseActivity implements View.OnClickListener {
    //旧密码
    private TextView old_password;
    private EditText et_oldpassword;
    //新密码
    private TextView tv_newpassword1;
    private EditText et_newpassword;
    //再次输入新密码
    private TextView tv_oncepassword;
    private EditText et_oncepassword;
    private boolean isVisible = true;
    private boolean Visible = true;
    private ImageView img_return;
    private TextView upassword_tv_Success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upassword);
        //初始化控件
        initView();
    }

    private void initView() {
        old_password = (TextView) findViewById(R.id.old_password);
        et_oldpassword = (EditText) findViewById(R.id.et_oldpassword);

        tv_newpassword1 = (TextView) findViewById(R.id.tv_newpassword1);
        et_newpassword = (EditText) findViewById(R.id.et_newpassword);

        tv_oncepassword = (TextView) findViewById(R.id.tv_oncepassword);
        et_oncepassword = (EditText) findViewById(R.id.et_oncepassword);
        //  et_oldpassword.setOnClickListener(this);
        // et_newpassword.setOnClickListener(this);
        //et_oncepassword.setOnClickListener(this);
        img_return = (ImageView) findViewById(R.id.imag_return);

        //完成控件id
        upassword_tv_Success = (TextView) findViewById(R.id.upassword_tv_Success);
        //点击完成按钮监听
        upassword_tv_Success.setOnClickListener(this);


        //隐藏的控件
        old_password.setVisibility(View.INVISIBLE);
        tv_newpassword1.setVisibility(View.INVISIBLE);
        tv_oncepassword.setVisibility(View.INVISIBLE);
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }
        });


        et_oldpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                old_password.setVisibility(View.VISIBLE);
                tv_oncepassword.setVisibility(View.INVISIBLE);
                tv_newpassword1.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tv_newpassword1.setVisibility(View.VISIBLE);
                old_password.setVisibility(View.INVISIBLE);
                tv_oncepassword.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_oncepassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tv_oncepassword.setVisibility(View.VISIBLE);
                tv_newpassword1.setVisibility(View.INVISIBLE);
                old_password.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 监听完成按钮
            case R.id.upassword_tv_Success:
                // 得到已登陆的用户手机号
                String phone = CommonUtils.getPhone("phone");
                // 获取新密码
                String NewPassword = et_newpassword.getText().toString().trim();
                // 确认一遍
                String OncePassword = et_oncepassword.getText().toString().trim();
                // 两者比较
                if (NewPassword.equals(OncePassword)) {
                    new BaseData() {
                        @Override
                        public void setResultData(String response) {
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                            String status = loginBean.getStatus();
                            switch (status) {
                                case "ok":
                                    Toast.makeText(UpasswordActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                    finish();
                                    overridePendingTransition(R.anim.register_in, R.anim.login_out);
                                    break;
                                case "error":
                                    Toast.makeText(UpasswordActivity.this, "填写有误请重新填写!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }.getData("http://114.112.104.151:8203/LvScore_Service/visit/setUserLoginPassword.do?telNum=" + phone + "&password=" + NewPassword, BaseData.NOTIME, "", 0);

                } else {
                    Toast.makeText(this, "密码输入不一致请重新输入。", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
