package com.me.dingxiangyuan.acitvity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.factory.FragmentFactory;
import com.me.dingxiangyuan.view.NoScrollViewPager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton home_rb;
    private RadioButton forum_rb;
    private RadioButton mine_rb;
    private NoScrollViewPager main_viewPager;
    private long mExitTime;
    public static int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initView();
    }

    private void initView() {
        main_viewPager = (NoScrollViewPager) findViewById(R.id.main_viewPager);
        home_rb = (RadioButton) findViewById(R.id.home_rb);
        forum_rb = (RadioButton) findViewById(R.id.forum_rb);
        mine_rb = (RadioButton) findViewById(R.id.mine_rb);

        home_rb.setOnClickListener(this);
        forum_rb.setOnClickListener(this);
        mine_rb.setOnClickListener(this);
        main_viewPager.setOffscreenPageLimit(2);
        //设置viewPager设配器
        main_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_rb:
                main_viewPager.setCurrentItem(0);
                break;
            case R.id.forum_rb:
                main_viewPager.setCurrentItem(1);
                break;
            case R.id.mine_rb:
                main_viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return true;
    }
}
