package com.jindouy.station_android.module.bean;

/**
 * created by huanghui at 2016/11/24
 */

public class MyFragmentBean {

    /**
     * account : 0.94
     * address : 北京市昌平区
     * city : 2
     * city_name : 北京
     * device_token :
     * forzen_account : 9.06
     * id : 2
     * idcard : 123
     * lasttime : 1479957756
     * lat : 40.078657
     * lng : 116.367816
     * nickname : li
     * pass : d93a5def7511da3d0f2d171d9c344e91
     * phone : 13811347470
     * pic : http://139.224.190.235/Uploads/2016-11-03/581aea3c328a7.jpg
     * regtime : 1478158912
     * sendname : 霍营小区代售点
     * status : 1
     */

    private InfoBean info;
    /**
     * info : {"account":"0.94","address":"北京市昌平区","city":"2","city_name":"北京","device_token":"","forzen_account":"9.06","id":"2","idcard":"123","lasttime":"1479957756","lat":"40.078657","lng":"116.367816","nickname":"li","pass":"d93a5def7511da3d0f2d171d9c344e91","phone":"13811347470","pic":"http://139.224.190.235/Uploads/2016-11-03/581aea3c328a7.jpg","regtime":"1478158912","sendname":"霍营小区代售点","status":"1"}
     * status : 1
     * url :
     */

    private int status;
    private String url;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class InfoBean {
        private String account;
        private String address;
        private String city;
        private String city_name;
        private String device_token;
        private String forzen_account;
        private String id;
        private String idcard;
        private String lasttime;
        private String lat;
        private String lng;
        private String nickname;
        private String pass;
        private String phone;
        private String pic;
        private String regtime;
        private String sendname;
        private String status;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getForzen_account() {
            return forzen_account;
        }

        public void setForzen_account(String forzen_account) {
            this.forzen_account = forzen_account;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
