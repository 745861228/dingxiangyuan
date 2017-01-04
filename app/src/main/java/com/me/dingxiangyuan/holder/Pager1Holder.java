package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.FestivalBean;

import static android.R.attr.data;
import static com.me.dingxiangyuan.R.id.imageView;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager1Holder extends BaseHolder<String> {

    private ImageView snowman_image;
    private TextView name_tv;
    private TextView date_tv;
    private TextView gift_tv;

    public Pager1Holder(View itemView) {
        super(itemView);
        //初始化控件
        initView(itemView);
    }

    /**
     * 初始化控件
     *
     * @param itemView
     */
    private void initView(View itemView) {
        snowman_image = (ImageView) itemView.findViewById(R.id.snowman_image);
        name_tv = (TextView) itemView.findViewById(R.id.name_tv);
        date_tv = (TextView) itemView.findViewById(R.id.date_tv);
        gift_tv = (TextView) itemView.findViewById(R.id.gift_tv);
    }

    @Override
    public void setData(Context context, String s) {
        initDatas(context, s);
    }

    /**
     * 解析数据
     *
     * @param context
     * @param s
     */
    private void initDatas(Context context, String s) {
      /*  Gson gson = new Gson();
        FestivalBean festivalBean = gson.fromJson(s, FestivalBean.class);
        Glide.with(context).load(festivalBean.data.img).into(snowman_image);
        name_tv.setText(festivalBean.data.name.substring(0, 3));
        date_tv.setText(festivalBean.data.name.substring(4));*/
    }
}
