package com.me.dingxiangyuan.forumFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.DetailsActivity;
import com.me.dingxiangyuan.adapter.SiftRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.SiftJsonBean;
import com.me.dingxiangyuan.onLoadMoreListen.OnLoadMoreListener;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */

public class Siftfragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private LinearLayoutManager linearLayoutManager;
    private SiftRvAdapter siftRvAdapter;
    private MyHomeData myHomeData;
    public String data;
    private SiftJsonBean siftJsonBean;
    private RecyclerView sift_recycler;
    private SwipeRefreshLayout refreshLayout;
    List<SiftJsonBean.DataBean> siftlistjson = new ArrayList<>();
    private boolean isFlag = false;
    private int index = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //停止刷新
                    refreshLayout.setRefreshing(false);
                    break;
            }
        }
    };


    @Override
    protected void onLoad() {
        myHomeData = new Siftfragment.MyHomeData();
        index = 1;
        myHomeData.getData(UrlUtils.Sift + index, BaseData.NORMALTIME, null, 0);
    }

    @Override
    public View createSuccessView() {
        View view = initView();
        return view;
    }

    /**
     * 初始化空件
     *
     * @return
     */
    private View initView() {
        View inflate = CommonUtils.inflate(R.layout.sift_layout);
        refreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe);
        sift_recycler = (RecyclerView) inflate.findViewById(R.id.sift_recyclerView);
        /**
         * 设置布局管理
         */
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        sift_recycler.setLayoutManager(linearLayoutManager);
        //设置进度颜色
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
        //一上来先去做刷新的逻辑操作
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                //请求网络
                index = 1;
                siftData();
            }
        });
        //设置下拉刷新
        refreshLayout.setOnRefreshListener(this);
        /**
         * 加载
         */
        sift_recycler.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onloadMore() {
                //控制它加载的数据是下一页
                index = index + 1;
                isFlag = false;
                siftloadMoreData();
            }
        });

        return inflate;
    }

    /**
     * 对下拉刷新做操作
     */
    private void siftData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index = 1;
                isFlag = true;
                myHomeData = new MyHomeData();
                myHomeData.getData(UrlUtils.Sift + index, BaseData.NORMALTIME, null, 0);
                //发送给线程
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //数据请求
        siftData();
    }

    /**
     * 对上拉刷新做操作
     */
    private void siftloadMoreData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myHomeData = new Siftfragment.MyHomeData();
                myHomeData.getData(UrlUtils.Sift + index, BaseData.NORMALTIME, null, 0);
                //把数据发送给主线程
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    /**
     * 请求网络
     */
    class MyHomeData extends BaseData {
        private List<SiftJsonBean.DataBean> siftList;

        @Override
        public void setResultData(final String response) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            Siftfragment.this.data = response;
            Gson gson = new Gson();
            siftJsonBean = gson.fromJson(data, SiftJsonBean.class);
            siftList = siftJsonBean.getData();
            CommonUtils.runOnMainThread(new Runnable() {

                @Override
                public void run() {
                    //true是刷新
                    if (isFlag) {
                        siftlistjson.clear();
                        siftlistjson.addAll(siftList);
                    } else {
                        siftlistjson.addAll(siftList);
                    }
                    if (siftRvAdapter == null) {
                        siftRvAdapter = new SiftRvAdapter(getActivity(), siftlistjson);
                        sift_recycler.setAdapter(siftRvAdapter);
                        //设置recycler设置点击事件
                        siftRvAdapter.setOnItemClickLitener(new SiftRvAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent=new Intent(getActivity(), DetailsActivity.class);
                                startActivity(intent);
                            }
                            @Override
                            public void onItemLongClick(View view, int position) {
                            }
                        });
                    } else {
                        siftRvAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        siftRvAdapter = null;
    }
}
