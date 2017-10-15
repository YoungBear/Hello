package com.example.hello.common;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by youngbear on 2017/10/15.
 */

public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;

    private Gson mGson;

    private Class<T> mClass;

    public GsonRequest(String url, Class<T> clazz, Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mGson = new Gson();
        this.mClass = clazz;
        this.mListener = listener;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {

        try {
            String e = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(this.mGson.fromJson(e, this.mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }
}
