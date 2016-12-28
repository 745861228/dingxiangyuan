package com.me.dingxiangyuan.base;

import android.os.Bundle;
import android.view.Window;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * author by LiKe on 2016/12/28.
 */

public class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
