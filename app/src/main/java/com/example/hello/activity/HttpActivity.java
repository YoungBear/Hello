package com.example.hello.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.sdk.LogUtils;
import com.example.sdk.http.Callback;
import com.example.sdk.http.HttpRequestManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ysx
 * @date 2017/12/13
 * @description 网络请求封装
 */
public class HttpActivity extends BaseActivity {
    private static final String TAG = "HttpActivity";

    @BindView(R.id.btn_okhttp)
    Button mBtnOkhttp;
    @BindView(R.id.tv_content)
    TextView mTvContent;

    public static final String TEST_URL = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
        mTvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @OnClick({R.id.btn_okhttp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_okhttp:
                doOkhttpRequest(TEST_URL);

                break;
            default:
                break;
        }
    }

    private void doOkhttpRequest(String url) {
        HttpRequestManager requestManager = HttpRequestManager.getInstance();
        requestManager.init();
        requestManager.httpStringGet(url, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.d(TAG, "onResponse: response: " + response);
                mTvContent.setText(response);
            }

            @Override
            public void onFailure(String message) {
                LogUtils.d(TAG, "onFailure: message: " + message);

            }
        });
    }
}
