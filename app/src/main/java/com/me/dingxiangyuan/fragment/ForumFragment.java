package com.me.dingxiangyuan.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.MyFragmentPagerAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.forumFragment.Allfragment;
import com.me.dingxiangyuan.forumFragment.Forumfagment;
import com.me.dingxiangyuan.forumFragment.Siftfragment;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.ShowingPage;

import java.util.ArrayList;

import static com.me.dingxiangyuan.R.id.forum_tv;

/**
 * author by LiKe on 2016/12/28.
 */

public class ForumFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public String data;
    private MyHomeData myHomeData;
    /**
     * ViewPager
     */
    private ViewPager viewPager;
    private TextView forum;
    private TextView sift;
    private TextView all;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    private LinearLayout title_linearLayout;

    @Override
    protected void onLoad() {
        myHomeData = new MyHomeData();
        ForumFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    public View createSuccessView() {
        View view = initView();
        return view;
    }
    /**
     * 初始化控件
     * @return
     */
    private View initView() {
        View inflate = CommonUtils.inflate(R.layout.forum_layout);
        viewPager = (ViewPager) inflate.findViewById(R.id.viewPager);
        title_linearLayout = (LinearLayout) inflate.findViewById(R.id.title_linearLayout);
        forum = (TextView) inflate.findViewById(forum_tv);
        sift = (TextView) inflate.findViewById(R.id.sift_tv);
        all = (TextView) inflate.findViewById(R.id.all_tv);
        //给控件设置点击控件
        viewPager.addOnPageChangeListener(this);
        forum.setOnClickListener(this);
        sift.setOnClickListener(this);
        all.setOnClickListener(this);
        //创建Fragment集合
        fragments = new ArrayList<>();
        fragments.add(new Forumfagment());
        fragments.add(new Siftfragment());
        fragments.add(new Allfragment());
        //创建ViewPager设配器
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        return inflate;
    }

    /**
     * 对控件设置点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forum_tv:
                viewPager.setCurrentItem(0);
                break;
            case R.id.sift_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.all_tv:
                viewPager.setCurrentItem(2);
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
        for (int i = 0; i < title_linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) title_linearLayout.getChildAt(i);
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
    /**
     * 请求网络
     */
    class MyHomeData extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            ForumFragment.this.data = data;
        }
    }
}
