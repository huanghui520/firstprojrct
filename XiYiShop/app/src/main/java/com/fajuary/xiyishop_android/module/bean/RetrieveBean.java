package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/23
 * 三个参数
 */

public class RetrieveBean {

    /**
     * code : 403
     * msg : 查询成功！
     * datas : 该手机号不存在！
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
