package com.example.hello.util;

import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * Created by bearyang on 2016/12/16.
 */

public class SystemInfoUtils {

    public static String getSystemProperty(String key) {
        Class<?> classType = null;
        Method getMethod = null;
        String ret = null;
        try {
            if (classType == null) {
                classType = Class.forName("android.os.SystemProperties");
                getMethod = classType.getDeclaredMethod("get", String.class);
            }
            ret = (String) getMethod.invoke(classType, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getSystemProperty(String key, String def) {
        if (TextUtils.isEmpty(getSystemProperty(key))) {
            return def;
        } else {
            return getSystemProperty(key);
        }
    }
}
