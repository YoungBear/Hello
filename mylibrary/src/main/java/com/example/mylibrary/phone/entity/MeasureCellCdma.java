package com.example.mylibrary.phone.entity;

import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;

import com.example.mylibrary.phone.MeasureContext;
import com.example.mylibrary.phone.common.ParamField;

import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureCellCdma extends MeasureCell {

    private int baseStationId = 0;

    private int networkId = 0;

    private int systemId = 0;

    private int evdoRSSI = 0;

    private int cdmaRSSI = 0;

    private int evdoEcio = 0;

    private int evdoLevel = 0;

    private int evdoSnr = 0;

    private int cdmaEcio = 0;

    private int cdmaLevel = 0;

    public int getBaseStationId() {
        return baseStationId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public int getSystemId() {
        return systemId;
    }

    public int getEvdoRSSI() {
        return evdoRSSI;
    }

    public int getCdmaRSSI() {
        return cdmaRSSI;
    }

    @Override
    public void setCellInfo(CellInfo cellInfo) {
        if (cellInfo == null) {
            return;
        }

        if (!(cellInfo instanceof android.telephony.CellInfoCdma)) {
            return;
        }

        this.isValid = true;
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String mccMnc = telephonyManager.getNetworkOperator();
        if (mccMnc != null && mccMnc.length() >= 5) {
            this.mcc = mccMnc.substring(0, 3);
            this.mnc = mccMnc.substring(3, 5);
        }

        android.telephony.CellInfoCdma cellInfoCdma = (android.telephony.CellInfoCdma) cellInfo;
        CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
        this.baseStationId = cellIdentityCdma.getBasestationId();
        this.networkId = cellIdentityCdma.getNetworkId();
        this.systemId = cellIdentityCdma.getSystemId();
        this.timeStamp = System.currentTimeMillis();

        CellSignalStrengthCdma signalStrength = cellInfoCdma
                .getCellSignalStrength();
        if (signalStrength != null) {
            this.evdoRSSI = signalStrength.getEvdoDbm();
            this.evdoEcio = signalStrength.getEvdoEcio();
            this.evdoLevel = signalStrength.getEvdoLevel();
            this.evdoSnr = signalStrength.getEvdoSnr();
            this.cdmaRSSI = signalStrength.getEvdoDbm();
            this.cdmaEcio = signalStrength.getCdmaEcio();
            this.cdmaLevel = signalStrength.getCdmaLevel();
        }
    }

    @Override
    public void setCellInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        CellLocation location = telephonyManager.getCellLocation();
        if (location instanceof CdmaCellLocation) {// 其他CDMA等网络
            this.isValid = true;
            CdmaCellLocation cdma = (CdmaCellLocation) location;
            this.baseStationId = cdma.getBaseStationId() >= 0 ? cdma
                    .getBaseStationId() : this.baseStationId;
            this.networkId = cdma.getNetworkId() >= 0 ? cdma.getNetworkId()
                    : this.networkId;
            this.systemId = cdma.getSystemId() >= 0 ? cdma.getSystemId()
                    : this.systemId;
            String mccMnc = telephonyManager.getNetworkOperator();
            if (mccMnc != null && mccMnc.length() >= 5) {
                this.mcc = mccMnc.substring(0, 3);
                this.mnc = mccMnc.substring(3, 5);
            }

            SignalStrength signalStrength = MeasureContext.getInstance()
                    .getSignalStrength();
            if (signalStrength != null) {
                this.evdoRSSI = -113 + 2 * signalStrength.getEvdoDbm();
                this.cdmaRSSI = -113 + 2 * signalStrength.getCdmaDbm();
                this.signalStrength = (signalStrength.getCdmaDbm() == -1) ? this.evdoRSSI
                        : this.cdmaRSSI;
            }

            this.timeStamp = System.currentTimeMillis();
        }
    }

    @Override
    public List<ParamField> ToPropertyList() {
        List<ParamField> fieldList = super.ToPropertyList();
        fieldList.add(new ParamField("BaseStationId", this.getBaseStationId()
                + ""));
        fieldList.add(new ParamField("NetworkId", this.networkId + ""));
        fieldList.add(new ParamField("SystemId", this.systemId + ""));
        fieldList.add(new ParamField("CdmaRSSI", this.cdmaRSSI + ""));
        fieldList.add(new ParamField("CdmaEcio", this.cdmaEcio + ""));
        fieldList.add(new ParamField("CdmaLevel", this.cdmaLevel + ""));
        fieldList.add(new ParamField("EvdoRSSI", this.evdoRSSI + ""));
        fieldList.add(new ParamField("EvdoEcio", this.evdoEcio + ""));
        fieldList.add(new ParamField("EvdoLevel", this.evdoLevel + ""));
        fieldList.add(new ParamField("EvdoSnr", this.evdoSnr + ""));

        return fieldList;
    }

}
