package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.AllJsonBean;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.ViewHolderNormalAll;
import com.me.dingxiangyuan.holder.ViewHolderPicAll;
import com.me.dingxiangyuan.utils.CommonUtils;

/**
 * Created by Administrator on 2016/12/31.
 */

public class AllRvAdpater extends RecyclerView.Adapter<BaseHolder>{
    private  Context context;
    private  AllJsonBean alllist;
    private final int NORMAL = 0;
    private final int PIC = 1;
    public AllRvAdpater(Context context, AllJsonBean alllist) {
        this.context=context;
        this.alllist=alllist;
    }
    @Override
    public int getItemViewType(int position) {
        if(alllist.getData().get(position).getImgs().size() == 0){
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
                holder = new ViewHolderNormalAll(view);

                break;

            case PIC:
                view = CommonUtils.inflate(R.layout.siftrv_laout_item_pic);
                holder = new ViewHolderPicAll(view);
                break;
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData(context,alllist.getData().get(position));
    }

    @Override
    public int getItemCount() {
        return alllist.getData().size();
    }
}
