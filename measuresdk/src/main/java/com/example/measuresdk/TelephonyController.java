package com.example.measuresdk;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by ysx on 2017/8/7.
 */

public class TelephonyController {

    private static final String TAG = "TelephonyController";

    private static volatile TelephonyController instance;

    private TelephonyManager mTelephonyManager;
    private Context mContext;

    private TelephonyController(Context context) {
        mContext = context.getApplicationContext();
        mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static TelephonyController getInstance(Context context) {
        if (null == instance) {
            synchronized (TelephonyController.class) {
                if (null == instance) {
                    instance = new TelephonyController(context);
                }
            }
        }
        return instance;
    }

    public TelephonyManager getTelephonyManager() {
        return mTelephonyManager;
    }


}
