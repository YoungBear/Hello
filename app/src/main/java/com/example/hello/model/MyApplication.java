package com.example.hello.model;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.example.sdk.LogUtils;
import com.example.sdk.http.HttpRequestManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import net.gotev.uploadservice.UploadService;

/**
 * Created by bearyang on 2016/12/16.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate of MyApplication.");
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo
                    (getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("TEST_CHANNEL");
            LogUtils.d(TAG, "channel: " + channel);
            Toast.makeText(this, "channel: " + channel, Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        UploadService.NAMESPACE = "com.example.hello";
        initHttpRequestManager();

    }

    private void initHttpRequestManager() {
        HttpRequestManager.getInstance().init(getApplicationContext());
    }

}
