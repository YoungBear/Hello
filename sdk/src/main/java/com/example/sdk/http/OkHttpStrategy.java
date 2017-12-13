package com.example.sdk.http;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author ysx
 * @date 2017/12/13
 * @description
 */

public class OkHttpStrategy implements Strategy {

    private Handler mHandler;

    public OkHttpStrategy(Handler handler) {
        mHandler = handler;
    }
    @Override
    public void httpStringGet(String url, final Callback<String> callback) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                /**
                 * response.body().string()也是网络访问，所以还需要在子程序中执行
                 */
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(result);
                    }
                });
            }
        });

    }
}
