package com.example.sdk.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sdk.http.volley.GsonRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author ysx
 * @date 2017/12/14
 * @description
 */

public class VolleyStrategy implements HttpStrategy {

    private Context mContext;
    private RequestQueue mRequestQueue;


    public VolleyStrategy(Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    @Override
    public void httpStringGet(String url, Object tag, final Callback<String> callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.getMessage());
                    }
                }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed;
                try {
                    parsed = new String(response.data, HttpConstant.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        stringRequest.setShouldCache(false);
        stringRequest.setTag(tag);
        mRequestQueue.add(stringRequest);
    }

    @Override
    public <T> void httpGet(String url, Class<T> clazz, Object tag, Map<String, String> headers, final Callback<T> callback) {
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET, url, clazz, headers, null,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.getMessage());

                    }
                });
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(HttpConstant.MAX_TIME_OUT, HttpConstant.MAX_RETRIES_NUM, HttpConstant.BACKOFF_MULT));
        gsonRequest.setShouldCache(false);
        gsonRequest.setTag(tag);
        mRequestQueue.add(gsonRequest);
    }

    @Override
    public <T> void httpPost(String url, Class<T> clazz, Object tag, Map<String, String> headers, Map<String, String> params, final Callback<T> callback) {
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.POST, url, clazz, headers, params,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.getMessage());

                    }
                });
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(HttpConstant.MAX_TIME_OUT, HttpConstant.MAX_RETRIES_NUM, HttpConstant.BACKOFF_MULT));
        gsonRequest.setTag(tag);
        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void cancelRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
