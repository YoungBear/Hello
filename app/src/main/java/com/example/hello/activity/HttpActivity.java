package com.example.hello.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.sdk.LogUtils;
import com.example.sdk.http.Callback;
import com.example.sdk.http.HttpRequestManager;
import com.example.sdk.http.OkHttpStrategy;
import com.example.sdk.http.VolleyStrategy;

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
    @BindView(R.id.btn_volley)
    Button mBtnVolley;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    public static final String TEST_URL = "http://47.95.249.79:8080/test/tiananmen.json";

    private OkHttpStrategy mOkHttpStrategy;
    private VolleyStrategy mVolleyStrategy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);
        mTvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected void onDestroy() {
        HttpRequestManager.getInstance().cancelRequest(TAG);
        super.onDestroy();
    }

    @OnClick({R.id.btn_okhttp, R.id.btn_volley})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_okhttp:
                doOkhttpRequest(TEST_URL);
                break;
            case R.id.btn_volley:
                doVolleyRequest(TEST_URL);
                break;
            default:
                break;
        }
    }

    /**
     * 显示加载进度条，隐藏内容
     */
    private void showLoading(){
        mProgressBar.setVisibility(View.VISIBLE);
        mTvContent.setVisibility(View.GONE);
    }

    /**
     * 显示内容，隐藏加载进度条
     */
    private void dismissLoading() {
        mProgressBar.setVisibility(View.GONE);
        mTvContent.setVisibility(View.VISIBLE);
    }

    private void doOkhttpRequest(String url) {
        if (mOkHttpStrategy == null) {
            mOkHttpStrategy = new OkHttpStrategy(new Handler(Looper.getMainLooper()));
        }
        HttpRequestManager.getInstance().setHttpStrategy(mOkHttpStrategy);
        doHttpRequest(url);
    }

    private void doVolleyRequest(String url) {
        if (mVolleyStrategy == null) {
            mVolleyStrategy = new VolleyStrategy(this);
        }
        HttpRequestManager.getInstance().setHttpStrategy(mVolleyStrategy);
        doHttpRequest(url);
    }

    private void doHttpRequest(String url) {
        LogUtils.d(TAG, "doHttpRequest: url: " + url);
        showLoading();
        HttpRequestManager.getInstance().cancelRequest(TAG);
        HttpRequestManager.getInstance().httpStringGet(url, TAG, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.d(TAG, "onResponse: response: " + response);
                mTvContent.setText(response);
                dismissLoading();
            }

            @Override
            public void onFailure(String message) {
                LogUtils.d(TAG, "onFailure: message: " + message);
                dismissLoading();
                Toast.makeText(HttpActivity.this,
                        getString(R.string.network_failed), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
