package com.example.hello.model;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by bearyang on 2016/12/16.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate of MyApplication.");
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo
                    (getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("TEST_CHANNEL");
            Log.d(TAG, "channel: " + channel);
            Toast.makeText(this, "channel: " + channel, Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
