package com.me.dingxiangyuan.acitvity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.BoardRecyclerAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.CommunityBean;
import com.me.dingxiangyuan.onLoadMoreListen.OnLoadMoreListener;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class BanKuaiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private CircleImageView board_iv;
    private TextView board_title;
    private TextView board_detail;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<CommunityBean.DataBean> topList = new ArrayList<>();
    private List<CommunityBean.DataBean> contentList = new ArrayList<>();
    private BoardRecyclerAdapter boardRecyclerAdapter;
    private int tag;
    private int page = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<CommunityBean.DataBean> data1 = (List<CommunityBean.DataBean>) msg.obj;
            if (data1 != null && data1.size() > 0) {
                switch (msg.what) {
                    case 0:
                        swipeRefreshLayout.setRefreshing(false);
                        topList.clear();
                        topList.addAll(data1);
                        break;
                    case 1:
                        contentList.addAll(data1);
                        break;
                }
            }
           if (boardRecyclerAdapter == null) {
               if (contentList.size()>0&&topList.size()>0){
                   boardRecyclerAdapter = new BoardRecyclerAdapter(topList, contentList, BanKuaiActivity.this);
                   recyclerView.setAdapter(boardRecyclerAdapter);
                //添加适配器
               }

            } else {
                boardRecyclerAdapter.notifyDataSetChanged();
            }
    }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_kuai);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        topList.clear();
        contentList.clear();
    }
    /**
     * 请求网络数据
     */
    private void requestTopData(int topType) {
        final Gson gson = new Gson();
        new BaseData() {
            @Override
            public void setResultData(String response) {
                CommunityBean communityBean = gson.fromJson(response, CommunityBean.class);
                List<CommunityBean.DataBean> data1 = communityBean.getData();
                Message message = handler.obtainMessage();
                message.obj = data1;
                message.what = 0;
                handler.sendMessageDelayed(message, 2000);
            }
        }.getData(UrlUtils.moonTop+topType,BaseData.NORMALTIME,"",0);
        new BaseData() {
            @Override
            public void setResultData(String response) {
                CommunityBean communityBean = gson.fromJson(response, CommunityBean.class);
                List<CommunityBean.DataBean> data2 = communityBean.getData();
                Message message = handler.obtainMessage();
                message.obj = data2;
                message.what = 1;
                handler.sendMessageDelayed(message, 2000);
            }
        }.getData(UrlUtils.moonContent+ "&page=" + page + "&forumType=" + topType,BaseData.NORMALTIME,"",0);
    }
    /**
     * 标题信息
     *
     * @param tag
     */
    private void titleMessage(int tag) {
        if (tag == 10) {
            Data(R.mipmap.forum_section_first_logo, "你的月亮我的心", "最走心的情感答疑电台");
        } else if (tag == 11) {
            Data(R.mipmap.forum_section_second_logo, "恋爱羞羞事", "春风十里睡你");
        } else if (tag == 12) {
            Data(R.mipmap.forum_section_third_logo, "约会必杀技", "约会套路一网打尽");
        } else if (tag == 13) {
            Data(R.mipmap.forum_section_four_logo, "主要看颜值", "不整容,颜值照样up");
        } else if (tag == 14) {
            Data(R.mipmap.forum_section_five_logo, "恋爱直播间", "八卦?狗血?全都有");
        }
        requestTopData(tag);
    }

    /**
     * 标题控件添加数据
     */
    public void Data(int url, String title, String detail) {
        Glide.with(BanKuaiActivity.this).load(url).into(board_iv);
        board_title.setText(title);
        board_detail.setText(detail);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tag = getIntent().getIntExtra("tag", 0);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                //一上来先去做刷新的逻辑操作
                swipeRefreshLayout.setRefreshing(true);
                //请求数据
                titleMessage(tag);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.forum_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BanKuaiActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        board_iv = (CircleImageView) findViewById(R.id.board_iv);
        board_title = (TextView) findViewById(R.id.board_title);
        board_detail = (TextView) findViewById(R.id.board_detail);

        recyclerView.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onloadMore() {
                page++;
                requestTopData(tag);
            }
        });
        //返回按钮
        findViewById(R.id.forum_back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        requestTopData(tag);
        Toast.makeText(BanKuaiActivity.this, "刷新了", Toast.LENGTH_SHORT).show();
    }
}
