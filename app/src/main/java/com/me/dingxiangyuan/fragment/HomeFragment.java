package com.me.dingxiangyuan.fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.CarouselfigureBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.NetUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.List;

/**
 * author by LiKe on 2016/12/28.
 */

public class HomeFragment extends BaseFragment {

    private ViewPager lunBo_viewPager;
    private List<CarouselfigureBean.DataEntity> data;

    @Override
    protected void onLoad() {
        int netWorkType = NetUtils.getNetWorkType(getActivity());
        if (netWorkType == NetUtils.NETWORKTYPE_INVALID) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }/* else {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }*/
        HomeFragmentData data = new HomeFragmentData();
        data.getData("http://www.yulin520.com/a2a/broadcast/files?sign=7442C54B6DAFB81CEB01588164F3CCA8&ts=1482907765&pageSize=9&page=1", BaseData.NORMALTIME, null, 0);
    }

    @Override
    public View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_home);
        lunBo_viewPager = (ViewPager) view.findViewById(R.id.LunBo_viewPager);
        return view;
    }

    class HomeFragmentData extends BaseData {

        @Override
        public void setResultData(String response) {
            if (response != null) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                final CarouselfigureBean carouselfigureBean = gson.fromJson(response, CarouselfigureBean.class);
                // 在主线程中进行更新UI
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        initViewPagerData(carouselfigureBean);
                    }
                });
            } else {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }
    }

    /**
     * 设置轮播图
     *
     * @param carouselfigureBean
     */

    private void initViewPagerData(CarouselfigureBean carouselfigureBean) {
        data = carouselfigureBean.getData();
        lunBo_viewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
        lunBo_viewPager.setOffscreenPageLimit(3);//>=3
        lunBo_viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getActivity())
                        .load(data.get(position).getImg())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        lunBo_viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));
    }
}
