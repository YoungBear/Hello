package com.example.measuresdk.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by ysx on 2017/8/12.
 */

public class PermissionUtils {

    public static final int REQUEST_CODE = 0x111;

    /**
     * REQUEST_CODES和PERMISSIONS相对应
     * */
    public static final int[] REQUEST_CODES = {
            1,
            2,
            3
    };

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };



    public static boolean hasPermission(Context context, String permission) {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(context, permission);
    }

    public static void requestPermission(Activity activity, String permission) {
        ActivityCompat.requestPermissions(activity,
                new String[] {permission},
                REQUEST_CODE);
    }

    public static boolean hasPermissions(Context context) {
        for (String item : PERMISSIONS) {
            if (!hasPermission(context, item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 在Activity里判断并请求权限
     * 如果拒绝权限，则不会进入下一步，直接finish该Activity
     * */
    public static void requestPermissions(Activity activity) {
        for (String permission : PERMISSIONS) {
            if (!hasPermission(activity, permission)) {
                ActivityCompat.requestPermissions(activity,
                        new String[] {permission},
                        REQUEST_CODE);
            }
        }
    }
}
