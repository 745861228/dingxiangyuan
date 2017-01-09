package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.LoadMoreRvAdapater;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.LoadMoreBean;
import com.me.dingxiangyuan.interfaces.OnItemClickListener;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.HomeLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class HomeLoadMoreActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView lordMoreRecyclerView;
    private boolean isFlag = false;
    private List<LoadMoreBean.DataBean> dataBeanArrayList = new ArrayList<>();
    private int page = -1;
    private LoadMoreRvAdapater loadMoreRvAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_load_more);
        //初始化数据
        initView();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        lordMoreRecyclerView = (RecyclerView) findViewById(R.id.lordMoreRecyclerView);

        //设置swipeRefreshLayout进度条颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lordMoreRecyclerView.setLayoutManager(linearLayoutManager);
        //设置进来就刷新数据
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                isFlag = true;
                page = 1;
                swipeRefreshLayout.setRefreshing(true);
                initDatas();
            }
        });
        //设置课下拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);
        //对recycleView添加一个滑动的监听
         lordMoreRecyclerView.addOnScrollListener(new HomeLoadMoreListener(linearLayoutManager) {
             @Override
             public void onLoadMore() {
                 isFlag = false;
                 initDatas();
                 page++;
             }
         });
    }

    private void initDatas() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    new BaseData() {
                        @Override
                        public void setResultData(final String response) {
                            if (response != null) {
                                CommonUtils.runOnMainThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        //解析数据
                                        Gson gson = new Gson();
                                        LoadMoreBean loadMoreBean = gson.fromJson(response, LoadMoreBean.class);
                                        if (isFlag) {
                                            swipeRefreshLayout.setRefreshing(false);
                                            dataBeanArrayList.clear();
                                            dataBeanArrayList.addAll(loadMoreBean.data);
                                        } else {
                                            dataBeanArrayList.addAll(loadMoreBean.data);
                                        }
                                        if (loadMoreRvAdapater == null) {
                                            loadMoreRvAdapater = new LoadMoreRvAdapater(dataBeanArrayList, HomeLoadMoreActivity.this);
                                            lordMoreRecyclerView.setAdapter(loadMoreRvAdapater);
                                        } else {
                                            loadMoreRvAdapater.notifyDataSetChanged();
                                        }

                                        loadMoreRvAdapater.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void setOnItemClickListener(int position) {
                                                Intent intent = new Intent(HomeLoadMoreActivity.this,WebViewActivity.class);
                                                intent.putExtra("dataBeanArrayListUrl",dataBeanArrayList.get(position).url);
                                                startActivity(intent);
                                            }
                                        });

                                    }
                                });
                            }
                        }
                    }.getData(UrlUtils.LOADMORE + page, BaseData.NOTIME, null, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 下拉数显操作
     */
    @Override
    public void onRefresh() {
        page = 1;
        initDatas();
    }
}
