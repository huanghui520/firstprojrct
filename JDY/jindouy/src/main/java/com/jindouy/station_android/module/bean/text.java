package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/24
 */

public class text {


    /**
     * info : [[{"id":"2","sendname":"北京昌平回龙观基站2"},{"id":"3","sendname":"筋斗云快递"},{"id":"9","sendname":"上坡之家"}],[{"id":"1","sendname":"筋斗云快递1"}]]
     * status : 1
     * url :
     */

    private int status;
    private String url;
    /**
     * id : 2
     * sendname : 北京昌平回龙观基站2
     */
    private List<List<InfoBean>> info;

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

    public List<List<InfoBean>> getInfo() {
        return info;
    }

    public void setInfo(List<List<InfoBean>> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String id;
        private String sendname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }
    }
}
