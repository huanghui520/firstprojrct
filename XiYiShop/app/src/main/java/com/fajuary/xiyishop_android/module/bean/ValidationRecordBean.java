package com.fajuary.xiyishop_android.module.bean;
/**
 * created by huanghui at 2016/10/30
 * 验证记录
 */

public class ValidationRecordBean {
    private String number;
    private String time;

    public ValidationRecordBean(String number, String time) {
        this.number = number;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
