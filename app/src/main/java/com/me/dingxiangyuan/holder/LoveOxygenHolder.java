package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.dingxiangyuan.R;

import com.me.dingxiangyuan.acitvity.MainActivity;
import com.me.dingxiangyuan.acitvity.WebViewActivity;
import com.me.dingxiangyuan.adapter.LoveOxygenItemLvAdapter;
import com.me.dingxiangyuan.bean.LoveOxygenBean;
import com.me.dingxiangyuan.utils.LogUtils;
import com.me.dingxiangyuan.view.MyListView;

import java.util.ArrayList;


/**
 * author by LiKe on 2017/1/1.
 */

public class LoveOxygenHolder extends BaseHolder<ArrayList<LoveOxygenBean.DataBean>> {

    private final MyListView loveOxygen_item_lv;
    private final TextView startTime_tv;

    public LoveOxygenHolder(View itemView) {
        super(itemView);
        loveOxygen_item_lv = (MyListView) itemView.findViewById(R.id.loveOxygen_item_lv);
        startTime_tv = (TextView) itemView.findViewById(R.id.startTime_tv);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MainActivity.width, LinearLayout.LayoutParams.WRAP_CONTENT);
        loveOxygen_item_lv.setLayoutParams(params);
    }

    @Override
    public void setData(final Context context, final ArrayList<LoveOxygenBean.DataBean> arrayList) {

        loveOxygen_item_lv.setAdapter(new LoveOxygenItemLvAdapter(arrayList, context));
        for (int i = 0; i < arrayList.size(); i++) {
            if (!TextUtils.isEmpty(arrayList.get(i).startTime)) {
                startTime_tv.setText(arrayList.get(1).startTime.substring(5, 10));
            }
        }

        loveOxygen_item_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("dataBeanUrl",arrayList.get(position).url);
                context.startActivity(intent);
            }
        });
    }
}

