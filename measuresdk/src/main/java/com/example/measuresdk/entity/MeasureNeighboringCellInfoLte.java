package com.example.measuresdk.entity;

import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.NeighboringCellInfo;

import com.example.measuresdk.common.ParamField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureNeighboringCellInfoLte extends MeasureNeighboringCellInfo {
    private int pci = 0;

    private int tac = 0;

    private int eNB =0;

    private int cellID =0;

    private MeasureCellLte measureCellLte = null;

    /**
     * @return
     */
    public int getPCI() {
        return pci;
    }

    /**
     * @return
     */
    public int getTAC() {
        return tac;
    }


    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int geteNB() {
        return eNB;
    }

    public void seteNB(int eNB) {
        this.eNB = eNB;
    }

    /* (non-Javadoc)
     * @see com.rnop.hook.measure.entity.MeasureNeighboringCellInfo#initCellInfo(android.telephony.NeighboringCellInfo)
     */
    @Override
    public void initNeighboringCellInfo(NeighboringCellInfo neighboringCellInfo) {
        super.initNeighboringCellInfo(neighboringCellInfo);

        this.eNB = (this.getCid() & 0xFFFFF00) / 256;
        this.cellID = (this.getCid() & 0xFF);
    }

    /* (non-Javadoc)
     * @see com.rnop.hook.measure.entity.MeasureNeighboringCellInfo#initCellInfo(android.telephony.CellInfo)
     */
    @Override
    public void initCellInfo(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoLte) {
            MeasureCellLte cell = new MeasureCellLte();
            cell.setCellInfo(cellInfo);
            this.measureCellLte = cell;
            this.tac = cell.getTac();
            this.pci = cell.getPci();
            //this.lac = cell.getTAC();
            this.cid = cell.getCid();
            this.networkType = "LTE";
            this.rssi = cell.getSignalStrength();

            this.eNB = (this.getCid() & 0xFFFFF00) / 256;
            this.cellID = (this.getCid() & 0xFF);

        }
    }

    /* (non-Javadoc)
     * @see com.rnop.hook.measure.entity.MeasureNeighboringCellInfo#ToPropertyList()
     */
    @Override
    public List<ParamField> ToPropertyList() {
        List<ParamField> fieldList = super.ToPropertyList();

        if (this.measureCellLte != null) {
            fieldList = new ArrayList<ParamField>();
            fieldList.add(new ParamField("NetworkType", this.getNetworkType()));
            fieldList.add(new ParamField("TAC", this.measureCellLte.getTac() + ""));
            fieldList.add(new ParamField("PCI", this.measureCellLte.getPci() + ""));
            fieldList.add(new ParamField("Cid", this.measureCellLte.getCid() + ""));
            fieldList.add(new ParamField("eNB", this.geteNB() + ""));
            fieldList.add(new ParamField("CellID", this.getCellID() + ""));
            fieldList.add(new ParamField("RSRP", this.measureCellLte.getSignalStrength() + ""));
            fieldList.add(new ParamField("RSRQ", this.measureCellLte.getRsrq() + ""));
            fieldList.add(new ParamField("SINR", this.measureCellLte.getRssnr() + ""));
        }
        if (this.pci != -1) {
            fieldList = new ArrayList<ParamField>();
            fieldList.add(new ParamField("TAC", this.getTAC() + ""));
            fieldList.add(new ParamField("PCI", this.getPCI() + ""));
            fieldList.add(new ParamField("Cid", this.getCid() + ""));
            fieldList.add(new ParamField("eNB", this.geteNB() + ""));
            fieldList.add(new ParamField("CellID", this.getCellID() + ""));
            fieldList.add(new ParamField("Rssi", String.valueOf(this.getRssi())));
            fieldList.add(new ParamField("NetworkType", this.getNetworkType()));
        }


        return fieldList;
    }

    @Override
    public String ToString() {
        if(this.measureCellLte==null) {
            return super.ToString();
        }
        else {
            return "<na>" + this.getNetworkType()
                    + "|" + this.getTAC()
                    + "|" + this.getCid()
                    + "|" + this.getPCI()
                    + "|" + this.geteNB()
                    + "|" + this.getCellID()
                    + "|" + this.measureCellLte.getSignalStrength()
                    + "|" + this.measureCellLte.getRsrq()
                    + "|" + this.measureCellLte.getRssnr() + "</na>";
        }
    }
}
