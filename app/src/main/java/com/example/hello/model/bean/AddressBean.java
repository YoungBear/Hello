package com.example.hello.model.bean;

import java.util.List;

/**
 * @author ysx
 *         date 2017/12/16
 *         description
 */

public class AddressBean {

    /**
     * message : ok
     * result : [{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.915119,"lng":116.403963},"name":"天安门","uid":"65e1ee886c885190f60e77ff"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.909677,"lng":116.404168},"name":"天安门广场","uid":"c9b5fb91d49345bc5d0d0262"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.91408,"lng":116.407851},"name":"天安门东-地铁站","uid":"940aeb3c98d5a0218a2fb5de"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.913279,"lng":116.40393},"name":"天安门广场-国旗","uid":"4ae2adcf574bcd2b38221c66"},{"business":"","city":"北京市","cityid":"131","district":"西城区","location":{"lat":39.913776,"lng":116.39805},"name":"天安门西-地铁站","uid":"002975204d3b1e9b9968b4de"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.915866,"lng":116.403648},"name":"天安门城楼-入口","uid":"fd975efc10193656734b879a"},{"business":"","city":"北京市","cityid":"131","district":"西城区","location":{"lat":39.913922,"lng":116.400102},"name":"天安门-公交车站","uid":"26b1e84a170fc88c2efaa7a5"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.914683,"lng":116.403173},"name":"天安门-观礼台","uid":"d5a70b82114e2b2bddee8bae"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.907253,"lng":116.405886},"name":"天安门广场-入口","uid":"9711b16c49ec39e78605bfb4"},{"business":"","city":"北京市","cityid":"131","district":"东城区","location":{"lat":39.914065,"lng":116.407966},"name":"天安门东-公交车站","uid":"e1ee3a5e7be676c8213bf257"}]
     * status : 0
     */

    private String message;
    private int status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * business :
         * city : 北京市
         * cityid : 131
         * district : 东城区
         * location : {"lat":39.915119,"lng":116.403963}
         * name : 天安门
         * uid : 65e1ee886c885190f60e77ff
         */

        private String business;
        private String city;
        private String cityid;
        private String district;
        private LocationBean location;
        private String name;
        private String uid;

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public static class LocationBean {
            /**
             * lat : 39.915119
             * lng : 116.403963
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", result=" + result +
                '}';
    }
}
