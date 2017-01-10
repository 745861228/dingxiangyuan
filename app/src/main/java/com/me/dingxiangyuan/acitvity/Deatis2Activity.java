package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.XqRvAdapter2;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.SiftJsonBean;
import com.me.dingxiangyuan.bean.XpJosnBean;
import com.me.dingxiangyuan.utils.CommonUtils;

import java.text.SimpleDateFormat;

public class Deatis2Activity extends AppCompatActivity  {

    private SiftJsonBean.DataBean siftJsonBean;
    private ImageView xq_image1;
    private ImageView touxian_image1;
    private TextView wangming_text1;
    private TextView time_tv1;
    private TextView title_textView1;
    private TextView content_textView1;
    private ImageView tupian1_image;
    private SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    private RecyclerView xq_recycler2;
    private XpJosnBean xpJosnBean;
    private int id;
    private TextView huitei;
    private ImageView caidan;
    private CheckBox index_cart_cb2;
    private TextView nice_tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatis2);
        xq_image1 = (ImageView) findViewById(R.id.xq_image1);
        xq_recycler2 = (RecyclerView) findViewById(R.id.xq_recycler2);
        xq_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin,R.anim.leftout);
            }
        });
        Intent intent = getIntent();
        siftJsonBean = (SiftJsonBean.DataBean) intent.getSerializableExtra("siftJsonBean");
        touxian_image1 = (ImageView) findViewById(R.id.touxian_image1);
        Glide.with(this).load(siftJsonBean.getHeadImg()).into(touxian_image1);
        id = siftJsonBean.getId();
//        index_cart_cb2 = (CheckBox) findViewById(index_cart_cb1);
//        index_cart_cb2.setOnClickListener(this);
//        nice_tv2 = (TextView) findViewById(jiajia_nice_tv2);
        wangming_text1 = (TextView) findViewById(R.id.wangming_text1);
        wangming_text1.setText(siftJsonBean.getUserName());
        time_tv1 = (TextView) findViewById(R.id.time_tv1);
        String t= format.format(siftJsonBean.getCreateTime());
        time_tv1.setText(t);
        title_textView1 = (TextView) findViewById(R.id.title_textView1);
        title_textView1.setText(siftJsonBean.getTitle());
        content_textView1 = (TextView) findViewById(R.id.content_textView1);
        content_textView1.setText(siftJsonBean.getContent());
        huitei = (TextView) findViewById(R.id.huitei);
        huitei.setText("("+siftJsonBean.getReplyTimes()+")");
        tupian1_image = (ImageView) findViewById(R.id.tupian1_image);
        if (siftJsonBean.getImgs().size() > 0) {
            Glide.with(this).load(siftJsonBean.getImgs().get(0).getMiniImg()).into(tupian1_image);
        } else {
            tupian1_image.setVisibility(View.GONE);
        }
        xq_recycler2.setLayoutManager(new LinearLayoutManager(this));

        initDate();

    }

    private void initDate() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                Gson gson=new Gson();
                xpJosnBean = gson.fromJson(response, XpJosnBean.class);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        XqRvAdapter2  adpter=new XqRvAdapter2(Deatis2Activity.this,xpJosnBean);
                        xq_recycler2.setAdapter(adpter);
                    }
                });

            }
        }.getData("http://www.yulin520.com/a2a/forumReply/detailedShow?pageSize=10&sign=8783406554DDD2920DC61FAC5F6A7811&sort=1&ts=1768501243&page=1&id="+id,BaseData.NORMALTIME,"",0);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.index_cart_cb2:
//                if (index_cart_cb2.isChecked()) {
//                    nice_tv2.setText(Integer.parseInt(siftJsonBean.getNice() + 1 + ""));
//                } else {
//                    nice_tv2.setText(siftJsonBean.getNice());
//                }
//                break;
//        }
//    }
}
