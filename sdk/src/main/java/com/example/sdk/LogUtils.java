package com.example.sdk;

import android.util.Log;

/**
 * @author ysx
 * @date 2018/01/23
 * @description 日志管理类
 */
public final class LogUtils {

    /**
     * 打印日志级别，Log.VERBOSE表示打印所有日志
     */
    public static int LOG_LEVEL = Log.VERBOSE;

    public static void v(String tag, String msg) {
        if (Log.VERBOSE >= LOG_LEVEL) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (Log.VERBOSE >= LOG_LEVEL) {
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG >= LOG_LEVEL) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (Log.DEBUG >= LOG_LEVEL) {
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (Log.INFO >= LOG_LEVEL) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (Log.INFO >= LOG_LEVEL) {
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (Log.WARN >= LOG_LEVEL) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (Log.WARN >= LOG_LEVEL) {
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (Log.ERROR >= LOG_LEVEL) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (Log.ERROR >= LOG_LEVEL) {
            Log.e(tag, msg, tr);
        }
    }
}
