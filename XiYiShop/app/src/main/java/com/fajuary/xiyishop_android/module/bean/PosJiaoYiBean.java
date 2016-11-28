package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/11/21
 */

public class PosJiaoYiBean {


    /**
     * out_trade_no : 11479795176104
     * amount : 0.01
     * trans_type : WEIXIN_PAY
     */

    private TradeDataBean trade_data;
    /**
     * trade_data : {"out_trade_no":"11479795176104","amount":"0.01","trans_type":"WEIXIN_PAY"}
     * re_code : 0
     */

    private int re_code;

    public TradeDataBean getTrade_data() {
        return trade_data;
    }

    public void setTrade_data(TradeDataBean trade_data) {
        this.trade_data = trade_data;
    }

    public int getRe_code() {
        return re_code;
    }

    public void setRe_code(int re_code) {
        this.re_code = re_code;
    }

    public static class TradeDataBean {
        private String out_trade_no;
        private String amount;
        private String trans_type;

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTrans_type() {
            return trans_type;
        }

        public void setTrans_type(String trans_type) {
            this.trans_type = trans_type;
        }
    }
}
