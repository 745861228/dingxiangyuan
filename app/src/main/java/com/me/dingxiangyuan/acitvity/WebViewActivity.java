package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.me.dingxiangyuan.R;

import static com.me.dingxiangyuan.R.id.dialog_webView;
import static com.me.dingxiangyuan.R.id.icon;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String coldWoreBeanUrl;
    private String dataBeanUrl;
    private String dataBeanArrayListUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //获取数据
        Intent intent = getIntent();
        coldWoreBeanUrl = intent.getStringExtra("coldWoreBeanUrl");
        dataBeanUrl = intent.getStringExtra("dataBeanUrl");
        dataBeanArrayListUrl = intent.getStringExtra("dataBeanArrayListUrl");
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
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

        if (!TextUtils.isEmpty(coldWoreBeanUrl)){
            webView.loadUrl(coldWoreBeanUrl);
        }else if (!TextUtils.isEmpty(dataBeanUrl)){
            webView.loadUrl(dataBeanUrl);
        }else if(!TextUtils.isEmpty(dataBeanArrayListUrl)){
            webView.loadUrl(dataBeanArrayListUrl);
        }
    }
}
