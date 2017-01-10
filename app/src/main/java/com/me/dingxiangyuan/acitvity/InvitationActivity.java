package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

public class InvitationActivity extends BaseActivity {
    private ImageView img_return;
    private ImageView imageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        initView();
    }

    private void initView() {
        img_return = (ImageView) findViewById(R.id.imag_return);
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }
        });
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, (Parcelable) InvitationActivity.this);
                intent.setType("text/plain");


            }
        });
    }
}
