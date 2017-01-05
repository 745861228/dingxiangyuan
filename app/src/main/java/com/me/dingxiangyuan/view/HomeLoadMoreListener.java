package com.me.dingxiangyuan.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * author by LiKe on 2017/1/5.
 */

public abstract class HomeLoadMoreListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager linearLayoutManager;
    private int preTotalItem;
    private boolean isLoading;

    public HomeLoadMoreListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //获取左后一条可见条目索引
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        //获取条目总数
        int itemCount = linearLayoutManager.getItemCount();

        if (itemCount != preTotalItem) {
            //可以进行加载了
            isLoading = false;
            //上一次的总个数等于当前个数----
            preTotalItem = itemCount;
        }

        if (!isLoading) {
            if (lastVisibleItemPosition == itemCount - 1) {
                isLoading = true;
                onLoadMore();
            }
        }
    }

    public abstract void onLoadMore();
}
