package com.jindouy.station_android.module.bean;

/**
 * created by huanghui at 2016/11/14
 * t提现申请
 */

public class MyTiXianBean {

    /**
     * msg : 提现成功
     */

    private InfoBean info;
    /**
     * info : {"msg":"提现成功"}
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
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
