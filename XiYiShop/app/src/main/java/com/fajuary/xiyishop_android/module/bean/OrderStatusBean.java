package com.fajuary.xiyishop_android.module.bean;


import java.util.List;

/**
 * created by huanghui at 2016/10/11
 * 订单状态
 */

public class OrderStatusBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"buytime":1477809543,"goodsName":"7+2 乞力马扎罗攀登-可口可乐路线","orderId":309,"spare_status":0,"standardName":"","type":0,"userName":"神游"},{"buytime":1477809499,"goodsName":"7+2 麦金利峰","orderId":308,"spare_status":2,"standardName":"","type":2,"userName":"神游"},{"buytime":1477808795,"goodsName":"7+2 乞力马扎罗攀登-可口可乐路线","orderId":306,"spare_status":2,"standardName":"","type":2,"userName":"喜马拉雅"},{"buytime":1477808305,"goodsName":"7+2 乞力马扎罗攀登-可口可乐路线","orderId":304,"spare_status":1,"standardName":"","type":2,"userName":"任卓2"},{"buytime":1477807751,"goodsName":"7+2 乞力马扎罗攀登-可口可乐路线","orderId":303,"spare_status":1,"standardName":"","type":1,"userName":"喜马拉雅"}]
     */

    private String code;
    private String msg;
    /**
     * buytime : 1477809543
     * goodsName : 7+2 乞力马扎罗攀登-可口可乐路线
     * orderId : 309
     * spare_status : 0
     * standardName :
     * type : 0
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
        private String goodsName;
        private String orderId;
        private String spare_status;
        private String standardName;
        private String type;
        private String userName;
        private String refund;

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getBuytime() {
            return buytime;
        }

        public void setBuytime(String buytime) {
            this.buytime = buytime;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSpare_status() {
            return spare_status;
        }

        public void setSpare_status(String spare_status) {
            this.spare_status = spare_status;
        }

        public String getStandardName() {
            return standardName;
        }

        public void setStandardName(String standardName) {
            this.standardName = standardName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
