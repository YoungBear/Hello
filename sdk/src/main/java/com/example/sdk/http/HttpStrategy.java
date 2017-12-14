package com.example.sdk.http;

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
     * 根据标签，取消网络请求
     *
     * @param tag
     */
    void cancelRequest(Object tag);
}
