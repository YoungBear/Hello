package com.example.measuresdk.entity;

import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;

import com.example.measuresdk.common.ParamField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureNeighboringCellInfoCdma extends MeasureNeighboringCellInfo {
    private int baseStationId = -1;

    private int evdoRSSI = -1;

    private int cdmaRSSI = -1;

    /**
     * get BaseStationId
     *
     * @return BaseStationId
     */
    public int getBaseStationId() {
        return baseStationId;
    }

    /**
     * get EvdoRSSI
     *
     * @return EvdoRSSI
     */
    public int getEvdoRSSI() {
        return evdoRSSI;
    }

    /**
     * get CdmaRSSI
     *
     * @return CdmaRSSI
     */
    public int getCdmaRSSI() {
        return cdmaRSSI;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.rnop.hook.measure.entity.MeasureNeighboringCellInfo#initCellInfo(android
     * .telephony.CellInfo)
     */
    @Override
    public void initCellInfo(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoCdma) {
            MeasureCellCdma cell = new MeasureCellCdma();
            cell.setCellInfo(cellInfo);
            this.baseStationId = cell.getBaseStationId();
            this.cdmaRSSI = cell.getCdmaRSSI();
            this.evdoRSSI = cell.getEvdoRSSI();
            this.lac = cell.getBaseStationId();
            this.cid = cell.getCid();
            this.networkType = "CDMA";
            this.rssi = cell.getSignalStrength();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.rnop.hook.measure.entity.MeasureNeighboringCellInfo#toPropertyList()
     */
    @Override
    public List<ParamField> toPropertyList() {
        List<ParamField> fieldList = super.toPropertyList();
        if (this.baseStationId != -1) {
            fieldList = new ArrayList<ParamField>();
            fieldList.add(new ParamField("BaseStationId", this
                    .getBaseStationId() + ""));
            fieldList.add(new ParamField("Cid", this.getCid() + ""));
            fieldList.add(new ParamField("CdmaRSSI", this.getCdmaRSSI() + ""));
            fieldList.add(new ParamField("EvdoRSSI", this.getEvdoRSSI() + ""));
            fieldList.add(new ParamField("NetworkType", this.getNetworkType()));
        }

        return fieldList;
    }

    @Override
    public String toString() {
        return "<na>" + this.getNetworkType()
                + "|" + this.getBaseStationId()
                + "|" + this.getCid()
                + "|" + this.getCdmaRSSI()
                + "|" + this.getEvdoRSSI()+"</na>";
    }
}
