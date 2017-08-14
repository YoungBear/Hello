package com.example.measuresdk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2017/8/9.
 */

public class MeasureData {
    private static final String TAG = "MeasureData";

    /**
     * 标题常量
     * */
    public static final String TITLE_POSITION = "室分点";
    public static final String TITLE_FLOOR = "楼层";
    public static final String TITLE_POINT_X = "X";
    public static final String TITLE_POINT_Y = "Y";
    public static final String TITLE_PCI = "PCI";
    public static final String TITLE_RSRP = "RSRP";
    public static final String TITLE_SINR = "SINR";

    /**
     * 分隔符
     * */
    public static final String SEPARATOR = ",";

    private List<MeasureItem> mMeasureItemList = new ArrayList<>();

    /**
     * 文件名
     * */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MeasureItem> getMeasureItemList() {
        return mMeasureItemList;
    }

    public void addData(MeasureItem item) {
        mMeasureItemList.add(item);
    }

    public static final String getTitle() {
        String title = TITLE_POSITION
                + SEPARATOR + TITLE_FLOOR
                + SEPARATOR + TITLE_POINT_X
                + SEPARATOR + TITLE_POINT_Y
                + SEPARATOR + TITLE_PCI
                + SEPARATOR + TITLE_RSRP
                + SEPARATOR + TITLE_SINR
                + "\r\n";
        return title;
    }

    public static class MeasureItem{
        /**
         * 地点
         * */
        private String position;


        /**
         * 楼层
         * */
        private String floor;

        /**
         * 坐标
         * */
        private String point_x;
        private String point_y;

        /**
         * 信号值
         * */
        private String pci;
        private String rsrp;
        private String sinr;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getPoint_x() {
            return point_x;
        }

        public void setPoint_x(String point_x) {
            this.point_x = point_x;
        }

        public String getPoint_y() {
            return point_y;
        }

        public void setPoint_y(String point_y) {
            this.point_y = point_y;
        }

        public String getPci() {
            return pci;
        }

        public void setPci(String pci) {
            this.pci = pci;
        }

        public String getRsrp() {
            return rsrp;
        }

        public void setRsrp(String rsrp) {
            this.rsrp = rsrp;
        }

        public String getSinr() {
            return sinr;
        }

        public void setSinr(String sinr) {
            this.sinr = sinr;
        }

        public String getContent() {
            String content = getPosition()
                    + MeasureData.SEPARATOR + getFloor()
                    + MeasureData.SEPARATOR + getPoint_x()
                    + MeasureData.SEPARATOR + getPoint_y()
                    + MeasureData.SEPARATOR + getPci()
                    + MeasureData.SEPARATOR + getRsrp()
                    + MeasureData.SEPARATOR + getSinr()
                    + "\r\n";
            return content;
        }
    }


}
