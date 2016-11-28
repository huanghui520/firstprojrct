package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/28
 * <p>
 * 收尾款二维码信息
 */

public class PayWeiKuanBean {

    private String shopName;//商户名
    private String orderGoodsId;//服务id
    private String shopId;//商户id
    private String goodsName;//商品名
    private String money;//金额
    private String title;//收尾款类型  1，2
    private String type;//2  收尾款


    public PayWeiKuanBean() {
    }

    public PayWeiKuanBean( String shopName,String orderGoodsId, String shopId, String goodsName, String money, String title, String type) {
        this.shopName = shopName;
        this.orderGoodsId = orderGoodsId;
        this.shopId = shopId;
        this.goodsName = goodsName;
        this.money = money;
        this.title = title;
        this.type = type;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PayWeiKuanBean{" +
                "shopName='" + shopName + '\'' +
                ", orderGoodsId='" + orderGoodsId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", money='" + money + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
