package com.example.measuresdk;

import android.app.Activity;
import android.content.Context;

import com.example.measuresdk.utils.PermissionUtils;

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

    public void requestPermission(Activity activity) {
        //动态请求权限
        PermissionUtils.requestPermissions(activity);
    }

    public void init(Activity activity) {

        setContext(activity);

        // TODO: 2017/8/12 其他初始化工作

    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

}
