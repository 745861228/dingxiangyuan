package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.CommunityBean;
import com.zhy.autolayout.AutoLinearLayout;



public class BoardContentHolder extends BaseHolder<CommunityBean.DataBean>{


    public final TextView title_tv;
    public final TextView content_tv;
    public final TextView broadcasting_tv;
    public final TextView comment_number_tv;
    public final TextView timer_tv;
    public final AutoLinearLayout view_group;
    public final ImageView go_to_comment_iv;

    public BoardContentHolder(View itemView) {
        super(itemView);
        title_tv = (TextView) itemView.findViewById(R.id.title_tv);
        content_tv = (TextView) itemView.findViewById(R.id.content_tv);
        broadcasting_tv = (TextView) itemView.findViewById(R.id.broadcasting_tv);
        timer_tv = (TextView) itemView.findViewById(R.id.timer_tv);
        comment_number_tv = (TextView) itemView.findViewById(R.id.comment_number_tv);
        view_group = (AutoLinearLayout) itemView.findViewById(R.id.view_group);
        go_to_comment_iv = (ImageView) itemView.findViewById(R.id.go_to_comment_iv);
    }

    @Override
    public void setData(Context context, CommunityBean.DataBean dataBean) {

    }


}
