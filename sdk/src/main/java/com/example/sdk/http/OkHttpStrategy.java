package com.example.sdk.http;

import android.os.Handler;

import com.example.sdk.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    public <T> void httpGet(String url, final Class<T> clazz, Object tag,
                            Map<String, String> headers, final Callback<T> callback) {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(Headers.of(headers));
        }
        final Request request = builder
                .url(url)
                .tag(tag)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                final T t = JsonUtils.parseJson(str, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(t);
                    }
                });

            }
        });
    }

    @Override
    public <T> void httpPost(String url, final Class<T> clazz, Object tag, Map<String, String> headers,
                             Map<String, String> params, final Callback<T> callback) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(Headers.of(headers));
        }
        final Request request = builder
                .url(url)
                .tag(tag)
                .post(formBodyBuilder.build())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                final T t = JsonUtils.parseJson(str, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(t);
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
