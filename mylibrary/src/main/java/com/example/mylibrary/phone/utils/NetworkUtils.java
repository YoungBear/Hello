package com.example.mylibrary.phone.utils;

/**
 * Created by ysx on 2017/8/8.
 */

public class NetworkUtils {

    private static final String[] NETWORK_TYPES = {
            "UNKNOWN", //0
            "GPRS", //1
            "EDGE", //2
            "UMTS", //3
            "CDMA", //4
            "EVDO", //5
            "EVDO", //6
            "1xRTT", //7
            "HSDPA", //8
            "HSUPA", //9
            "HSPA", //10
            "iDen", //11
            "EVDO", //12
            "LTE", //13
            "eHRPD", //14
            "HSPA+", //15
            "GSM", //16
            "TD_SCDMA", //17
            "IWLAN" //18
    };


    /**
     * 转换网络类型 在中国 联通3g为UMTS或者HSDPA, 电信的3g为EVDO,电信的2G为CMDA, 移动和联通的2g为GPRS或者EGDE
     *
     * @param networkType
     * @return
     */
    public static String getNetworkType(int networkType) {
        String networkTypeText;
        if (networkType >= 0 && networkType <= NETWORK_TYPES.length) {
            networkTypeText = NETWORK_TYPES[networkType];
        } else {
            networkTypeText = String.valueOf(networkType);
        }
        return networkTypeText;
    }
}
