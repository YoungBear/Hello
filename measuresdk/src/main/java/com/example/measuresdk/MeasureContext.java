package com.example.measuresdk;

import android.Manifest;
import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.measuresdk.core.MeasureNeighboringCellInfoManager;
import com.example.measuresdk.entity.MeasureCell;
import com.example.measuresdk.entity.MeasureCellCdma;
import com.example.measuresdk.entity.MeasureCellGsm;
import com.example.measuresdk.entity.MeasureCellLte;
import com.example.measuresdk.entity.MeasureReport;
import com.example.measuresdk.entity.PhoneInfo;

import java.util.List;

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

    /**
     * 请求权限相关
     * */
    public static final int REQUEST_CODE = 0x100;

    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Context mContext;

    private boolean isInited;
    private boolean isRegistered;

    private TelephonyManager mTelephonyManager;
    private MyPhoneStateListener mPhoneStateListener;

    /**
     * 手机相关参数
     */
    private PhoneInfo mPhoneInfo;
    private CellLocation mCellLocation;
    private SignalStrength mSignalStrength;
    private MeasureCell.SignalStrengthResult mSignalStrengthResult;


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

    public void init(Context context) {

        setContext(context);

        // TODO: 2017/8/12 其他初始化工作

        init();


        isInited = true;

    }

    public void init() {
        if (mContext != null) {
            mPhoneInfo = new PhoneInfo(mContext);
            mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

            mPhoneStateListener = new MyPhoneStateListener(mContext);
            mPhoneStateListener.addCallBack(mCallBack);
        }
    }

    public void register() {
        mTelephonyManager.listen(mPhoneStateListener, MyPhoneStateListener.EVENTS);
        isRegistered = true;
    }

    private MyPhoneStateListener.CallBack mCallBack = new MyPhoneStateListener.CallBack() {

        @Override
        public void onCellLocationChanged(CellLocation location) {
            mCellLocation = location;
        }

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            mSignalStrength = signalStrength;
            mSignalStrengthResult = MeasureCell.signalStrengthParse(signalStrength);
        }

        @Override
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            Log.d(TAG, "onCellInfoChanged: cellInfo: " + cellInfo);
        }
    };

    public void unregister() {
        mPhoneStateListener.removeCallBack(mCallBack);
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
        isRegistered = false;

    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

    public PhoneInfo getPhoneInfo() {
        return mPhoneInfo;
    }

    public CellLocation getCellLocation() {
        return mCellLocation;
    }

    public void setCellLocation(CellLocation cellLocation) {
        mCellLocation = cellLocation;
    }

    public SignalStrength getSignalStrength() {
        return mSignalStrength;
    }

    public void setSignalStrength(SignalStrength signalStrength) {
        mSignalStrength = signalStrength;
    }

    public MeasureCell.SignalStrengthResult getSignalStrengthResult() {
        return mSignalStrengthResult;
    }

    /**
     * 获取测量报告
     */
    public MeasureReport getMeasureReport() {
        MeasureReport measureReport = new MeasureReport();
        TelephonyManager telephonyManager = (TelephonyManager)
                mContext.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
            if (cellInfoList == null || cellInfoList.size() == 0) {
                // 走通用线路
                MeasureCellGsm gsmCellInfo = new MeasureCellGsm();
                MeasureCellCdma cdmaCellInfo = new MeasureCellCdma();
                MeasureCellLte lteCellInfo = new MeasureCellLte();
                gsmCellInfo.setCellInfo();
                cdmaCellInfo.setCellInfo();
                lteCellInfo.setCellInfo();
                if (gsmCellInfo.isValid()) {
                    measureReport.getMeasureGsmCells().add(gsmCellInfo);
                }

                if (cdmaCellInfo.isValid()) {
                    measureReport.getMeasureCdmaCells().add(cdmaCellInfo);
                }

                if (lteCellInfo.isValid()) {
                    measureReport.getMeasureLteCells().add(lteCellInfo);
                }
            } else {
                // 处理主小区
                for (CellInfo cellInfo : cellInfoList) {
                    if (!cellInfo.isRegistered()) {
                        continue;
                    }

                    if (cellInfo instanceof CellInfoCdma) {
                        MeasureCellCdma cell = new MeasureCellCdma();
                        cell.setCellInfo(cellInfo);
                        measureReport.getMeasureCdmaCells().add(cell);
                        continue;
                    }

                    if (cellInfo instanceof CellInfoLte) {
                        MeasureCellLte cell = new MeasureCellLte();
                        cell.setCellInfo(cellInfo);
                        measureReport.getMeasureLteCells().add(cell);
                        continue;
                    }

                    if (cellInfo instanceof CellInfoGsm) {
                        MeasureCellGsm cell = new MeasureCellGsm();
                        cell.setCellInfo(cellInfo);
                        measureReport.getMeasureGsmCells().add(cell);
                    }
                }
            }

            measureReport
                    .setNeighboringCellInfos(MeasureNeighboringCellInfoManager
                            .getNeighboringCellInfos());
        } catch (Exception e) {
            Log.e(TAG, "Unable to obtain cell signal information", e);
        }

        return measureReport;
    }


    /**
     * getter and setter
     * */
    public boolean isInited() {
        return isInited;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    //    public void setInited(boolean inited) {
//        isInited = inited;
//    }

}
