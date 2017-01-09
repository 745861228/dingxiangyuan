package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

public class UpasswordActivity extends BaseActivity{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upassword);
        //初始化控件
         initView();
    }

    private void initView() {
        old_password= (TextView) findViewById(R.id.old_password);
        et_oldpassword= (EditText) findViewById(R.id.et_oldpassword);

        tv_newpassword1= (TextView) findViewById(R.id.tv_newpassword1);
        et_newpassword= (EditText) findViewById(R.id.et_newpassword);

        tv_oncepassword= (TextView) findViewById(R.id.tv_oncepassword);
        et_oncepassword= (EditText) findViewById(R.id.et_oncepassword);
      //  et_oldpassword.setOnClickListener(this);
       // et_newpassword.setOnClickListener(this);
        //et_oncepassword.setOnClickListener(this);
        img_return=(ImageView)findViewById(R.id.imag_return);


        //隐藏的控件
        old_password.setVisibility(View.INVISIBLE);
        tv_newpassword1.setVisibility(View.INVISIBLE);
        tv_oncepassword.setVisibility(View.INVISIBLE);
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin,R.anim.leftout);            }
        });


        et_oldpassword.addTextChangedListener(new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           old_password.setVisibility(View.VISIBLE);
           tv_oncepassword  .setVisibility(View.INVISIBLE);
           tv_newpassword1 .setVisibility(View.INVISIBLE);

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
                tv_newpassword1 .setVisibility(View.VISIBLE);
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
                tv_oncepassword  .setVisibility(View.VISIBLE);
                tv_newpassword1 .setVisibility(View.INVISIBLE);
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



}
