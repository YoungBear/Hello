package com.example.mylibrary.phone.entity;

import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.NeighboringCellInfo;

import com.example.mylibrary.phone.common.ParamField;
import com.example.mylibrary.phone.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureNeighboringCellInfo {

    protected int lac = -1;

    protected int cid = 0;

    protected String networkType = "";

    protected float psc = 0;

    protected double rssi = 0;

    /**
     * get LAC
     *
     * @return LAC
     */
    public int getLac() {
        return lac;
    }

    /**
     * get CID
     *
     * @return CID
     */
    public int getCid() {
        return cid;
    }

    /**
     * get NetworkType
     *
     * @return NetworkType
     */
    public String getNetworkType() {
        return networkType;
    }

    /**
     * get Psc
     *
     * @return Psc
     */
    public float getPsc() {
        return psc;
    }

    /**
     * get Rssi
     *
     * @return Rssi
     */
    public double getRssi() {
        return rssi;
    }

    /**
     * 初始化数据
     *
     * @param neighboringCellInfo 邻区对象
     */
    public void initNeighboringCellInfo(NeighboringCellInfo neighboringCellInfo) {
        this.lac = neighboringCellInfo.getLac();
        this.cid = neighboringCellInfo.getCid();
        this.networkType = NetworkUtils.getNetworkType(neighboringCellInfo
                .getNetworkType());
        this.psc = neighboringCellInfo.getPsc();
        this.rssi = -133 + 2 * neighboringCellInfo.getRssi();
    }

    /**
     * 初始化数据
     *
     * @param cellInfo 邻区对象
     */
    public void initCellInfo(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoGsm) {
            MeasureCellGsm cell = new MeasureCellGsm();
            cell.setCellInfo(cellInfo);
            this.lac = cell.getLac();
            this.cid = cell.getCid();
            this.networkType = "GSM";
            this.psc = cell.getPsc();
            this.rssi = cell.getSignalStrength();
        }
    }

    /**
     * 已集合形式返回对象数据
     *
     * @return 对象数据
     */
    public List<ParamField> ToPropertyList() {
        List<ParamField> fieldList = new ArrayList<ParamField>();
        fieldList.add(new ParamField("Lac", this.getLac() + ""));
        fieldList.add(new ParamField("Cid", this.getCid() + ""));
        fieldList.add(new ParamField("Psc", this.getPsc() + ""));
        fieldList.add(new ParamField("Rssi", this.getRssi() + ""));
        fieldList.add(new ParamField("NetworkType", this.getNetworkType()));

        return fieldList;
    }

    /**
     * 返回上报字符串
     *
     * @return 上报字符串
     */
    public String ToString() {
        return "<na>"
                + this.getNetworkType()
                + "|" + this.getLac()
                + "|" + this.getCid()
                + "|" + this.getPsc()
                + "|" + this.getRssi()
                + "</na>";
    }

}
