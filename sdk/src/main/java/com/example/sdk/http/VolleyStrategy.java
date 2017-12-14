package com.example.sdk.http;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

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
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
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
    public void cancelRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
