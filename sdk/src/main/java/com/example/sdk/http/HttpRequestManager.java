package com.example.sdk.http;

import android.os.Handler;
import android.os.Looper;

/**
 * @author ysx
 * @date 2017/12/13
 * @description
 */

public class HttpRequestManager {
    private static final String TAG = "HttpRequestManager";

    private HttpStrategy mHttpStrategy;
    private Handler mHandler;

    /**
     * 初始化动作
     *
     */
    public void init() {
        mHandler = new Handler(Looper.getMainLooper());
        mHttpStrategy = new OkHttpStrategy(mHandler);
    }

    public static HttpRequestManager getInstance() {
        return Holder.sInstance;
    }

    private HttpRequestManager() {

    }

    private static final class Holder {
        private static final HttpRequestManager sInstance = new HttpRequestManager();
    }

    public void httpStringGet(String url, Object tag, Callback<String> callback) {
        mHttpStrategy.httpStringGet(url, tag, callback);
    }

    public void cancelRequest(Object tag) {
        mHttpStrategy.cancelRequest(tag);
    }
}
