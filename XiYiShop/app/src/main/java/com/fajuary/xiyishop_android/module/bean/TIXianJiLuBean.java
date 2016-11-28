package com.fajuary.xiyishop_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/10/13
 * 提现记录
 */

public class TIXianJiLuBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"amount":0,"bandId":8,"present_state":0,"time":1354293472}]
     */

    private String code;
    private String msg;
    /**
     * amount : 0
     * bandId : 8
     * present_state : 0
     * time : 1354293472
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
        private String amount;
        private String bandId;
        private String present_state;
        private String time;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBandId() {
            return bandId;
        }

        public void setBandId(String bandId) {
            this.bandId = bandId;
        }

        public String getPresent_state() {
            return present_state;
        }

        public void setPresent_state(String present_state) {
            this.present_state = present_state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
