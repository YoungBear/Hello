package com.example.mylibrary.phone;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/7.
 */

public class MyPhoneStateListener extends PhoneStateListener {

    private static final String TAG = "MyPhoneStateListener";

    public static final int EVENTS =
            PhoneStateListener.LISTEN_CELL_LOCATION
                    | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                    | PhoneStateListener.LISTEN_CELL_INFO;

    private Context mContext;

    private List<CallBack> mCallBackList = new ArrayList<>();

    public MyPhoneStateListener(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public void onCellLocationChanged(CellLocation location) {
        Log.d(TAG, "onCellLocationChanged: location: " + location);
        if (mCallBackList != null) {
            for (CallBack callBack : mCallBackList) {
                callBack.onCellLocationChanged(location);
            }
        }
        super.onCellLocationChanged(location);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        Log.d(TAG, "onSignalStrengthsChanged, signalStrength.toString(): " + signalStrength.toString());
        if (mCallBackList != null) {
            for (CallBack callBack : mCallBackList) {
                callBack.onSignalStrengthsChanged(signalStrength);
            }
        }
        super.onSignalStrengthsChanged(signalStrength);
    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        Log.d(TAG, "onCellInfoChanged: cellInfo: " + cellInfo);
        if (mCallBackList != null) {
            for (CallBack callBack : mCallBackList) {
                callBack.onCellInfoChanged(cellInfo);
            }
        }
        super.onCellInfoChanged(cellInfo);
    }

    public void addCallBack(CallBack callBack) {
        mCallBackList.add(callBack);
    }

    public void removeCallBack(CallBack callBack) {
        if (mCallBackList.contains(callBack)) {
            mCallBackList.remove(callBack);
        }
    }

    public interface CallBack {
        void onCellLocationChanged(CellLocation location);
        void onSignalStrengthsChanged(SignalStrength signalStrength);
        void onCellInfoChanged(List<CellInfo> cellInfo);
    }
}
