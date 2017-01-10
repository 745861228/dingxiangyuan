package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.RecyclerAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.CommunityBean;
import com.me.dingxiangyuan.bean.DetailData;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.List;

public class DeatisBankuaiActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    private CheckBox detail_image;
    private CircleImageView landlord_image;
    private TextView landlord_name;
    private TextView commit_time;
    private TextView landlord_title;
    private TextView landlord_content;
    private TextView back_landlord;
    private CommunityBean.DataBean communityBean;
    private SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    private CheckBox detail_image1;
    private RecyclerView landlord_recycler;
    private Toolbar toolBar;
    private DetailData detailData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatis_bankuai);
        /**
         * 接受数据
         */
        Intent intent = getIntent();
        communityBean = (CommunityBean.DataBean) intent.getSerializableExtra("communityBean");

        detail_image = (CheckBox) findViewById(R.id.detail_image);
        Drawable[] compoundDrawables = detail_image.getCompoundDrawables();
        Rect r = new Rect(0, 0, compoundDrawables[1].getMinimumWidth() * 2 / 5, compoundDrawables[1].getMinimumHeight() * 2 / 5);
        //定义一个Rect边界
        compoundDrawables[1].setBounds(r);
        //给每一个RadioButton设置图片大小
        detail_image.setCompoundDrawables(null, compoundDrawables[1], null, null);
        initView();

        requestData();

    }

    /**
     * 请求网络数据
     */
    private void requestData() {
        String topDetailUrl = "http://www.yulin520.com/a2a/forumReply/detailedShow?pageSize=10&id=" + communityBean.getId() + "&sign=8783406554DDD2920DC61FAC5F6A7811&sort=1&ts=1768501243&page=1";
        new BaseData() {
            @Override
            public void setResultData(  String response) {
                Gson gson = new Gson();
                detailData = gson.fromJson(response, DetailData.class);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {

                        List<DetailData.DataBean> data1 = detailData.getData();
                        if (data1 != null && data1.size() > 0) {
                            back_landlord.setText("回帖(" + data1.size() + ")");
                            setAdapter(data1);
                        }
                    }
                });
            }
        }.getData(topDetailUrl, BaseData.NORMALTIME, "", 0);
    }
    private void setAdapter(List<DetailData.DataBean> data1) {
        landlord_recycler.setLayoutManager(new LinearLayoutManager(DeatisBankuaiActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        landlord_recycler.setAdapter(new RecyclerAdapter<DetailData.DataBean>(DeatisBankuaiActivity.this, data1, R.layout.comment) {
            @Override
            public void convert(RecyclerHolder holder, DetailData.DataBean data, int position) {
                ImageView two_land = holder.findView(R.id.two_land);
                if (position == 0) {
                    two_land.setVisibility(View.VISIBLE);
                } else {
                    two_land.setVisibility(View.GONE);
                }

                CircleImageView comment_image = holder.findView(R.id.comment_image);
                Glide.with(DeatisBankuaiActivity.this).load(data.getHeadImg()).into(comment_image);
                holder.setText(R.id.comment_name, data.getUserName());
                TextView comment_time = holder.findView(R.id.comment_time);
                String str = formatter.format(data.getCreateTime());
                comment_time.setText(data.getFloor() + " " + str);
                holder.setText(R.id.comment_content, data.getContent());

            }
        });

    }

    /**
     * 初始化数据
     */
    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        toolBar.inflateMenu(R.menu.toolbar);
        toolBar.setOnMenuItemClickListener(this);
        findViewById(R.id.return_back).setOnClickListener(this);
        //楼主的头像
        landlord_image = (CircleImageView) findViewById(R.id.landlord_image);
        Glide.with(this).load(communityBean.getHeadImg()).into(landlord_image);
        //楼主的名字
        landlord_name = (TextView) findViewById(R.id.landlord_name);
        landlord_name.setText(communityBean.getUserName());
        //帖子的时间
        commit_time = (TextView) findViewById(R.id.commit_time);
        String str = formatter.format(communityBean.getTopTime());
        commit_time.setText(str);
        //帖子标题
        landlord_title = (TextView) findViewById(R.id.landlord_title);
        landlord_title.setText(communityBean.getTitle());
        //帖子内容
        landlord_content = (TextView) findViewById(R.id.landlord_content);
        landlord_content.setText(communityBean.getContent());
        //回帖
        back_landlord = (TextView) findViewById(R.id.back_landlord);
        detail_image1 = (CheckBox) findViewById(R.id.detail_image);
        detail_image1.setText(communityBean.getNice() + "");
        landlord_recycler = (RecyclerView) findViewById(R.id.landlord_recycler);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.iv_table) {
            Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show();
        }
       return false;
    }

    /**
     * 对返回图片设置
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.return_back:
                finish();
                overridePendingTransition(R.anim.leftin,R.anim.leftout);
                break;
        }
    }
}
