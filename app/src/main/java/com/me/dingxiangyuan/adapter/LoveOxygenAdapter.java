package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoveOxygenBean;
import com.me.dingxiangyuan.holder.LoveOxygenHolder;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;

import java.util.ArrayList;

/**
 * author by LiKe on 2017/1/1.
 */

public class LoveOxygenAdapter extends RecyclerView.Adapter<LoveOxygenHolder> {

    private Context context;
    private ArrayList<ArrayList<LoveOxygenBean.DataBean>> arrayLists;

    public LoveOxygenAdapter(ArrayList<ArrayList<LoveOxygenBean.DataBean>> arrayLists, Context context) {
        this.arrayLists = arrayLists;
        this.context = context;
    }

    @Override
    public LoveOxygenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.loveoxygenrv_item);
       // View view = LayoutInflater.from(context).inflate(R.layout.loveoxygenrv_item,parent,false);
        LoveOxygenHolder loveOxygenHolder = new LoveOxygenHolder(view);
        return loveOxygenHolder;
    }

    @Override
    public void onBindViewHolder(LoveOxygenHolder holder, int position) {
        holder.setData(context,arrayLists.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayLists.size();
    }
}
