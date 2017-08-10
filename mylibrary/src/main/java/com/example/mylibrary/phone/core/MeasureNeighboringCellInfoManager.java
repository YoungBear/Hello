package com.example.mylibrary.phone.core;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;

import com.example.mylibrary.LogUtils;
import com.example.mylibrary.phone.MeasureContext;
import com.example.mylibrary.phone.entity.MeasureNeighboringCellInfo;
import com.example.mylibrary.phone.entity.MeasureNeighboringCellInfoCdma;
import com.example.mylibrary.phone.entity.MeasureNeighboringCellInfoLte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureNeighboringCellInfoManager {

    private static final String TAG = "MeasureNeighboringCellI";

    /**
     * 获取邻区信息
     */
    public static List<MeasureNeighboringCellInfo> getNeighboringCellInfos() {
        List<MeasureNeighboringCellInfo> result = getNeighboringCellInfoCase2();
        return result == null || result.size() == 0 ? getNeighboringCellInfoCase1()
                : result;
    }

    /**
     * 获取邻区信息 通过方法getNeighboringCellInfo
     */
    public static List<MeasureNeighboringCellInfo> getNeighboringCellInfoCase1() {
        List<MeasureNeighboringCellInfo> result = new ArrayList<MeasureNeighboringCellInfo>();
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        List<NeighboringCellInfo> neighborCells = telephonyManager
                .getNeighboringCellInfo();
        LogUtils.d(TAG, "neighborCells: " + neighborCells);
        if (neighborCells == null) {
            return result;
        }

        for (NeighboringCellInfo cellInfo : neighborCells) {
            MeasureNeighboringCellInfo neighboringCellInfo = new MeasureNeighboringCellInfo();
            neighboringCellInfo.initNeighboringCellInfo(cellInfo);
            result.add(neighboringCellInfo);
        }

        return result;
    }

    /**
     * 获取邻区信息 通过方法getAllCellInfo
     */
    public static List<MeasureNeighboringCellInfo> getNeighboringCellInfoCase2() {
        List<MeasureNeighboringCellInfo> neighborCells = new ArrayList<MeasureNeighboringCellInfo>();
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
        if (cellInfoList == null) {
            return neighborCells;
        }

        for (CellInfo cellInfo : cellInfoList) {
            if (cellInfo.isRegistered()) {
                continue;
            }

            MeasureNeighboringCellInfo neighboringCellInfo = null;
            if (cellInfo instanceof CellInfoGsm) {
                neighboringCellInfo = new MeasureNeighboringCellInfo();
            }

            if (cellInfo instanceof CellInfoCdma) {
                neighboringCellInfo = new MeasureNeighboringCellInfoCdma();
            }

            if (cellInfo instanceof CellInfoLte) {
                neighboringCellInfo = new MeasureNeighboringCellInfoLte();
            }

            if (neighboringCellInfo != null) {
                neighboringCellInfo.initCellInfo(cellInfo);
                neighborCells.add(neighboringCellInfo);
            }
        }

        return neighborCells;
    }
}
