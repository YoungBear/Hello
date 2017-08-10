package com.example.hello.model;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import net.gotev.uploadservice.UploadService;

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

        // setup the broadcast action namespace string which will
        // be used to notify upload status.
        // Gradle automatically generates proper variable as below.
        // Or, you can define it manually.
        UploadService.NAMESPACE = "com.example.hello";

        // TODO: 2017/8/9 请求权限
//        // MeasureContext
//        MeasureContext measureContext = MeasureContext.getInstance();
//        measureContext.setContext(this);
//        measureContext.init();

    }
}
