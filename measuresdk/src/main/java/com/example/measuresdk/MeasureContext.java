package com.example.measuresdk;

import android.Manifest;
import android.content.Context;

/**
 * Created by ysx on 2017/8/12.
 * <p>
 * 在某一个Activity初始化该单例类
 * first: getInstance()
 * second: init()
 */

public class MeasureContext {
    private static final String TAG = "MeasureContext";

    private static volatile MeasureContext instance;

    private Context mContext;

    private boolean isInited;



    /**
     * 请求权限相关
     * */
    public static final int REQUEST_CODE = 0x100;

    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private MeasureContext() {
    }

    public static MeasureContext getInstance() {
        if (null == instance) {
            synchronized (MeasureContext.class) {
                if (null == instance) {
                    instance = new MeasureContext();
                }
            }
        }
        return instance;
    }

//    public void requestPermission(Activity activity) {
//        //动态请求权限
//        if (PermissionUtils.isLackPermissions(activity)) {
//            PermissionUtils.requestPermissions(activity);
//        } else {
//            init(activity);
//        }
//
//    }

    public void init(Context context) {

        setContext(context);

        // TODO: 2017/8/12 其他初始化工作

        isInited = true;

    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }


    /**
     * getter and setter
     * */
    public boolean isInited() {
        return isInited;
    }

//    public void setInited(boolean inited) {
//        isInited = inited;
//    }

}
