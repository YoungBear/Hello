package com.example.hello.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;

import com.example.hello.model.app.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearyang on 2016/12/28.
 */

public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    public static List<ResolveInfo> getAllApps(Context context) {
        List<ResolveInfo> appList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appList.addAll(pm.queryIntentActivities(mainIntent, 0));
        return appList;
    }

    public static boolean isSystemApp(Context context, String packageName) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, 0);
            return (info.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ResolveInfo> getSystemApps(Context context) {
        List<ResolveInfo> appList = new ArrayList<>();
        List<ResolveInfo> allAppList = getAllApps(context);
        for (ResolveInfo info : allAppList) {
            String packageName = info.activityInfo.applicationInfo.packageName;
            if (isSystemApp(context, packageName)) {
                appList.add(info);
            }
        }
        return appList;

    }

    public static boolean isThirdApp(Context context, String packageName) {
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName, 0);
            int appFlags = info.flags;
            Log.d(TAG, "packageName: " + packageName + ", appFlags: " + appFlags);
            return (appFlags & ApplicationInfo.FLAG_SYSTEM) == 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ResolveInfo> getThirdApps(Context context) {
        List<ResolveInfo> appList = new ArrayList<ResolveInfo>();
        List<ResolveInfo> allAppList = getAllApps(context);
        for (ResolveInfo info : allAppList) {
            String packageName = info.activityInfo.applicationInfo.packageName;
            if (isThirdApp(context, packageName)) {
                appList.add(info);
            }
        }
        return appList;
    }

    public static PackageInfo getPackageInfo(Context context, String packageName) {
        try {
            if (context == null) {
                return null;
            }
            return context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_ACTIVITIES);
        } catch (Exception e) {
            return null;
        }
    }

    public static AppInfo getAppInfo(Context context, String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = getPackageInfo(context, packageName);
            if (packageInfo != null) {
                AppInfo appInfo = new AppInfo();
                appInfo.packageName = packageInfo.packageName;
                appInfo.versionName = packageInfo.versionName;
                appInfo.versionCode = packageInfo.versionCode;
                appInfo.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                appInfo.icon = packageInfo.applicationInfo.loadIcon(pm);
                return appInfo;
            }
        }
        return null;
    }

}
