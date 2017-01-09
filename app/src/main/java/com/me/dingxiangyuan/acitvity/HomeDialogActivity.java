package com.me.dingxiangyuan.acitvity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;

public class HomeDialogActivity extends Activity {

    private WebView dialog_webView;
    private String holidayDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dialog);

        holidayDetails = getIntent().getStringExtra("holidayDetails");

        dialog_webView = (WebView) findViewById(R.id.dialog_webView);

        initDatas();
    }

    private void initDatas() {

        dialog_webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = dialog_webView.getSettings();
        //是否自动打开窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //是否识别jsp
        settings.setJavaScriptEnabled(true);
        //设置如何缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //是否展示缩放按钮
        settings.setBuiltInZoomControls(true);
        //设置默认的缩放比例
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);

        dialog_webView.loadUrl(holidayDetails);
    }
}
