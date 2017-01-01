package com.me.dingxiangyuan.fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.HomeFragmentRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.CarouselfigureBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;
import com.me.dingxiangyuan.utils.NetUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import static com.me.dingxiangyuan.R.id.home_fm_sv;

/**
 * author by LiKe on 2016/12/28.
 */

public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener {

    private List<CarouselfigureBean.DataEntity> data;
    private RecyclerView recyclerView;
    private String[] url = new String[]{UrlUtils.CarouselUrl,UrlUtils.ZhuJiao, UrlUtils.LoveUrl};
    private List<String> jsonList = new ArrayList<>();
    private SpringView springView;
    private View view;


    @Override
    protected void onLoad() {
        int netWorkType = NetUtils.getNetWorkType(getActivity());
        HomeFragmentData data = new HomeFragmentData();
        if (netWorkType == NetUtils.NETWORKTYPE_INVALID) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        } else {
            for (int i = 0; i < url.length; i++) {
                data.getData(url[i], BaseData.NORMALTIME, null, 0);
            }
        }

    }

    @Override
    public View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_home);
        initView();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        springView = (SpringView) view.findViewById(home_fm_sv);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_fm_rv);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(this);
        springView.setHeader(new DefaultHeader(getActivity()));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

    class HomeFragmentData extends BaseData {

        @Override
        public void setResultData(String response) {
            if (response != null) {
                jsonList.add(response);
            } else {
                return;
            }
            LogUtils.d("zzzzzzzzzzzzzz", jsonList.size() + "--------------------");
            if (jsonList.size() == url.length) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置recyclerView适配器和布局管理器
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(new HomeFragmentRvAdapter(getActivity(),jsonList));
                    }
                });
            }
        }
    }

    /**
     * 设置轮播图
     *
     * @param
     */

//    private void initViewPagerData(CarouselfigureBean carouselfigureBean) {
//        lunBo_viewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
//        lunBo_viewPager.setOffscreenPageLimit(3);//>=3
//        lunBo_viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return data.size();
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                ImageView imageView = new ImageView(getActivity());
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                Glide.with(getActivity())
//                        .load(data.get(position).getImg())
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
//                        .into(imageView);
//                container.addView(imageView);
//                return imageView;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View) object);
//            }
//        });
//        lunBo_viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));
//    }
    @Override
    public void onStop() {
        super.onStop();
        jsonList.clear();
    }
}
