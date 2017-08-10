package com.example.mylibrary.phone.entity;

import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import com.example.mylibrary.phone.MeasureContext;
import com.example.mylibrary.phone.common.ParamField;

import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureCellGsm extends MeasureCell {


    private int psc = -1;

    public int getPsc() {
        return psc;
    }


    @Override
    public void setCellInfo(CellInfo cellInfo) {
        if (cellInfo == null) {
            return;
        }

        if (!(cellInfo instanceof CellInfoGsm)) {
            return;
        }

        android.telephony.CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
        CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
        if (cellIdentityGsm == null) {
            return;
        }

        this.isValid = true;
        this.mcc = String.valueOf(cellIdentityGsm.getMcc());
        this.mnc = "0" + String.valueOf(cellIdentityGsm.getMnc());
        this.lac = cellIdentityGsm.getLac();
        this.cid = cellIdentityGsm.getCid();
        this.psc = cellIdentityGsm.getPsc();
        this.timeStamp = System.currentTimeMillis();
        CellSignalStrengthGsm signalStrength = cellInfoGsm.getCellSignalStrength();
        if (signalStrength != null) {
            this.signalStrength = signalStrength.getDbm();
        }
    }

    @Override
    public void setCellInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        CellLocation location = telephonyManager.getCellLocation();

        if (telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE) {
            return;
        }

        if (location instanceof GsmCellLocation) {// GSM网络
            this.isValid = true;
            GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
            this.lac = gsmCellLocation.getLac();
            this.cid = gsmCellLocation.getCid();
            String mccMnc = telephonyManager.getNetworkOperator();
            if (mccMnc != null && mccMnc.length() >= 5) {
                this.mcc = mccMnc.substring(0, 3);
                this.mnc = mccMnc.substring(3, 5);
            }

            if (MeasureContext.getInstance().getSignalStrength() != null) {
                this.signalStrength = -133 + 2 * MeasureContext.getInstance().getSignalStrength()
                        .getGsmSignalStrength();
            }

            this.timeStamp = System.currentTimeMillis();
        }
    }


    @Override
    public List<ParamField> ToPropertyList() {
        List<ParamField> fieldList = super.ToPropertyList();
        fieldList.add(new ParamField("LAC", this.getLac() + ""));
        fieldList.add(new ParamField("Cid", this.getCid() + ""));
        fieldList.add(new ParamField("SignalStrength", this.getSignalStrength() + ""));
        fieldList.add(new ParamField("", ""));

        return fieldList;
    }

}
