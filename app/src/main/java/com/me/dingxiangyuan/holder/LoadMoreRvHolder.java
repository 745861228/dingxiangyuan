package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoadMoreBean;

/**
 * author by LiKe on 2017/1/5.
 */

public class LoadMoreRvHolder extends BaseHolder<LoadMoreBean.DataBean> {

    private final ImageView large_image;
    private final TextView title_tv;
    private final TextView click_tv;
    private final TextView replyTimes_tv;
    private final TextView star_tv;

    public LoadMoreRvHolder(View itemView) {
        super(itemView);
        large_image = (ImageView) itemView.findViewById(R.id.large_image);
        title_tv = (TextView) itemView.findViewById(R.id.title_tv);
        click_tv = (TextView) itemView.findViewById(R.id.click_tv);
        replyTimes_tv = (TextView) itemView.findViewById(R.id.replyTimes_tv);
        star_tv = (TextView) itemView.findViewById(R.id.star_tv);
    }

    @Override
    public void setData(Context context, LoadMoreBean.DataBean dataBean) {
        Glide.with(context).load(dataBean.indexImg).into(large_image);
        title_tv.setText(dataBean.title);
        click_tv.setText(dataBean.click+"阅读");
        replyTimes_tv.setText(dataBean.replyTimes+"评论");
        star_tv.setText(dataBean.star+"点赞");
    }
}
