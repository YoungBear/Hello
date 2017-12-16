package com.example.sdk.http;

/**
 * @author ysx
 * @date 2017/12/14
 * @description
 */

public final class HttpConstant {

    public static final String UTF_8 = "utf-8";

    /**
     * 超时时间
     */
    public static final int MAX_TIME_OUT = 60 * 1000;
    /**
     * 最大重试次数
     */
    public static final int MAX_RETRIES_NUM = 3;
    /**
     * 超时时间乘积因子
     */
    public static final float BACKOFF_MULT = 1f;
}
