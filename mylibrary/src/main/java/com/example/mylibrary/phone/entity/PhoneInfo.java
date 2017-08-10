package com.example.mylibrary.phone.entity;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.mylibrary.phone.utils.NetworkUtils;

/**
 * Created by ysx on 2017/8/8.
 * 手机设备信息
 *
 */

public class PhoneInfo {
    private String systemVersion;
    private String model;
    private String networkType;
    private String simOperator;
    private String simSerialNumber;
    private String IMEI;
    private String IMSI;
    // TODO: 2017/8/8 add location
//    private MRLocation location;

    public PhoneInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            systemVersion = android.os.Build.VERSION.RELEASE;
            model = android.os.Build.MODEL;
            networkType = NetworkUtils.getNetworkType(
                    telephonyManager.getNetworkType());
            simOperator = telephonyManager.getSimOperator();
            simSerialNumber = telephonyManager.getSimSerialNumber();
            IMEI = telephonyManager.getDeviceId();
            IMSI = telephonyManager.getSubscriberId();
        }
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        this.simSerialNumber = simSerialNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }
}
