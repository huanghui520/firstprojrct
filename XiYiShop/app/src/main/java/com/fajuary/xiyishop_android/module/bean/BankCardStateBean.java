package com.fajuary.xiyishop_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/10/17
 *
 * 银行卡类状态
 */

public class BankCardStateBean {


    /**
     * code : 1
     * msg : 查询成功！
     * datas : [{"bank_account":"中国邮政储蓄银行","card_num":"6215991270000173474","cardholdername":"胡刚","id":19,"mobile":"","shop_id":48,"time":1476969305},{"bank_account":"农行","card_num":"6228480402564890018","cardholdername":"李","id":37,"mobile":"","shop_id":48,"time":1477046475},{"bank_account":"农行","card_num":"6228480402564890018","cardholdername":"李","id":38,"mobile":"","shop_id":48,"time":1477046475},{"bank_account":"农行","card_num":"6228480402564890018","cardholdername":"李","id":39,"mobile":"","shop_id":48,"time":1477046475},{"bank_account":"农行","card_num":"6228480402564890018","cardholdername":"李","id":40,"mobile":"","shop_id":48,"time":1477046475}]
     */

    private String code;
    private String msg;
    /**
     * bank_account : 中国邮政储蓄银行
     * card_num : 6215991270000173474
     * cardholdername : 胡刚
     * id : 19
     * mobile :
     * shop_id : 48
     * time : 1476969305
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
        private String bank_account;
        private String card_num;
        private String cardholdername;
        private String id;
        private String mobile;
        private String shop_id;
        private String time;

        public String getBank_account() {
            return bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCardholdername() {
            return cardholdername;
        }

        public void setCardholdername(String cardholdername) {
            this.cardholdername = cardholdername;
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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
