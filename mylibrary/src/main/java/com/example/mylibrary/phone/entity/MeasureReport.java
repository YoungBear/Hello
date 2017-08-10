package com.example.mylibrary.phone.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 * <p>
 * 测量报告
 */

public class MeasureReport {

    public static final String INVALID = "invalid";

    private List<MeasureCell> measureGsmCells = new ArrayList<MeasureCell>();

    private List<MeasureCell> measureCdmaCells = new ArrayList<MeasureCell>();

    private List<MeasureCell> measureLteCells = new ArrayList<MeasureCell>();

    private List<MeasureNeighboringCellInfo> neighboringCellInfos = new ArrayList<MeasureNeighboringCellInfo>();

    /**
     * 获取Gsm测量小区
     */
    public List<MeasureCell> getMeasureGsmCells() {
        return this.measureGsmCells;
    }

    /**
     * 获取Cdma测量小区
     */
    public List<MeasureCell> getMeasureCdmaCells() {
        return this.measureCdmaCells;
    }

    /**
     * 获取Lte测量小区
     */
    public List<MeasureCell> getMeasureLteCells() {
        return this.measureLteCells;
    }

    /**
     * 获取邻区测量信息
     */
    public List<MeasureNeighboringCellInfo> getNeighboringCellInfos() {
        return this.neighboringCellInfos;
    }

    /**
     * 设置邻区测量信息
     *
     * @param neighboringCellInfos 邻区测量信息
     */
    public void setNeighboringCellInfos(
            List<MeasureNeighboringCellInfo> neighboringCellInfos) {
        this.neighboringCellInfos = neighboringCellInfos;
    }

    public String getPci() {
        if (measureLteCells.size() > 0) {
            MeasureCell measureCell = measureLteCells.get(0);
            if (measureCell instanceof MeasureCellLte) {
                return String.valueOf(((MeasureCellLte) measureCell).getPci());
            }
        } else if (measureGsmCells.size() > 0) {

        } else if (measureCdmaCells.size() > 0) {

        }
        return INVALID;
    }

    public String getRsrp() {
        if (measureLteCells.size() >= 0) {
            MeasureCell measureCell = measureLteCells.get(0);
//            if (measureCell instanceof MeasureCellLte) {
//                return String.valueOf(((MeasureCellLte) measureCell).getR);
//            }
        } else if (measureGsmCells.size() >= 0) {
//            MeasureCell measureCell = measureLteCells.get(0);
//            if (measureCell instanceof MeasureCellGsm) {
//                return String.valueOf(((MeasureCellGsm) measureCell).getPsc());
//            }
        } else if (measureCdmaCells.size() >= 0) {
//            MeasureCell measureCell = measureLteCells.get(0);
//            if (measureCell instanceof MeasureCellCdma) {
//                return String.valueOf(((MeasureCellCdma) measureCell).getCdmaRSSI());
//            }
        }
        return INVALID;
    }

}
