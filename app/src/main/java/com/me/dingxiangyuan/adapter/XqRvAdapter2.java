package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.XpJosnBean;

import java.text.SimpleDateFormat;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/1/7.
 */

public class XqRvAdapter2 extends RecyclerView.Adapter<XqRvAdapter2.MyViewHolder> {
    private Context context;
    private XpJosnBean listBean;
    private SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");


    public XqRvAdapter2(Context context, XpJosnBean listBean) {
        this.context=context;
        this.listBean=listBean;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.xq_layout_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(listBean.getData().get(position).getHeadImg()).into(holder.xq_recycler_image);
        holder.xq_recycler_text.setText(listBean.getData().get(position).getUserName());
        holder.xq_recycler_luo.setText(listBean.getData().get(position).getFloor());
        String t= format.format(listBean.getData().get(position).getCreateTime());
        holder.xq_recycler_tv.setText(t);
        holder.xq_recycler_context.setText(listBean.getData().get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return listBean.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView xq_recycler_image;
        private final TextView xq_recycler_text;
        private final TextView xq_recycler_luo;
        private final TextView xq_recycler_tv;
        private final TextView xq_recycler_context;


        public MyViewHolder(View itemView) {
            super(itemView);
            xq_recycler_image = (ImageView) itemView.findViewById(R.id.xq_recycler_image);
            xq_recycler_text = (TextView) itemView.findViewById(R.id.xq_recycler_text);
            xq_recycler_luo = (TextView) itemView.findViewById(R.id.xq_recycler_luo);
            xq_recycler_tv = (TextView) itemView.findViewById(R.id.xq_recycler_tv);
            xq_recycler_context = (TextView) itemView.findViewById(R.id.xq_recycler_context);
        }
    }
}
