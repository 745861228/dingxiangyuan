package com.me.dingxiangyuan.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.CarouselfigureBean;
import com.me.dingxiangyuan.utils.LogUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.List;

/**
 * Created by qwe on 2016/12/29.
 */

public class Pager0Holder extends BaseHolder<String> {

    private final ViewPager viewPager;
    private final AutoLinearLayout linearLayout;

    public Pager0Holder(View itemView) {
        super(itemView);
        viewPager = (ViewPager) itemView.findViewById(R.id.home_pager0_viewpager);
       linearLayout = (AutoLinearLayout) itemView.findViewById(R.id.home_pager0_linearLayout);
    }

    @Override
    public void setData(final Context context, String s) {
        //解析json
        Gson gson = new Gson();
        CarouselfigureBean carouselfigureBean = gson.fromJson(s, CarouselfigureBean.class);
        final List<CarouselfigureBean.DataEntity> data = carouselfigureBean.getData();


        viewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
        viewPager.setOffscreenPageLimit(3);//>=3
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(context);
                Glide.with(context).load(data.get(position % data.size()).getImg()).into(imageView);
                LogUtils.d("url----------",data.get(position % data.size()).getImg());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击啦", Toast.LENGTH_SHORT).show();
                    }
                });
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));
        viewPager.setCurrentItem(1000*data.size());
        //初始化小圆点
          initDot(data,context);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    ImageView imageView = (ImageView) linearLayout.getChildAt(i);
                    if (i == position%data.size()) {
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

    /**
     * 初始化小圆点
     * @param data
     * @param context
     */
    private void initDot(List<CarouselfigureBean.DataEntity> data, Context context) {
        if (linearLayout.getChildCount()<=0){
            for (int i = 0; i < data.size(); i++) {
                ImageView imageView = new ImageView(context);
                if (i == 0) {
                    imageView.setImageResource(R.drawable.dot_focuse);
                } else {
                    imageView.setImageResource(R.drawable.dot_normal);
                }
                AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(5, 5);
                params.setMargins(5, 5, 5, 5);
                linearLayout.addView(imageView, params);
            }
        }
    }


}
