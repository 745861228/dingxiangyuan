package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.MainActivity;
import com.me.dingxiangyuan.bean.LoveCommunityBean;
import com.me.dingxiangyuan.fragment.LoveCommunityFragment;

import java.util.ArrayList;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager2Holder extends BaseHolder<String> {

    private final ViewPager cm_viewPager;
    private final LinearLayout cm_linearLayout;

    private ArrayList<LoveCommunityBean.DataBean> arrayList0 = new ArrayList<>();
    private ArrayList<LoveCommunityBean.DataBean> arrayList1 = new ArrayList<>();
    private ArrayList<LoveCommunityBean.DataBean> arrayList2 = new ArrayList<>();
    private ArrayList<LoveCommunityBean.DataBean> arrayList3 = new ArrayList<>();

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
                        arrayList0.clear();
                        arrayList0.add(loveCommunityBean.data.get(0));
                        arrayList0.add(loveCommunityBean.data.get(1));
                        arrayList0.add(loveCommunityBean.data.get(2));
                        fragment = LoveCommunityFragment.getFragment(arrayList0);
                        break;

                    case 1:
                        arrayList1.clear();
                        arrayList1.add(loveCommunityBean.data.get(3));
                        arrayList1.add(loveCommunityBean.data.get(4));
                        arrayList1.add(loveCommunityBean.data.get(5));
                        fragment = LoveCommunityFragment.getFragment(arrayList1);

                        break;

                    case 2:
                        arrayList2.clear();
                        arrayList2.add(loveCommunityBean.data.get(6));
                        arrayList2.add(loveCommunityBean.data.get(7));
                        arrayList2.add(loveCommunityBean.data.get(8));
                        fragment = LoveCommunityFragment.getFragment(arrayList2);
                        break;

                    case 3:
                        arrayList3.clear();
                        arrayList3.add(loveCommunityBean.data.get(9));
                        arrayList3.add(loveCommunityBean.data.get(10));
                        arrayList3.add(loveCommunityBean.data.get(11));
                        fragment = LoveCommunityFragment.getFragment(arrayList3);
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        cm_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < cm_linearLayout.getChildCount(); i++) {
                    ImageView imageView = (ImageView) cm_linearLayout.getChildAt(i);
                    if (i == position) {
                        imageView.setImageResource(R.drawable.dot_focuse);
                    } else {
                        imageView.setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initDot(Context context, LoveCommunityBean loveCommunityBean) {
        if (cm_linearLayout.getChildCount()<=0){
            for (int i = 0; i < 4; i++) {
                ImageView imageView = new ImageView(context);
                if (i == 0) {
                    imageView.setImageResource(R.drawable.dot_focuse);
                } else {
                    imageView.setImageResource(R.drawable.dot_normal);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5, 5);
                params.setMargins(5, 0, 5, 0);
                cm_linearLayout.addView(imageView, params);
            }
        }
    }

}
