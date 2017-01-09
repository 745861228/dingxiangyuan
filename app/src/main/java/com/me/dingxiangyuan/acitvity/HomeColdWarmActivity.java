package com.me.dingxiangyuan.acitvity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.me.dingxiangyuan.R;

import static com.me.dingxiangyuan.R.id.lordMoreRecyclerView;
import static com.me.dingxiangyuan.R.id.swipeRefreshLayout;

public class HomeColdWarmActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView lordMoreRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cold_warm);

        initView();
    }

    /**
     * 初始控件
     */
    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        lordMoreRecyclerView = (RecyclerView) findViewById(R.id.lordMoreRecyclerView);

        //设置swipeRefreshLayout进度条颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        //设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lordMoreRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
