package com.fajuary.xiyishop_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/10/26
 */

public class DetailsListBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : []
     */

    private String code;
    private String msg;
    private List<ListBean> datas;

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

    public List<ListBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ListBean> datas) {
        this.datas = datas;
    }

    public static class ListBean {
        private String userName;
        private String money;
        private String time;


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }
}
