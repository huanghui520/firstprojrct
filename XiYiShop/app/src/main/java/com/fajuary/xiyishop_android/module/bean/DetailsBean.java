package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/21
 * 订单详情
 */

public class DetailsBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"buytime":1477027139,"goodsId":122,"money":1256000,"orderCode":"16102113180001","purchasequantity":2,"spare":2}
     */

    private String code;
    private String msg;
    /**
     * buytime : 1477027139
     * goodsId : 122
     * money : 1256000
     * orderCode : 16102113180001
     * purchasequantity : 2
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
        private String buytime;// 	购买时间
        private String goodsId;//商品id
        private String money;//支付金额
        private String orderCode;// 	订单编号
        private String purchasequantity;//购买数量
        private String spare;// 	全款/预付款 1、全款，2预付款
        private String count;// 	使用次数
        private String excisetime;
        private String bigpay;

        public String getBigpay() {
            return bigpay;
        }

        public void setBigpay(String bigpay) {
            this.bigpay = bigpay;
        }

        public String getExcisetime() {
            return excisetime;
        }

        public void setExcisetime(String excisetime) {
            this.excisetime = excisetime;
        }

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPurchasequantity() {
            return purchasequantity;
        }

        public void setPurchasequantity(String purchasequantity) {
            this.purchasequantity = purchasequantity;
        }

        public String getSpare() {
            return spare;
        }

        public void setSpare(String spare) {
            this.spare = spare;
        }

        public String getCount() {
            return count;
        }


        public void setCount(String count) {
            this.count = count;
        }
    }
}
