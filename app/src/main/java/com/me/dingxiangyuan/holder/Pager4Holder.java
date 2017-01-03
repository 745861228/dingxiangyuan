package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.LoveOxygenAdapter;
import com.me.dingxiangyuan.bean.LoveOxygenBean;

import java.util.ArrayList;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager4Holder extends BaseHolder<String> implements View.OnClickListener {

    private final RecyclerView loveOxygen_rv;
    private final TextView loadMore_tv;
    private ArrayList<ArrayList<LoveOxygenBean.DataBean>> dataBeanLiat = new ArrayList<>();
    private ArrayList<LoveOxygenBean.DataBean> dataBeenListTop = new ArrayList<>();
    private ArrayList<LoveOxygenBean.DataBean> dataBeenListBottom = new ArrayList<>();
    private Context context;

    public Pager4Holder(View itemView) {
        super(itemView);
        loveOxygen_rv = (RecyclerView) itemView.findViewById(R.id.loveOxygen_rv);
        loadMore_tv = (TextView) itemView.findViewById(R.id.loadMore_tv);

        //设置点击事件
        loadMore_tv.setOnClickListener(this);
    }

    @Override
    public void setData(Context context, String s) {
        this.context = context;
        //解析数据
        initDatas(context, s);
    }

    /**
     * 获取数据
     *
     * @param context
     * @param s
     */
    private void initDatas(Context context, String s) {
        Gson gson = new Gson();
        LoveOxygenBean loveOxygenBean = gson.fromJson(s, LoveOxygenBean.class);
        for (int i = 0; i < loveOxygenBean.data.size(); i++) {
            if (i < 3) {
                dataBeenListTop.add(loveOxygenBean.data.get(i));
            } else {
                dataBeenListBottom.add(loveOxygenBean.data.get(i));
            }
        }
        dataBeanLiat.add(dataBeenListTop);
        dataBeanLiat.add(dataBeenListBottom);

        //设置recycler布局管理
        loveOxygen_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        loveOxygen_rv.setAdapter(new LoveOxygenAdapter(dataBeanLiat, context));
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //home页加载更多点击事件
            case R.id.loadMore_tv:
                Toast.makeText(context, "跳转页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
