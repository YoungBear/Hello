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

public class OkHttpStrategy implements HttpStrategy {

    private Handler mHandler;
    private OkHttpClient mOkHttpClient;

    public OkHttpStrategy(Handler handler) {
        mHandler = handler;
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void httpStringGet(String url, Object tag, final Callback<String> callback) {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .tag(tag)
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

    @Override
    public void cancelRequest(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}
