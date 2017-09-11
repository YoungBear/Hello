package com.example.measuresdk.entity;

import android.content.Context;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthLte;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.example.measuresdk.MeasureContext;
import com.example.measuresdk.common.ParamField;

import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureCellLte extends MeasureCell {

    private int pci = 0;

    private int tac = 0;

    private int rsrq = 0;

    private int rssnr = 0;

    private int eNB = 0;

    private int cellId = 0;

    public int getPci() {
        return pci;
    }

    public int getTac() {
        return tac;
    }

    public int getRsrq() {
        return rsrq;
    }

    public int getRssnr() {
        return rssnr;
    }

    public int geteNB() {
        return eNB;
    }

    public int getCellId() {
        return cellId;
    }

    @Override
    public void setCellInfo(CellInfo cellInfo) {
        if (cellInfo == null) {
            return;
        }

        if (!(cellInfo instanceof CellInfoLte)) {
            return;
        }

        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
        CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
        if (cellIdentityLte == null) {
            return;
        }

        this.isValid = true;
        this.mcc = String.valueOf(cellIdentityLte.getMcc());
        this.mnc = "0" + String.valueOf(cellIdentityLte.getMnc());
        this.pci = cellIdentityLte.getPci();
        this.tac = cellIdentityLte.getTac();
        this.cid = cellIdentityLte.getCi();
        this.timeStamp = System.currentTimeMillis();
        CellSignalStrengthLte signalStrength = cellInfoLte
                .getCellSignalStrength();
        if (signalStrength != null) {
            this.signalStrength = signalStrength.getDbm();
        }

        this.eNB = (this.getCid() & 0xFFFFF00) / 256;
        this.cellId = (this.getCid() & 0xFF);
    }

    @Override
    public void setCellInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) MeasureContext.getInstance()
                .getContext().getSystemService(Context.TELEPHONY_SERVICE);
        CellLocation location = telephonyManager.getCellLocation();
        if (telephonyManager.getNetworkType() != TelephonyManager.NETWORK_TYPE_LTE) {
            return;
        }

        if (location instanceof GsmCellLocation) {// GSM网络
            this.isValid = true;
            GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
            this.lac = gsmCellLocation.getLac();
            this.cid = gsmCellLocation.getCid();
            this.tac = gsmCellLocation.getLac();

            String mccMnc = telephonyManager.getNetworkOperator();
            if (mccMnc != null && mccMnc.length() >= 5) {
                this.mcc = mccMnc.substring(0, 3);
                this.mnc = mccMnc.substring(3, 5);
            }

            SignalStrength signalStrength = MeasureContext.getInstance().getSignalStrength();
            if (signalStrength != null) {
                SignalStrengthResult ss = signalStrengthParse(signalStrength);
                this.signalStrength = ss.rsrp;
                this.rsrq = ss.rsrq;
                this.rssnr = ss.rssnr;
            }

            this.timeStamp = System.currentTimeMillis();
        } else if (location instanceof CdmaCellLocation) {
            this.isValid = true;
            SignalStrength signalStrength = MeasureContext.getInstance().getSignalStrength();
            if (signalStrength != null) {
                SignalStrengthResult ss = signalStrengthParse(signalStrength);
                this.signalStrength = ss.rsrp;
                this.rsrq = ss.rsrq;
                this.rssnr = ss.rssnr;
            }

            this.timeStamp = System.currentTimeMillis();
        }

        this.eNB = (this.getCid() & 0xFFFFF00) / 256;
        this.cellId = (this.getCid() & 0xFF);
    }

    @Override
    public List<ParamField> toPropertyList() {
        List<ParamField> fieldList = super.toPropertyList();
        fieldList.add(new ParamField("PCI", (this.getPci() == 0 ? "-" : this.getPci() + "")));
        fieldList.add(new ParamField("TAC", this.getTac() + ""));
        fieldList.add(new ParamField("CI", this.getCid() + ""));

        fieldList.add(new ParamField("eNB", this.geteNB() + ""));
        fieldList.add(new ParamField("CellID", this.getCellId() + ""));

        fieldList.add(new ParamField("RSRP", this.getSignalStrength() + ""));
        fieldList.add(new ParamField("RSRQ", this.getRsrq() + ""));
        fieldList.add(new ParamField("SINR", this.getRssnr() + ""));

        return fieldList;
    }

    // TODO: 2017/8/9 重写toString()方法，为上传数据做准备

}
