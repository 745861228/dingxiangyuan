package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.WebViewActivity;
import com.me.dingxiangyuan.bean.ColdWoreBean;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager3Holder extends BaseHolder<String> implements View.OnClickListener {

    private final ImageView coldWore_image;
    private Context context;
    private ColdWoreBean coldWoreBean;

    public Pager3Holder(View itemView) {
        super(itemView);
        coldWore_image = (ImageView) itemView.findViewById(R.id.coldWore_image);
        coldWore_image.setScaleType(ImageView.ScaleType.FIT_XY);

        //设置监听事件
        coldWore_image.setOnClickListener(this);
    }

    @Override
    public void setData(Context context, String s) {
        this.context = context;
        initDatas(context,s);
    }

    /**
     * 解析数据
     * @param context
     * @param s
     */
    private void initDatas(Context context, String s) {
        Gson gson = new Gson();
        coldWoreBean = gson.fromJson(s, ColdWoreBean.class);
        Glide.with(context).load(coldWoreBean.data.get(0).img).into(coldWore_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coldWore_image:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("coldWoreBeanUrl",coldWoreBean.data.get(0).url);
                context.startActivity(intent);
                break;
        }
    }
}
