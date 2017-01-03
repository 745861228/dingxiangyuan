package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.ColdWoreBean;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager3Holder extends BaseHolder<String>{

    private final ImageView coldWore_image;

    public Pager3Holder(View itemView) {
        super(itemView);
        coldWore_image = (ImageView) itemView.findViewById(R.id.coldWore_image);
        coldWore_image.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void setData(Context context, String s) {
        initDatas(context,s);
    }

    /**
     * 解析数据
     * @param context
     * @param s
     */
    private void initDatas(Context context, String s) {
        Gson gson = new Gson();
        ColdWoreBean coldWoreBean = gson.fromJson(s, ColdWoreBean.class);
        Glide.with(context).load(coldWoreBean.data.get(0).img).into(coldWore_image);
    }

}
