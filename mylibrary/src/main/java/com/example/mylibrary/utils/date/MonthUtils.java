package com.example.mylibrary.utils.date;

import java.util.Calendar;

/**
 * Created by ysx on 2017/9/23.
 */

public final class MonthUtils {
    private static final String TAG = "MonthUtils";

    private MonthUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param i 0表示当前月份，1表示下一个月份，-1表示上一个月份
     * @return 返回月份的int值 1~12
     */
    public static int getMonth(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, i);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @return 当前月份，从1~12
     */
    public static int getCurrentMonth() {
        return getMonth(0);
    }

    /**
     * @return 返回上个月的月份 1~12
     */
    public static int getLastMonth() {
        return getMonth(-1);
    }

    /**
     * @return 返回上两个月的月份 1~12
     */
    public static int getLastTwoMonth() {
        return getMonth(-2);
    }

    /**
     * @return 返回上三个月的月份 1~12
     */
    public static int getLastThreeMonth() {
        return getMonth(-3);
    }
}
