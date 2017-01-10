package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.HomeDialogActivity;
import com.me.dingxiangyuan.bean.FestivalBean;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager1Holder extends BaseHolder<String> implements View.OnClickListener {

    private ImageView snowman_image;
    private TextView name_tv;
    private TextView date_tv;
    private TextView gift_tv;
    private AutoRelativeLayout page1_relativeLayout;
    private FestivalBean festivalBean;
    private Context context;
    private TextView day_tv;

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
        page1_relativeLayout = (AutoRelativeLayout) itemView.findViewById(R.id.page1_relativeLayout);
        snowman_image = (ImageView) itemView.findViewById(R.id.snowman_image);
        name_tv = (TextView) itemView.findViewById(R.id.name_tv);
        date_tv = (TextView) itemView.findViewById(R.id.date_tv);
        gift_tv = (TextView) itemView.findViewById(R.id.gift_tv);


        page1_relativeLayout.setOnClickListener(this);
        gift_tv.setOnClickListener(this);

        day_tv = (TextView) itemView.findViewById(R.id.day_tv);
    }

    @Override
    public void setData(Context context, String s) {
        this.context = context;
        initDatas(context, s);
    }

    /**
     * 解析数据
     *
     * @param context
     * @param s
     */
    private void initDatas(Context context, String s) {
        Gson gson = new Gson();
        festivalBean = gson.fromJson(s, FestivalBean.class);
        Glide.with(context).load(festivalBean.data.img).into(snowman_image);
        name_tv.setText(festivalBean.data.name.substring(0, 3));
        date_tv.setText(festivalBean.data.name.substring(4));

        long festivalTime = festivalBean.data.festivalTime;
        long l = System.currentTimeMillis();
        long l1 = festivalTime - l;
        long l2 = l1 / 86400000L + 1;
        if (l2 == 1) {
            day_tv.setText("今");
        } else {
            day_tv.setText(l2 + "");
        }

    }

    @Override
    public void onClick(View v) {
        //点击跳转activity
        switch (v.getId()){
            case R.id.page1_relativeLayout:
                Intent intent = new Intent(context, HomeDialogActivity.class);
                intent.putExtra("holidayDetails",festivalBean.data.holidayDetails);
                context.startActivity(intent);
                break;
            case R.id.gift_tv:
                Intent intent2 = new Intent(context, HomeDialogActivity.class);
                intent2.putExtra("holidayDetails",festivalBean.data.holidayDetails);
                context.startActivity(intent2);
                break;
        }
    }
}
