package com.fajuary.xiyishop_big_android;

/**
 * created by huanghui at 2016/11/23
 */

public class ScanBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"bigpay":1,"buytime":1479697808,"count":0,"goodsId":197,"goodsName":"勃朗峰攀登","money":19800,"num":1,"orderGoodsId":722,"orderId":615,"ordercode":"16112111100001","phone":"15575205994","refund":0,"spare":2}
     */

    private String code;
    private String msg;
    /**
     * bigpay : 1
     * buytime : 1479697808
     * count : 0
     * goodsId : 197
     * goodsName : 勃朗峰攀登
     * money : 19800
     * num : 1
     * orderGoodsId : 722
     * orderId : 615
     * ordercode : 16112111100001
     * phone : 15575205994
     * refund : 0
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
        private int bigpay;
        private int buytime;
        private int count;
        private int goodsId;
        private String goodsName;
        private String money;
        private int num;
        private int orderGoodsId;
        private int orderId;
        private String ordercode;
        private String phone;
        private String shopName;
        private int refund;
        private int spare;

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getBigpay() {
            return bigpay;
        }

        public void setBigpay(int bigpay) {
            this.bigpay = bigpay;
        }

        public int getBuytime() {
            return buytime;
        }

        public void setBuytime(int buytime) {
            this.buytime = buytime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getOrderGoodsId() {
            return orderGoodsId;
        }

        public void setOrderGoodsId(int orderGoodsId) {
            this.orderGoodsId = orderGoodsId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public int getSpare() {
            return spare;
        }

        public void setSpare(int spare) {
            this.spare = spare;
        }
    }
}
