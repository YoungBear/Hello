package com.example.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hello.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends Activity {

    private static final String URL = "http://www.baidu.com";

    @Bind(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mWebView.loadUrl(URL);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
