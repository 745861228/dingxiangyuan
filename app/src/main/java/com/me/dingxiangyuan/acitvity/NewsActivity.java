package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.MyNewsFragmentPagerAdapter;
import com.me.dingxiangyuan.newsFragment.DialogueFragment;
import com.me.dingxiangyuan.newsFragment.ReplyFragment;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImageView xiaoxi_image;
    private TextView dialogue;
    private TextView reply;
    private ViewPager newsviewPager;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //找控件
        initView();
    }

    /**
     * 找控件
     */
    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        newsviewPager = (ViewPager) findViewById(R.id.newsviewPager);
        //给控件设置点击控件
        newsviewPager.addOnPageChangeListener(this);
        xiaoxi_image = (ImageView) findViewById(R.id.xiaoxi_image);
        xiaoxi_image.setOnClickListener(this);
        //会话
        dialogue = (TextView) findViewById(R.id.dialogue);
        dialogue.setOnClickListener(this);
        //回复
        reply = (TextView) findViewById(R.id.reply);
        reply.setOnClickListener(this);
        //创建Fragment集合
        fragments = new ArrayList<>();
        fragments.add(new DialogueFragment());
        fragments.add(new ReplyFragment());
        //创建ViewPager设配器
        MyNewsFragmentPagerAdapter adapter=new MyNewsFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        newsviewPager.setAdapter(adapter);
        newsviewPager.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
                    switch (v.getId())
                    {
                        case  R.id.dialogue:
                            newsviewPager.setCurrentItem(0);
                            break;
                        case  R.id.reply:
                            newsviewPager.setCurrentItem(1);
                            break;
                        case  R.id.xiaoxi_image:
                            finish();
                            break;

                    }
    }
    /**
     * 滑动时监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    /**
     * 选中监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == position) {
                textView.setBackgroundResource(R.drawable.button_underline);
                textView.setTextColor(getResources().getColor(R.color.yellow));
            } else {
                textView.setBackground(null);
                textView.setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }
    /**
     * 状态改变监听
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
