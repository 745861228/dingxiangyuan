package com.me.dingxiangyuan.factory;

import android.support.v4.app.Fragment;


import com.me.dingxiangyuan.fragment.ForumFragment;
import com.me.dingxiangyuan.fragment.HomeFragment;
import com.me.dingxiangyuan.fragment.MineFragment;

import java.util.HashMap;

/**
 * Created by LiKe on 2016/11/28.
 */
public class FragmentFactory {
    //创建集合
    private static HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();

    //创建静态方法
    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentHashMap.get(position);
        if (fragment != null) {
            return fragment;
        }

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;

            case 1:
                fragment = new ForumFragment();
                break;

            case 2:
                fragment = new MineFragment();
                break;
        }
        fragmentHashMap.put(position, fragment);
        return fragment;
    }
}
