package com.example.sdk.utils;

import com.google.gson.Gson;

/**
 * @author ysx
 * @date 2017/12/16
 * @description
 */

public final class JsonUtils {

    /**
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
