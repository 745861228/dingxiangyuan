package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.me.dingxiangyuan.MainActivity;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoveCommunityBean;
import com.me.dingxiangyuan.fragment.LoveCommunityFragment;

import java.util.ArrayList;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager2Holder extends BaseHolder<String> {

    private final ViewPager cm_viewPager;
    private final LinearLayout cm_linearLayout;
    private final int ONEPAGER = 0;
    private final int TWOPAGER = 3;
    private final int THREEPAGER = 6;
    private final int FOURPAGER = 9;

    private ArrayList<LoveCommunityBean.DataBean> arrayList = new ArrayList<>();

    public Pager2Holder(View itemView) {
        super(itemView);
        cm_viewPager = (ViewPager) itemView.findViewById(R.id.cm_viewPager);
        cm_linearLayout = (LinearLayout) itemView.findViewById(R.id.cm_linearLayout);
    }

    @Override
    public void setData(Context context, String s) {
        //获取数据
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
        LoveCommunityBean loveCommunityBean = gson.fromJson(s, LoveCommunityBean.class);
        initDot(context, loveCommunityBean);
        initViewPager(context, loveCommunityBean);

    }

    /**
     * 创建viewpager适配器
     *
     * @param context
     * @param loveCommunityBean
     */
    private void initViewPager(Context context, final LoveCommunityBean loveCommunityBean) {
        cm_viewPager.setAdapter(new FragmentPagerAdapter(((MainActivity) context).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        arrayList.clear();
                        for (int i = 0; i < loveCommunityBean.data.size(); i++) {
                            if (i >= ONEPAGER && i < TWOPAGER) {
                                arrayList.add(loveCommunityBean.data.get(i));
                            }
                        }
                        fragment = LoveCommunityFragment.getFragment(arrayList);
                        break;

                    case 1:
                        arrayList.clear();
                        for (int i = 0; i < loveCommunityBean.data.size(); i++) {
                            if (i >= TWOPAGER && i < THREEPAGER) {
                                arrayList.add(loveCommunityBean.data.get(i));
                            }
                        }
                        fragment = LoveCommunityFragment.getFragment(arrayList);

                        break;

                    case 2:
                        arrayList.clear();
                        for (int i = 0; i < loveCommunityBean.data.size(); i++) {
                            if (i >= THREEPAGER && i < FOURPAGER) {
                                arrayList.add(loveCommunityBean.data.get(i));
                            }
                        }
                        fragment = LoveCommunityFragment.getFragment(arrayList);

                        break;

                    case 3:
                        arrayList.clear();
                        for (int i = 0; i < loveCommunityBean.data.size(); i++) {
                            if (i >= FOURPAGER) {
                                arrayList.add(loveCommunityBean.data.get(i));
                            }
                        }
                        fragment = LoveCommunityFragment.getFragment(arrayList);
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }


    private void initDot(Context context, LoveCommunityBean loveCommunityBean) {
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(context);
            if (i == 0) {
                imageView.setImageResource(R.drawable.dot_focuse);
            } else {
                imageView.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            params.setMargins(5, 0, 5, 0);
            cm_linearLayout.addView(imageView, params);
        }
    }

}
