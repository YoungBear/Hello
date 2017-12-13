package com.example.sdk.http;

/**
 * @author ysx
 * @date 2017/12/13
 * @description
 */

public interface Strategy {
    void httpStringGet(String url, final Callback<String> callback);
}
