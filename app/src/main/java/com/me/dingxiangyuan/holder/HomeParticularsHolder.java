package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.HomeLoveParticularsBean;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author by LiKe on 2017/1/7.
 */

public class HomeParticularsHolder extends BaseHolder<HomeLoveParticularsBean.DataBean> {

    private final CircleImageView xq_recycler_image;
    private final TextView xq_recycler_text;
    private final TextView xq_recycler_luo;
    private final TextView xq_recycler_tv;
    private final TextView xq_recycler_context;

    public HomeParticularsHolder(View itemView) {
        super(itemView);
        xq_recycler_image = (CircleImageView) itemView.findViewById(R.id.xq_recycler_image);
        xq_recycler_text = (TextView) itemView.findViewById(R.id.xq_recycler_text);
        xq_recycler_luo = (TextView) itemView.findViewById(R.id.xq_recycler_luo);
        xq_recycler_tv = (TextView) itemView.findViewById(R.id.xq_recycler_tv);
        xq_recycler_context = (TextView) itemView.findViewById(R.id.xq_recycler_context);
    }

    @Override
    public void setData(Context context, HomeLoveParticularsBean.DataBean dataBean) {
        Glide.with(context).load(dataBean.headImg).into(xq_recycler_image);
        xq_recycler_text.setText(dataBean.userName);
        xq_recycler_luo.setText(dataBean.floor);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");
        String createTime = format.format(dataBean.createTime);
        xq_recycler_tv.setText(createTime);
        xq_recycler_context.setText(dataBean.content);
    }

}
