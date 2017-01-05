package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.SiftJsonBean;

public class Deatis2Activity extends AppCompatActivity {

    private SiftJsonBean.DataBean siftJsonBean;
    private ImageView xq_image1;
    private ImageView touxian_image1;
    private TextView wangming_text1;
    private TextView time_tv1;
    private TextView title_textView1;
    private TextView content_textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatis2);
        xq_image1 = (ImageView) findViewById(R.id.xq_image1);
        xq_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        siftJsonBean = (SiftJsonBean.DataBean) intent.getSerializableExtra("siftJsonBean");
        touxian_image1 = (ImageView) findViewById(R.id.touxian_image1);
        Glide.with(this).load(siftJsonBean.getHeadImg()).into(touxian_image1);
        wangming_text1 = (TextView) findViewById(R.id.wangming_text1);
        wangming_text1.setText(siftJsonBean.getUserName());
//        time_tv1 = (TextView) findViewById(R.id.time_tv1);
//        time_tv1.setText(siftJsonBean.getReplyTimes());
        title_textView1 = (TextView) findViewById(R.id.title_textView1);
        title_textView1.setText(siftJsonBean.getTitle());
        content_textView1 = (TextView) findViewById(R.id.content_textView1);
        content_textView1.setText(siftJsonBean.getContent());
    }
}
