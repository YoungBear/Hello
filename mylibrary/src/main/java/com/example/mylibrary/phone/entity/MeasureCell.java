package com.example.mylibrary.phone.entity;

import android.telephony.CellInfo;
import android.telephony.SignalStrength;
import android.util.Log;

import com.example.mylibrary.phone.MeasureContext;
import com.example.mylibrary.phone.common.ParamField;
import com.example.mylibrary.phone.constant.CellConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/8.
 */

public abstract class MeasureCell {

    private static final String TAG = "MeasureCell";

    protected boolean isValid = false;

    protected String mcc;
    protected String mnc;
    protected int lac;
    protected int cid;
    protected int signalStrength;
    protected long timeStamp;

    public abstract void setCellInfo(CellInfo cellInfo);
    public abstract void setCellInfo();

    public boolean isValid() {
        return isValid;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public int getLac() {
        return lac;
    }

    public int getCid() {
        return cid;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public static SignalStrengthResult signalStrengthParse(SignalStrength signalStrength) {
        SignalStrengthResult result = new SignalStrengthResult();
        PhoneInfo phoneInfo = MeasureContext.getInstance().getPhoneInfo();

        String networkType = phoneInfo.getNetworkType();
        Log.d(TAG, "networkType: " + networkType);

        Class<?> classA = signalStrength.getClass();
        try {
            Method getRsrp = classA.getDeclaredMethod(CellConstant.METHOD_GET_LTE_RSRP);
            Method getRsrq = classA.getDeclaredMethod(CellConstant.METHOD_GET_LTE_RSRQ);
            Method getRssnr = classA.getDeclaredMethod(CellConstant.METHOD_GET_LTE_RSSNR);
            Method getCqi = classA.getDeclaredMethod(CellConstant.METHOD_GET_LTE_CQI);
            Method getSignalStrength = classA.getDeclaredMethod(CellConstant.METHOD_GET_LTE_SIGNAL_STRENGTH);

            // 设置可access
            getRsrp.setAccessible(true);
            getRsrq.setAccessible(true);
            getRssnr.setAccessible(true);
            getCqi.setAccessible(true);
            getSignalStrength.setAccessible(true);

            result.rsrp = (int) getRsrp.invoke(signalStrength);
            result.rsrq = (int) getRsrq.invoke(signalStrength);
            result.rssnr = (int) getRssnr.invoke(signalStrength);
            result.cqi = (int) getCqi.invoke(signalStrength);
            result.signalStrength = (int) getSignalStrength.invoke(signalStrength);

            Log.d(TAG, "signalStrengthParse: result.rsrp: " + result.rsrp
                    + ", result.rsrq: " + result.rsrq
                    + ", result.rssnr: " + result.rssnr
                    + ", result.cqi: " + result.cqi
                    + ", result.signalStrength: " + result.signalStrength);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        String model = phoneInfo.getModel();
        Log.d(TAG, "signalStrengthParse: model: " + model);
        String s = signalStrength.toString();
        Log.d(TAG, "signalStrength.toString(): " + s);
        String[] datas = s.split(" ");
        Log.d(TAG, "datas.length: " + datas.length);
//        if (datas.length >= 15) {
//            Log.d(TAG, "Rsrp: " + datas[9] + ", Rsrq: " + datas[10] + ", Rssnr: " + datas[11]);
//        }



        return result;
    }

    /**
     * 已集合形式返回对象数据
     *
     * @return 对象数据
     */
    public List<ParamField> ToPropertyList() {
        List<ParamField> fieldList = new ArrayList<ParamField>();
        fieldList.add(new ParamField("MCC", this.getMcc() + ""));
        fieldList.add(new ParamField("MNC", this.getMnc() + ""));

        return fieldList;
    }

    public static class SignalStrengthResult {
        public int rsrp = -1;
        public int rsrq = -1;
        public int rssnr = -1;
        public int cqi = -1;
        public int signalStrength = -1;

        @Override
        public String toString() {
            return "SignalStrengthResult{" +
                    "rsrp=" + rsrp +
                    ", rsrq=" + rsrq +
                    ", rssnr=" + rssnr +
                    ", cqi=" + cqi +
                    ", signalStrength=" + signalStrength +
                    '}';
        }
    }


}
