package com.fajuary.jdy_android.module.bean;

/**
 * created by huanghui at 2016/11/8
 *
 * 我的——我的账户
 */

public class MyAccountBean {

    /**
     * account : 0
     * total : 0
     */

    private InfoBean info;
    /**
     * info : {"account":0,"total":0}
     * status : 1
     * url :
     */

    private String status;
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
        private String account;
        private String total;

        public String getAccount() {
            return account;
        }
        public void setAccount(String account) {
            this.account = account;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
