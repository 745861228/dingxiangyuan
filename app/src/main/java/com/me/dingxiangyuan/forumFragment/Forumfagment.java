package com.me.dingxiangyuan.forumFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.BanKuaiActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.bean.BoardBean;
import com.me.dingxiangyuan.utils.CommonUtils;

import com.zhy.autolayout.utils.AutoUtils;




public class Forumfagment extends Fragment implements View.OnClickListener {

    private View first_card;
    private View second_card;
    private View third_card;
    private View four_card;
    private View five_card;
    private TextView first_number;
    private TextView second_number;
    private TextView third_number;
    private TextView four_number;
    private TextView five_number;
    private Intent intent;
    private  String  path="http://www.yulin520.com/a2a/forum/counts?sign=E91E58CB6799199AB773905953675415&ts=1483358965";
    private BoardBean boardBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.forum_layout_item);
        initView(view);
        initData();
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        first_card = view.findViewById(R.id.first_card);
        second_card = view.findViewById(R.id.second_card);
        third_card = view.findViewById(R.id.third_card);
        four_card = view.findViewById(R.id.four_card);
        five_card = view.findViewById(R.id.five_card);
        first_number = (TextView) view.findViewById(R.id.first_number);
        second_number = (TextView) view.findViewById(R.id.second_number);
        third_number = (TextView) view.findViewById(R.id.third_number);
        four_number = (TextView) view.findViewById(R.id.four_number);
        five_number = (TextView) view.findViewById(R.id.five_number);

        first_card.setOnClickListener(this);
        second_card.setOnClickListener(this);
        third_card.setOnClickListener(this);
        four_card.setOnClickListener(this);
        five_card.setOnClickListener(this);
        autoView();
    }

    /**
     * 动态适配
     */
    private void autoView() {
        AutoUtils.auto(first_card);
        AutoUtils.auto(second_card);
        AutoUtils.auto(third_card);
        AutoUtils.auto(four_card);
        AutoUtils.auto(five_card);
    }

    /**
     * 请求数据
     */
    private void initData() {
        new BaseData() {

            @Override
            public void setResultData(String response) {
                boardBean = new Gson().fromJson(response, BoardBean.class);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        first_number.setText(boardBean.getData().getType1Counts() + "条帖子");
                        second_number.setText(boardBean.getData().getType2Counts() + "条帖子");
                        third_number.setText(boardBean.getData().getType3Counts() + "条帖子");
                        four_number.setText(boardBean.getData().getType4Counts() + "条帖子");
                        five_number.setText(boardBean.getData().getType5Counts() + "条帖子");
                    }
                });

            }
        }.getData(path,BaseData.NORMALTIME,"",0);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.first_card:
                jump(10);
                break;
            case R.id.second_card:
                jump(11);
                break;
            case R.id.third_card:
                jump(12);
                break;
            case R.id.four_card:
                jump(13);
                break;
            case R.id.five_card:
                jump(14);
                break;
        }
    }

    public void jump(int i) {
        if (intent == null) {
            intent = new Intent(getActivity(), BanKuaiActivity.class);
        }
        intent.putExtra("tag", i);
        startActivity(intent);
    }
}
