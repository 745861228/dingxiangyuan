package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.XqRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.AllJsonBean;
import com.me.dingxiangyuan.bean.XpJosnBean;
import com.me.dingxiangyuan.utils.CommonUtils;

import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {

    private ImageView touxian_image;
    private TextView wangming_text;
    private TextView time_tv;
    private TextView title_textView;
    private TextView content_textView;
    private RecyclerView xq_recycler;
    private ImageView xq_image;
    private AllJsonBean.DataBean allJsonBean;
    private ImageView tupian_image;
    private SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");

    private XpJosnBean xpJosnBean;
    private int id;
    private TextView huitei_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        xq_image = (ImageView) findViewById(R.id.xq_image);
        xq_recycler = (RecyclerView) findViewById(R.id.xq_recycler);
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
        id = allJsonBean.getId();

        wangming_text = (TextView) findViewById(R.id.wangming_text);
        wangming_text.setText(allJsonBean.getUserName());
        time_tv = (TextView) findViewById(R.id.time_tv);
        String t= format.format(allJsonBean.getCreateTime());
        time_tv.setText(t);
        title_textView = (TextView) findViewById(R.id.title_textView);
        title_textView.setText(allJsonBean.getTitle());
        content_textView = (TextView) findViewById(R.id.content_textView);
        content_textView.setText(allJsonBean.getContent());
        huitei_textView = (TextView) findViewById(R.id.huitei_textView);
        huitei_textView.setText("("+allJsonBean.getReplyTimes()+")");
        tupian_image = (ImageView) findViewById(R.id.tupian_image);
        if (allJsonBean.getImgs().size() > 0) {
            Glide.with(this).load(allJsonBean.getImgs().get(0).getMiniImg()).into(tupian_image);
        } else {
            tupian_image.setVisibility(View.GONE);
        }
        xq_recycler.setLayoutManager(new LinearLayoutManager(this));
           initData();
    }

    /**
     * 网络请求
     */
    private void initData() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                Gson gson=new Gson();
                xpJosnBean = gson.fromJson(response, XpJosnBean.class);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        XqRvAdapter xqRvAdapter=new XqRvAdapter(DetailsActivity.this,xpJosnBean);
                        xq_recycler.setAdapter(xqRvAdapter);
                    }
                });

            }
        }.getData("http://www.yulin520.com/a2a/forumReply/detailedShow?pageSize=10&sign=8783406554DDD2920DC61FAC5F6A7811&sort=1&ts=1768501243&page=1&id="+id,BaseData.NORMALTIME,"",0);
    }

}
