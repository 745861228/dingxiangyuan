package com.me.dingxiangyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoveOxygenBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;

import java.util.ArrayList;


/**
 * author by LiKe on 2017/1/1.
 */

public class LoveOxygenItemLvAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LoveOxygenBean.DataBean> arrayList;

    public LoveOxygenItemLvAdapter(ArrayList<LoveOxygenBean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = CommonUtils.inflate(R.layout.loveoxygenrv_itrm_lv_item);
            // convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loveoxygenrv_itrm_lv_item, parent,false);//解决宽度不能铺满
            ImageView indexImg = (ImageView) convertView.findViewById(R.id.indexImg);
            TextView title_tv = (TextView) convertView.findViewById(R.id.title_tv);
            TextView reporterName_tv = (TextView) convertView.findViewById(R.id.reporterName_tv);
            TextView contentIntr_tv = (TextView) convertView.findViewById(R.id.contentIntr_tv);

            Glide.with(context).load(arrayList.get(position).indexImg).into(indexImg);
            title_tv.setText(arrayList.get(position).title);
            reporterName_tv.setText("作者: " + arrayList.get(position).reporterName);
            String subString = "";
            if (arrayList.get(position).contentIntr.length() > 20) {
                subString = arrayList.get(position).contentIntr.substring(0, 20) + "...";
            }
            contentIntr_tv.setText(subString);
        }
        return convertView;
    }
}
