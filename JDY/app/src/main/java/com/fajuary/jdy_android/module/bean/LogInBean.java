package com.fajuary.jdy_android.module.bean;/**
 * 作者：huanghui on 2016/11/8 13:21
 */

/**
 * created by huanghui at 2016/11/8
 */

public class LogInBean {

    /**
     * token : ca6942fab55ad2fc8938031525faac2e
     * type : 2
     */

    private InfoBean info;
    /**
     * info : {"token":"ca6942fab55ad2fc8938031525faac2e","type":2}
     * status : 1
     * url :
     */

    private String status;//状态码
    private String url;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class InfoBean {
        private String token;//用户token
        private String type;//用户类型（1、代收点，2、基站/中转站）

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
