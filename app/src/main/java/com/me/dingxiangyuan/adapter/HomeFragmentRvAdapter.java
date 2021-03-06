package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.Pager0Holder;
import com.me.dingxiangyuan.holder.Pager1Holder;
import com.me.dingxiangyuan.holder.Pager2Holder;
import com.me.dingxiangyuan.holder.Pager3Holder;
import com.me.dingxiangyuan.holder.Pager4Holder;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;

import java.util.List;

/**
 * Created by qwe on 2016/12/29.
 */

public class HomeFragmentRvAdapter extends RecyclerView.Adapter<BaseHolder> {

    private Context context;
    private List<String> jsonList;

    private final int LUNBO = 0;
    private final int ZHUGONGJIERI = 1;
    private final int VIEWPAGER = 2;
    private final int COLDWORE = 3;
    private final int LOADMORE = 4;


    public HomeFragmentRvAdapter(Context context, List<String> jsonList) {
        this.context = context;
        this.jsonList = jsonList;
        LogUtils.i("HomeFragmentRvAdapter", jsonList.size() + "------------------------------" + this.jsonList.size());
    }

    @Override
    public int getItemCount() {
        LogUtils.i("jsonList.size()", "--------------------------" + jsonList.size());
        LogUtils.i("AAAAAAAAAAAA0", jsonList.get(0));
        LogUtils.i("AAAAAAAAAAAA1", jsonList.get(1));
        LogUtils.i("AAAAAAAAAAAA2", jsonList.get(2));
        LogUtils.i("AAAAAAAAAAAA3", jsonList.get(3));
        LogUtils.i("AAAAAAAAAAAA4", jsonList.get(4));

        return jsonList.size();
    }

    /**
     * 判断当前状态
     *
     * @param
     * @param
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == LUNBO) {
            return LUNBO;
        } else if (position == ZHUGONGJIERI) {
            return ZHUGONGJIERI;
        } else if (position == VIEWPAGER) {
            return VIEWPAGER;
        } else if (position == COLDWORE) {
            return COLDWORE;
        }
        return LOADMORE;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断当前条目类型
        BaseHolder holder = null;
        View view = null;
        switch (viewType) {
            //顶部带动画轮播图
            case LUNBO:
                view = CommonUtils.inflate(R.layout.home_pager0);
                holder = new Pager0Holder(view);
                break;
            //助教节日
            case ZHUGONGJIERI:
                view = CommonUtils.inflate(R.layout.home_pager1);
                holder = new Pager1Holder(view);
                break;
            //恋乎社区
            case VIEWPAGER:
                view = CommonUtils.inflate(R.layout.home_pager2);
                holder = new Pager2Holder(view);
                break;
            //冷暖公知
            case COLDWORE:
                view = CommonUtils.inflate(R.layout.home_pager3);
                holder = new Pager3Holder(view);
                break;
            //加载更过
            case LOADMORE:
                view = CommonUtils.inflate(R.layout.home_pager4);
                holder = new Pager4Holder(view);
                break;
        }
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData(context, jsonList.get(position));
    }


}
