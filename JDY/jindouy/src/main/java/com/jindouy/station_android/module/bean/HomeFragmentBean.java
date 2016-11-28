package com.jindouy.station_android.module.bean;

/**
 * created by huanghui at 2016/11/16
 * 首页消息
 */

public class HomeFragmentBean {
    private String order_number;//订单号
    private String time;        //时间
    private String ststus;      //状态

    public HomeFragmentBean(String order_number, String time, String ststus) {
        this.order_number = order_number;
        this.time = time;
        this.ststus = ststus;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStstus() {
        return ststus;
    }

    public void setStstus(String ststus) {
        this.ststus = ststus;
    }
}
