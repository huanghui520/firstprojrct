package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/19
 */

public class LogInBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"token":"155c2a645ae6bec0ae196c080ac9a02a","shopId":"1"}
     */

    private String code;
    private String msg;
    /**
     * token : 155c2a645ae6bec0ae196c080ac9a02a
     * shopId : 1
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
        private String token;
        private String shopId;
        private String shopName;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
