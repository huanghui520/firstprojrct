package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/24
 * 验证订单
 */

public class VerifyResultBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"buytime":1477307657,"goodsId":104,"goodsName":"1119","money":0.01,"num":1,"orderGoodsId":104,"orderId":105,"ordercode":"16102419140002","spare":2}
     */

    private String code;
    private String msg;
    /**
     * buytime : 1477307657
     * goodsId : 104
     * goodsName : 1119
     * money : 0.01
     * num : 1
     * orderGoodsId : 104
     * orderId : 105
     * ordercode : 16102419140002
     * spare : 2
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
        private String buytime;
        private String goodsId;
        private String goodsName;
        private String money;
        private String num;
        private String orderGoodsId;
        private String orderId;
        private String ordercode;
        private String spare;
        private  String bigpay;
        private String phone;
        private  String refund;

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBigpay() {
            return bigpay;
        }

        public void setBigpay(String bigpay) {
            this.bigpay = bigpay;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        private String count;

        public String getBuytime() {
            return buytime;
        }

        public void setBuytime(String buytime) {
            this.buytime = buytime;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrderGoodsId() {
            return orderGoodsId;
        }

        public void setOrderGoodsId(String orderGoodsId) {
            this.orderGoodsId = orderGoodsId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getSpare() {
            return spare;
        }

        public void setSpare(String spare) {
            this.spare = spare;
        }
    }
}
