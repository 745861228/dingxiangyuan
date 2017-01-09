package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.HomeLoveParticularsBean;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.HomeParticularsHolder;
import com.me.dingxiangyuan.holder.LoadMoreHolder;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;

import java.util.List;

/**
 * author by LiKe on 2017/1/7.
 */

public class HomeParticularsAdapater extends RecyclerView.Adapter<BaseHolder> {

    private Context context;
    private List<HomeLoveParticularsBean.DataBean> dataBeanList;
    private final int NORMAL = 0;
    private final int LOADMORE = 1;


    public HomeParticularsAdapater(Context context, List<HomeLoveParticularsBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }


    @Override
    public int getItemViewType(int position) {
        if (position<dataBeanList.size()){
            return NORMAL;
        }
        return LOADMORE;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        BaseHolder holder = null;
        switch (viewType){
            case NORMAL:
                view = CommonUtils.inflate(R.layout.xq_layout_item);
                holder = new HomeParticularsHolder(view);
                break;

            case LOADMORE:
                view = CommonUtils.inflate(R.layout.progress_loadmore_item);
                holder = new LoadMoreHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (position<dataBeanList.size()){
            holder.setData(context,dataBeanList.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return dataBeanList.size()+1;
    }
}
