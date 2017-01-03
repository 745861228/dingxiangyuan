package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.AllJsonBean;
import com.me.dingxiangyuan.holder.BaseHolder;
import com.me.dingxiangyuan.holder.Pager5Holder;
import com.me.dingxiangyuan.holder.ViewHolderNormalAll;
import com.me.dingxiangyuan.holder.ViewHolderPicAll;
import com.me.dingxiangyuan.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/31.
 */

public class AllRvAdpater extends RecyclerView.Adapter<BaseHolder> {
    private Context context;
    private List<AllJsonBean.DataBean> alllist;
    private final int NORMAL = 0;
    private final int PIC = 1;
    private final int LAST_ITEM = 2;

    public AllRvAdpater(Context context, List<AllJsonBean.DataBean> alllist) {
        this.context = context;
        this.alllist = alllist;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == alllist.size()) {
            return LAST_ITEM;
        } else if (alllist.get(position).getImgs() == null) {
            return NORMAL;
        }
        return PIC;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        BaseHolder holder = null;
        switch (viewType) {
            case NORMAL:
                view = CommonUtils.inflate(R.layout.siftrv_layout_item_normal);
                holder = new ViewHolderNormalAll(view);
                break;

            case PIC:
                view = CommonUtils.inflate(R.layout.siftrv_laout_item_pic);
                holder = new ViewHolderPicAll(view);
                break;
            case LAST_ITEM:
                view = CommonUtils.inflate(R.layout.progress_item);

                LinearLayout.LayoutParams layoutParas=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT  );
                layoutParas.gravity = Gravity.CENTER_HORIZONTAL;
                view.setLayoutParams(layoutParas);
                holder = new Pager5Holder(view);
                break;

        }
        return holder;

    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (position < alllist.size()) {
            holder.setData(context, alllist.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return alllist.size() + 1;
    }
}
