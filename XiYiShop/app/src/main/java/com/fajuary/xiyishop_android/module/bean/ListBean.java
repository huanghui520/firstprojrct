package com.fajuary.xiyishop_android.module.bean;
/**
 * created by huanghui at 2016/10/26
 */

class ListBean1 {
    private String userName;
    private String money;
    private String time;

    public ListBean1(String userName, String money, String time) {
        this.userName = userName;
        this.money = money;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "userName='" + userName + '\'' +
                ", money='" + money + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
