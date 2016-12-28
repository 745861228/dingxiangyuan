package com.me.dingxiangyuan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.factory.FragmentFactory;
import com.me.dingxiangyuan.view.NoScrollViewPager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton home_rb;
    private RadioButton forum_rb;
    private RadioButton mine_rb;
    private NoScrollViewPager main_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


}
