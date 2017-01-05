package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

//关于我们
public class AboutActivity extends BaseActivity {
private ImageView img_return;
    private RelativeLayout abouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
    }

    private void initView() {
        img_return= (ImageView) findViewById(R.id.imag_return);
        abouts= (RelativeLayout) findViewById(R.id.abouts);
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin,R.anim.leftout);
            }
        });

        //关于我们
        abouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent=new Intent(AboutActivity.this,LunchActivity.class);
               // startActivity(intent);
            }
        });

    }
}
