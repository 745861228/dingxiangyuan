package com.me.dingxiangyuan.acitvity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.HomeParticularsAdapater;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.HomeLoveParticularsBean;
import com.me.dingxiangyuan.bean.LoveCommunityBean;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class ParticularsActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toorBar;
    private LoveCommunityBean.DataBean dataBean;
    private CircleImageView touxian_image1;
    private TextView wangming_text1;
    private TextView time_tv1;
    private TextView title_textView1;
    private TextView content_textView1;
    private CheckBox index_cart_cb;
    private TextView nice_tv;
    private TextView huitei_textView;
    private RecyclerView recyclerView;
    private String path = "http://www.yulin520.com/a2a/forumReply/detailedShow?pageSize=10&id=10459&sign=6F34FCC12B19E91E8514949EAFC911AA&sort=1&ts=1609018744&page=1&id=";
    private ImageView heart_image;
    private int[] pic = {R.mipmap.heart_1, R.mipmap.heart_2, R.mipmap.heart_3, R.mipmap.heart_4, R.mipmap.heart_5, R.mipmap.heart_6, R.mipmap.heart_7, R.mipmap.heart_8, R.mipmap.heart_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);

        initView();
        //获取数据
        Intent intent = getIntent();
        dataBean = (LoveCommunityBean.DataBean) intent.getSerializableExtra("dataBean");
        //设置数据
        initDatas();
        //请求recyclerView数据
        initRecyclerViewDatas();
    }

    /**
     * 设置recyclerView数据并设置适配器
     */
    private void initRecyclerViewDatas() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    //解析
                    Gson gson = new Gson();
                    HomeLoveParticularsBean homeLoveParticularsBean = gson.fromJson(response, HomeLoveParticularsBean.class);
                    huitei_textView.setText(homeLoveParticularsBean.data.size() + "");
                    //设置适配器
                    recyclerView.setAdapter(new HomeParticularsAdapater(ParticularsActivity.this, homeLoveParticularsBean.data));

                }
            }
        }.getData(path + dataBean.userId, BaseData.NORMALTIME, null, 0);
    }

    private void initDatas() {
        Glide.with(this).load(dataBean.headImg).into(touxian_image1);
        wangming_text1.setText(dataBean.userName);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");
        String createTime = format.format(dataBean.createTime);
        time_tv1.setText(createTime);
        content_textView1.setText(dataBean.content);
        title_textView1.setText(dataBean.title);
        nice_tv.setText(dataBean.nice + "");

    }

    /**
     * 初始化控件
     */
    private void initView() {
        toorBar = (Toolbar) findViewById(R.id.toorBar);
        //设置对应的菜单栏
        toorBar.inflateMenu(R.menu.toolbar_main);
        toorBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                }
                return false;
            }
        });


        toorBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //查找控件
        touxian_image1 = (CircleImageView) findViewById(R.id.touxian_image1);
        wangming_text1 = (TextView) findViewById(R.id.wangming_text1);
        time_tv1 = (TextView) findViewById(R.id.time_tv1);
        title_textView1 = (TextView) findViewById(R.id.title_textView1);
        content_textView1 = (TextView) findViewById(R.id.content_textView1);
        index_cart_cb = (CheckBox) findViewById(R.id.index_cart_cb);
        nice_tv = (TextView) findViewById(R.id.nice_tv);
        huitei_textView = (TextView) findViewById(R.id.huitei_textView);

        heart_image = (ImageView) findViewById(R.id.heart_image);

        //点赞
        index_cart_cb.setOnClickListener(this);
        //设置recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.xq_recycler2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setFocusable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_cart_cb:
                if (index_cart_cb.isChecked()) {
                    nice_tv.setText(Integer.parseInt(dataBean.nice) + 1 + "");
                    heart_image.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    int anInt = random.nextInt(pic.length);
                    heart_image.setImageResource(pic[anInt]);
                    setAnimation(heart_image, anInt);
                } else {
                    nice_tv.setText(dataBean.nice);
                    heart_image.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    public void setAnimation(ImageView heart_image, int anInt) {
        ObjectAnimator objectAnimatorY = null;
        ObjectAnimator objectAnimatorX = null;
        if (anInt % 2 == 0) {
            objectAnimatorY = ObjectAnimator.ofFloat(heart_image, View.TRANSLATION_Y, 0, -80, -200, -500);
            objectAnimatorX = ObjectAnimator.ofFloat(heart_image, View.TRANSLATION_X, 0, 80, 40, 90);
            objectAnimatorX.setDuration(3000);
            objectAnimatorY.setDuration(3000);
        } else {
            objectAnimatorY = ObjectAnimator.ofFloat(heart_image, View.TRANSLATION_Y, 0, -80, -200, -500);
            objectAnimatorX = ObjectAnimator.ofFloat(heart_image, View.TRANSLATION_X, 0, -40, -80, -45);
            objectAnimatorX.setDuration(3000);
            objectAnimatorY.setDuration(3000);
        }
        objectAnimatorX.start();
        objectAnimatorY.start();

    }
}
