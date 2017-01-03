package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.SiftJsonBean;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.ViewHolderNormal;
import com.me.dingxiangyuan.holder.ViewHolderPic;
import com.me.dingxiangyuan.utils.CommonUtils;

/**
 * Created by Administrator on 2016/12/30.
 */

public class SiftRvAdapter extends RecyclerView.Adapter<BaseHolder> {
    private  Context context;
    private  SiftJsonBean list;
    private final int NORMAL = 0;
    private final int PIC = 1;

    public SiftRvAdapter(Context context, SiftJsonBean list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemViewType(int position) {
         if(list.getData().get(position).getImgs().size() == 0){
             return NORMAL;
         }

        return PIC;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        BaseHolder holder = null;
        switch (viewType){
            case NORMAL:
                view = CommonUtils.inflate(R.layout.siftrv_layout_item_normal);
                holder = new ViewHolderNormal(view);

                break;

            case PIC:
                view = CommonUtils.inflate(R.layout.siftrv_laout_item_pic);
                holder = new ViewHolderPic(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData(context,list.getData().get(position));
    }

    @Override
    public int getItemCount() {
        return list.getData().size();
    }
}
