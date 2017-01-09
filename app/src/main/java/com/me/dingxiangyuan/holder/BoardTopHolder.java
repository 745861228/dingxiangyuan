package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.CommunityBean;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/4.
 */

public class BoardTopHolder extends BaseHolder<CommunityBean.DataBean> {

    public RecyclerView top_recycleView;

    public BoardTopHolder(View itemView) {
        super(itemView);
        top_recycleView = (RecyclerView) itemView.findViewById(R.id.top_recycleView);
    }

    @Override
    public void setData(Context context, CommunityBean.DataBean dataBean) {

    }
}
