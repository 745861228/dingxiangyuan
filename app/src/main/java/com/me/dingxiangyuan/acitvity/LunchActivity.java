package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.application.MyApplication;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.utils.CommonUtils;

import java.util.ArrayList;

public class LunchActivity extends BaseActivity {

    private ViewPager lunch_viewPager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //获取sp对象
                    if (isFirstLogin) {
                        jump();
                    } else {
                        CommonUtils.saveBolean("isFirstLogin", true);
                        intiView();
                    }
                    break;
            }
        }
    };
    private boolean isFirstLogin;
    private boolean isLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lunch);
        isFirstLogin = CommonUtils.getBoolean("isFirstLogin");
        if (isFirstLogin) {
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            handler.sendEmptyMessage(0);
        }

        isLand = CommonUtils.getBoolean("isLand");

        isLand = true;
    }

    /**
     * 初始化控件
     */
    private void intiView() {
        lunch_viewPager = (ViewPager) findViewById(R.id.lunch_viewPager);
        lunch_viewPager.setVisibility(View.VISIBLE);
        final ArrayList<Integer> picList = new ArrayList<>();
        picList.add(R.mipmap.introductory1_xhdpi);
        picList.add(R.mipmap.introductory2_xhdpi);
        picList.add(R.mipmap.introductory3_xhdpi);
        picList.add(R.mipmap.introductory4_xhdpi);

        lunch_viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return picList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView imageView = new ImageView(LunchActivity.this);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == picList.size() - 1) {
                            jump();
                        }
                    }
                });
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(picList.get(position));
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }


    //跳转界面
    public void jump() {
            if(isLand) {
                Intent intent = new Intent(LunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(LunchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }

    }
}
