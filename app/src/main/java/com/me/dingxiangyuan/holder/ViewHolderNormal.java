package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.SiftJsonBean;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/12/30.
 */

public class ViewHolderNormal extends BaseHolder<SiftJsonBean.DataBean> {

    private final TextView sift_title;
    private final TextView sift_content;
    private final TextView sift_name;
    private final TextView sift_createTime;
    private final TextView sift_replyTimes;
    private  SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    public ViewHolderNormal(View itemView) {
        super(itemView);
        sift_title = (TextView) itemView.findViewById(R.id.sift_title);
        sift_content = (TextView) itemView.findViewById(R.id.sift_content);
        sift_name = (TextView) itemView.findViewById(R.id.sift_name);
        sift_createTime = (TextView) itemView.findViewById(R.id.sift_createTime);
        sift_replyTimes = (TextView) itemView.findViewById(R.id.sift_replyTimes);

    }


    @Override
    public void setData(Context context, SiftJsonBean.DataBean dataBean) {
        if (!TextUtils.isEmpty(dataBean.getContent())) {
            sift_content.setVisibility(View.VISIBLE);
            sift_content.setText(dataBean.getContent());
            sift_name.setText(dataBean.getUserName());
            sift_title.setText(dataBean.getTitle());
            String t= format.format(dataBean.getCreateTime());
            sift_createTime.setText(t);
            sift_replyTimes.setText(dataBean.getReplyTimes()+"");
        } else {
            sift_content.setVisibility(View.GONE);
            sift_title.setText(dataBean.getTitle());
            sift_name.setText(dataBean.getUserName());
            String t = format.format(dataBean.getCreateTime());
            sift_createTime.setText(t);
            sift_replyTimes.setText(dataBean.getReplyTimes() + "");
        }
    }
}
