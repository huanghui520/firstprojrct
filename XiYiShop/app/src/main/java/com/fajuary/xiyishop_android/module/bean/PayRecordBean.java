package com.fajuary.xiyishop_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/10/30
 */

public class PayRecordBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"buytime":1477749715,"money":0.01,"order_code":"16102922010001","payment":2,"title":"nnnn","userName":"神游"}]
     */

    private String code;
    private String msg;
    /**
     * buytime : 1477749715
     * money : 0.01
     * order_code : 16102922010001
     * payment : 2
     * title : nnnn
     * userName : 神游
     */

    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String buytime;
        private String money;
        private String order_code;
        private String payment;
        private String title;
        private String userName;

        public String getBuytime() {
            return buytime;
        }

        public void setBuytime(String buytime) {
            this.buytime = buytime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
