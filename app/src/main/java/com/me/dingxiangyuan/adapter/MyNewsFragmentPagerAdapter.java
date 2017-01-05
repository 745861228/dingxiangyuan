package com.me.dingxiangyuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MyNewsFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> lists;
    public MyNewsFragmentPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {

        return lists.size();
    }
}
