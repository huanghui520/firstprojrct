package com.fajuary.xiyishop_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/10/15
 */

public class ShangPinBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"goodsId":124,"goodsName":"测试版本","price":100,"thumbnail":"http://60.205.149.58:8082/files/2016/10/17/141ea9a63b-d6f4-407d-88da-8984b3194e84.jpg"},{"goodsId":124,"goodsName":"测试版本","price":100,"thumbnail":"http://60.205.149.58:8082/files/2016/10/17/141ea9a63b-d6f4-407d-88da-8984b3194e84.jpg"},{"goodsId":124,"goodsName":"测试版本","price":100,"thumbnail":"http://60.205.149.58:8082/files/2016/10/17/141ea9a63b-d6f4-407d-88da-8984b3194e84.jpg"},{"goodsId":124,"goodsName":"测试版本","price":100,"thumbnail":"http://60.205.149.58:8082/files/2016/10/17/141ea9a63b-d6f4-407d-88da-8984b3194e84.jpg"}]
     */

    private String code;
    private String msg;
    /**
     * goodsId : 124
     * goodsName : 测试版本
     * price : 100
     * thumbnail : http://60.205.149.58:8082/files/2016/10/17/141ea9a63b-d6f4-407d-88da-8984b3194e84.jpg
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
        private String goodsId;//商品id
        private String goodsName;//商品名称
        private String price;//商品价格
        private String thumbnail;//缩略图

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
