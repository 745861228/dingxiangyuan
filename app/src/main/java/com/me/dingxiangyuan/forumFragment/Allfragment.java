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
import com.me.dingxiangyuan.acitvity.MessageActivity;
import com.me.dingxiangyuan.adapter.AllRvAdpater;
import com.me.dingxiangyuan.adapter.SiftRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.AllJsonBean;
import com.me.dingxiangyuan.onLoadMoreListen.OnLoadMoreListener;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class Allfragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private AllRvAdpater refreshAdapter;
    private MyHomeData myHomeData;
    public String data;
    private RecyclerView all_recycler;
    private AllJsonBean allJsonBean;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    List<AllJsonBean.DataBean> allList = new ArrayList<>();
    private boolean isFlag = false;
    private int index = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //刷新
                case 0:
                    //停止刷新
                    swipeRefreshLayout.setRefreshing(false);
                    //展示数据
                    break;
            }

        }
    };

    @Override
    protected void onLoad() {
        myHomeData = new Allfragment.MyHomeData();
        index = 1;
        myHomeData.getData(UrlUtils.All + index, BaseData.NORMALTIME, null, 0);
    }

    @Override
    public View createSuccessView() {
        View view = initView();
        return view;
    }

    /**
     * 找控件
     *
     * @return
     */
    private View initView() {
        final View inflate = CommonUtils.inflate(R.layout.all_recyclerview);
        all_recycler = (RecyclerView) inflate.findViewById(R.id.all_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefreshLayout);
        /**
         * 设置布局管理
         */
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        all_recycler.setLayoutManager(linearLayoutManager);
        //设置进度的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //一上来先去做刷新的逻辑操作
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //请求数据
                index = 1;
                refreshData();
            }
        });
        //设置下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //对recycleView添加一个滑动的监听

        /**
         * 加载
         */
        all_recycler.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onloadMore() {
                //控制它加载的数据是下一页的
                index = index + 1;
                isFlag = false;
                loadMoreData();
            }
        });
        /**
         * 设置触摸监听对悬浮button设置效果
         */
        all_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                showTab(dy <= 0);
            }
        });
        floatingActionButton = (FloatingActionButton) inflate.findViewById(R.id.floatingActionButton);
        /**
         *  设置点击事件对floatingActionButton
         */
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });



        return inflate;
    }

    /**
     * 悬浮按钮操作
     *
     * @param isShow
     */
    private void showTab(boolean isShow) {
        if (isShow) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
    }

    /**
     * 对下拉刷新做操作
     */
    public void refreshData() {
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
                myHomeData.getData(UrlUtils.All + index, BaseData.NORMALTIME, null, 0);
                //把数据发送给主线程
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 对上拉刷新做操作
     */
    private void loadMoreData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //设置当前条目的索引值

                myHomeData = new Allfragment.MyHomeData();
                myHomeData.getData(UrlUtils.All + index, BaseData.NORMALTIME, null, 0);
                //把数据发送给主线程
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //数据请求
        refreshData();
    }


    /**
     * 请求网络
     */
    class MyHomeData extends BaseData {

        private List<AllJsonBean.DataBean> dataList;

        @Override
        public void setResultData(final String response) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);

            Allfragment.this.data = response;
            Gson gson = new Gson();
            allJsonBean = gson.fromJson(data, AllJsonBean.class);

            dataList = allJsonBean.getData();

            CommonUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {

                    //true 是刷新
                    if (isFlag) {
                        allList.clear();
                        allList.addAll(dataList);
                    } else {
                        allList.addAll(dataList);
                    }
                    if (refreshAdapter == null) {
                        refreshAdapter = new AllRvAdpater(getActivity(), allList);
                        all_recycler.setAdapter(refreshAdapter);
                    } else {
                        refreshAdapter.notifyDataSetChanged();
                    }
                    //设置recycler设置点击事件
                    refreshAdapter.setOnItemClickLitener(new SiftRvAdapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int position) {
                           Intent intent=new Intent(getActivity(), DetailsActivity.class);
                           intent.putExtra("allJsonBean",allList.get(position));
                           getActivity().startActivity(intent);
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });

                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshAdapter = null;
    }
}
