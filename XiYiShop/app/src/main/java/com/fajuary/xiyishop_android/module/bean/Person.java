package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/27
 */

public class Person {
//    标题、输入金额、商户id
    private  String  title;//标题
    private String  money;//金额
    private String shopId;//商户id
    private  String type;//类型
    private String shopName;

    public Person(String title, String money, String shopId, String type, String shopName) {
        this.title = title;
        this.money = money;
        this.shopId = shopId;
        this.type = type;
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" +
                "title='" + title + '\'' +
                ", money='" + money + '\'' +
                ", shopId='" + shopId + '\'' +
                ", type='" + type + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
