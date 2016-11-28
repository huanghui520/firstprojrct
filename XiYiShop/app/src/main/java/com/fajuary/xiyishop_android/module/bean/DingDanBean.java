package com.fajuary.xiyishop_android.module.bean;


import java.util.List;

/**
 * created by huanghui at 2016/10/12
 * 未完成订单
 */

public class DingDanBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"bigpay":0,"count":0,"goodsName":"7+2 徒步南极点+文森峰","money":596600,"num":1,"orderAddress":{"address":"bbbb","id":11,"mobile":"13811180443","order_id":476,"shdate":3,"shname":"renzhuo"},"orderId":476,"spare":3,"spare_status":2,"standardName":"","thumbnail":"http://60.205.149.58:8082/files/2016/10/24/1786785ab8-088f-4939-8b9d-c4b6143b7405.jpg"},{"bigpay":0,"count":0,"goodsName":"7+2 乞力马扎罗攀登-可口可乐路线","money":0.01,"num":1,"orderAddress":null,"orderId":471,"spare":2,"spare_status":1,"standardName":"","thumbnail":"http://60.205.149.58:8082/files/2016/10/24/1796276487-6abe-4265-a463-9a70c282652d.jpg"}]
     */

    private String code;
    private String msg;
    /**
     * bigpay : 0
     * count : 0
     * goodsName : 7+2 徒步南极点+文森峰
     * money : 596600
     * num : 1
     * orderAddress : {"address":"bbbb","id":11,"mobile":"13811180443","order_id":476,"shdate":3,"shname":"renzhuo"}
     * orderId : 476
     * spare : 3
     * spare_status : 2
     * standardName :
     * thumbnail : http://60.205.149.58:8082/files/2016/10/24/1786785ab8-088f-4939-8b9d-c4b6143b7405.jpg
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
        private String bigpay;
        private String count;
        private String goodsName;
        private String money;
        private String num;
        private String refund;

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        /**
         * address : bbbb
         * id : 11
         * mobile : 13811180443
         * order_id : 476
         * shdate : 3
         * shname : renzhuo
         */

        private OrderAddressBean orderAddress;
        private String orderId;
        private String spare;
        private String spare_status;
        private String standardName;
        private String thumbnail;

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

        public OrderAddressBean getOrderAddress() {
            return orderAddress;
        }

        public void setOrderAddress(OrderAddressBean orderAddress) {
            this.orderAddress = orderAddress;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSpare() {
            return spare;
        }

        public void setSpare(String spare) {
            this.spare = spare;
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

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public static class OrderAddressBean {
            private String address;
            private String id;
            private String mobile;
            private String order_id;
            private String shdate;
            private String shname;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getShdate() {
                return shdate;
            }

            public void setShdate(String shdate) {
                this.shdate = shdate;
            }

            public String getShname() {
                return shname;
            }

            public void setShname(String shname) {
                this.shname = shname;
            }
        }
    }
}
