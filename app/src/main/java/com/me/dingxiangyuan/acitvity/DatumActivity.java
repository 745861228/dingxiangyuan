package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

public class DatumActivity extends BaseActivity {
    private ImageView ruturn_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datum);
        initView();
    }

    //初始化控件
    private void initView() {
        ruturn_img = (ImageView) findViewById(R.id.imag_return);

        //返回
        //返回
        ruturn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }

        });
    }
}