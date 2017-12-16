package com.example.sdk.http;

import java.util.Map;

/**
 * @author ysx
 * @date 2017/12/13
 * @description
 */

public interface HttpStrategy {
    /**
     * http请求，返回字符串，使用get方法
     * @param url
     * @param tag
     * @param callback
     */
    void httpStringGet(String url, Object tag, Callback<String> callback);

    /**
     * httpGet请求，带header
     * @param url
     * @param clazz
     * @param tag
     * @param headers
     * @param callback
     * @param <T>
     */
    <T> void httpGet(String url, Class<T> clazz, Object tag, Map<String, String> headers, Callback<T> callback);

    /**
     * httpPost请求，带header
     * @param url
     * @param clazz
     * @param tag
     * @param headers
     * @param params
     * @param callback
     * @param <T>
     */
    <T> void httpPost(String url, Class<T> clazz, Object tag, Map<String, String> headers,
                      Map<String, String> params, Callback<T> callback);

    /**
     * 根据标签，取消网络请求
     *
     * @param tag
     */
    void cancelRequest(Object tag);
}
