package com.example.sdk.http;

/**
 * @author ysx
 * @date 2017/12/13
 * @description
 */

public interface Callback<T> {

    void onResponse(T response);

    void onFailure(String message);
}
