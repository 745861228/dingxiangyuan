package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.AllJsonBean;

public class DetailsActivity extends AppCompatActivity {

    private ImageView touxian_image;
    private TextView wangming_text;
    private TextView time_tv;
    private TextView title_textView;
    private TextView content_textView;
    private RecyclerView xq_recycler;
    private ImageView xq_image;
    private String path = "http://www.yulin520.com/a2a/forumReply/detailedShow?pageSize=10&sign=8783406554DDD2920DC61FAC5F6A7811&sort=1&ts=1768501243&page=1&id=";
    private AllJsonBean.DataBean allJsonBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        xq_image = (ImageView) findViewById(R.id.xq_image);
        xq_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        allJsonBean = (AllJsonBean.DataBean) intent.getSerializableExtra("allJsonBean");
        touxian_image = (ImageView) findViewById(R.id.touxian_image);
        Glide.with(this).load(allJsonBean.getHeadImg()).into(touxian_image);
        wangming_text = (TextView) findViewById(R.id.wangming_text);
        wangming_text.setText(allJsonBean.getUserName());
//        time_tv = (TextView) findViewById(R.id.time_tv);
//        time_tv.setText(allJsonBean.getReplyTimes());
        title_textView = (TextView) findViewById(R.id.title_textView);
        title_textView.setText(allJsonBean.getTitle());
        content_textView = (TextView) findViewById(R.id.content_textView);
        content_textView.setText(allJsonBean.getContent());

    }

}
