package com.example.sdk.http.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final Listener<T> listener;

    public GsonRequest(int method,
                       String url,
                       Class<T> clazz,
                       Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(method, url, clazz, null, null, listener, errorListener);
    }

    public GsonRequest(int method,
                       String url,
                       Class<T> clazz,
                       Map<String, String> headers,
                       Map<String, String> params,
                       Listener<T> listener,
                       Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        this.headers = headers;
        this.params = params;
    }

    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = null;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
