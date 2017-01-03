package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoveOxygenBean;

/**
 * author by LiKe on 2017/1/1.
 */

public class LoveOxygenItemRvHolder extends BaseHolder<LoveOxygenBean.DataBean> {

    private final ImageView indexImg;
    private final TextView title_tv;
    private final TextView reporterName_tv;
    private final TextView contentIntr_tv;

    public LoveOxygenItemRvHolder(View itemView) {
        super(itemView);
        indexImg = (ImageView) itemView.findViewById(R.id.indexImg);
        title_tv = (TextView) itemView.findViewById(R.id.title_tv);
        reporterName_tv = (TextView) itemView.findViewById(R.id.reporterName_tv);
        contentIntr_tv = (TextView) itemView.findViewById(R.id.contentIntr_tv);
    }

    @Override
    public void setData(Context context, LoveOxygenBean.DataBean dataBean) {
        Glide.with(context).load(dataBean.indexImg).into(indexImg);
        title_tv.setText(dataBean.title);
        reporterName_tv.setText(dataBean.reporterName);
        contentIntr_tv.setText(dataBean.contentIntr);
    }
}
