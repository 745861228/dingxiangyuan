package com.me.dingxiangyuan.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoadMoreBean;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.LoadMoreHolder;
import com.me.dingxiangyuan.holder.LoadMoreRvHolder;
import com.me.dingxiangyuan.interfaces.OnItemClickListener;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.zhy.autolayout.utils.L;

import java.util.ArrayList;
import java.util.List;

import static com.me.dingxiangyuan.R.id.view;

/**
 * author by LiKe on 2017/1/5.
 */

public class LoadMoreRvAdapater extends RecyclerView.Adapter<BaseHolder> {

    private Context context;
    private List<LoadMoreBean.DataBean> arrayList;
    private final int NORMAL = 0;
    private final int LOADMORE = 1;
    private int lastPosition = -1;
    private OnItemClickListener onItemClickListener;


    public LoadMoreRvAdapater(List<LoadMoreBean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == arrayList.size()) {
            return LOADMORE;
        }
        return NORMAL;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        BaseHolder holder = null;
        switch (viewType) {
            case NORMAL:
                view = CommonUtils.inflate(R.layout.loadmore_rv_item);

                holder = new LoadMoreRvHolder(view);
                break;
            case LOADMORE:
                view = CommonUtils.inflate(R.layout.progress_item);
                holder = new LoadMoreHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        if (position < arrayList.size()) {
            LoadMoreRvHolder loadMoreHolder = (LoadMoreRvHolder) holder;
            loadMoreHolder.setData(context, arrayList.get(position));
            if (position>lastPosition){
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(loadMoreHolder.itemView, View.TRANSLATION_Y, 600, 200, 50, 0);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                lastPosition = position;
            }

            if (onItemClickListener!=null){
                loadMoreHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.setOnItemClickListener(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size() + 1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }
}
