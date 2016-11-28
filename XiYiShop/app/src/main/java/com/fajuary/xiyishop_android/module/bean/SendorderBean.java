package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/25
 * 验证此单
 */

public class SendorderBean {

    /**
     * code : 2
     * msg : 查询失败！
     * datas : 该服务不属于这家商户！
     */

    private String code;
    private String msg;
    private String datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
