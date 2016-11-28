package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/20
 * 账户信息
 */

public class AccountBean {
    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"amountToday":0,"balance":10000,"orderNum":0,"totalSales":1058435.16}
     */
    private String code;
    private String msg;
    /**
     * amountToday : 0
     * balance : 10000
     * orderNum : 0
     * totalSales : 1058435.16
     */
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String amountToday;//今日销售额
        private String balance;//账户余额
        private String orderNum;//今日订单数
        private String totalSales;//总销售额

        public String getAmountToday() {
            return amountToday;
        }

        public void setAmountToday(String amountToday) {
            this.amountToday = amountToday;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(String totalSales) {
            this.totalSales = totalSales;
        }
    }
}
