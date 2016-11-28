package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/21
 * 商品详情
 */

public class ShangPingXiangQingBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"goodsName":"OOO","introduction":"","price":99999,"thumbnail":"file:///F:/pic/01.jpg"}
     */

    private String code;
    private String msg;
    /**
     * goodsName : OOO
     * introduction :
     * price : 99999
     * thumbnail : file:///F:/pic/01.jpg
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
        private String goodsName;
        private String introduction;
        private String price;
        private String thumbnail;

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
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
