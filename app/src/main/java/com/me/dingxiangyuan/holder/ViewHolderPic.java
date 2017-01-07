package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.SiftJsonBean;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/12/30.
 */

public class ViewHolderPic extends BaseHolder<SiftJsonBean.DataBean> {

    private final TextView sift1_title;
    private final TextView sift1_content;
    private final TextView sift1_name;
    private final TextView sift1_createTime;
    private final TextView sift1_replyTimes;
    private final ImageView sift1_image;
    private final ImageView sift1_image1;
    private final ImageView sift1_image2;
    private SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm:ss");

    public ViewHolderPic(View itemView) {
        super(itemView);
        sift1_title = (TextView) itemView.findViewById(R.id.sift1_title);
        sift1_content = (TextView) itemView.findViewById(R.id.sift1_content);
        sift1_name = (TextView) itemView.findViewById(R.id.sift1_name);
        sift1_createTime = (TextView) itemView.findViewById(R.id.sift1_createTime);
        sift1_replyTimes = (TextView) itemView.findViewById(R.id.sift1_replyTimes);
        sift1_image = (ImageView) itemView.findViewById(R.id.sift1_image);
        sift1_image1 = (ImageView) itemView.findViewById(R.id.sift1_image1);
        sift1_image2 = (ImageView) itemView.findViewById(R.id.sift1_image2);
    }

    @Override
    public void setData(Context context, SiftJsonBean.DataBean dataBean) {
        if (dataBean.getImgs().size() != 0 && dataBean.getImgs().size() > 2) {
            sift1_image.setVisibility(View.VISIBLE);
            sift1_image1.setVisibility(View.VISIBLE);
            sift1_image2.setVisibility(View.VISIBLE);
            Glide.with(context).load(dataBean.getImgs().get(0).getMiniImg()).into(sift1_image);
            Glide.with(context).load(dataBean.getImgs().get(1).getMiniImg()).into(sift1_image1);
            Glide.with(context).load(dataBean.getImgs().get(2).getMiniImg()).into(sift1_image2);


            sift1_title.setText(dataBean.getTitle());
            sift1_content.setText(dataBean.getContent());
            sift1_name.setText(dataBean.getUserName());
            String t = format.format(dataBean.getCreateTime());
            sift1_createTime.setText(t);
            sift1_replyTimes.setText(dataBean.getReplyTimes() + "");
        } else if (dataBean.getImgs().size() != 0 && dataBean.getImgs().size() < 3) {
            sift1_image.setVisibility(View.VISIBLE);
            sift1_image1.setVisibility(View.GONE);
            sift1_image2.setVisibility(View.GONE);
            Glide.with(context).load(dataBean.getImgs().get(0).getMiniImg()).into(sift1_image);
            sift1_title.setText(dataBean.getTitle());
            sift1_content.setText(dataBean.getContent());
            sift1_name.setText(dataBean.getUserName());
            String t = format.format(dataBean.getCreateTime());
            sift1_createTime.setText(t);
            sift1_replyTimes.setText(dataBean.getReplyTimes() + "");
        } else if (dataBean.getImgs().size() == 1) {
            sift1_image.setVisibility(View.GONE);
            Glide.with(context).load(dataBean.getImgs().get(0).getMiniImg()).into(sift1_image);
            sift1_image1.setVisibility(View.GONE);
            sift1_image2.setVisibility(View.GONE);
            sift1_title.setText(dataBean.getTitle());
            sift1_content.setText(dataBean.getContent());
            sift1_name.setText(dataBean.getUserName());
            String t = format.format(dataBean.getCreateTime());
            sift1_createTime.setText(t);
            sift1_replyTimes.setText(dataBean.getReplyTimes() + "");
        } else {
            sift1_image.setVisibility(View.GONE);
            sift1_image1.setVisibility(View.GONE);
            sift1_image2.setVisibility(View.GONE);
            sift1_title.setText(dataBean.getTitle());
            sift1_content.setText(dataBean.getContent());
            sift1_name.setText(dataBean.getUserName());
            String t = format.format(dataBean.getCreateTime());
            sift1_createTime.setText(t);
            sift1_replyTimes.setText(dataBean.getReplyTimes() + "");
        }
    }

}
