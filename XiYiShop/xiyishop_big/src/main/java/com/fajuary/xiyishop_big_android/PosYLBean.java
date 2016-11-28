package com.fajuary.xiyishop_big_android;

/**
 * created by huanghui at 2016/11/25
 */

public class PosYLBean {

    /**
     * amount : 0.01
     * mchtNo : 848584092236000
     * tradeTime : 2016-11-25 11:04:00
     * termNo : 58500009
     * acquirer : 48480000
     * issBank : 建设银行
     * mchtName : 云刷科技
     * referenceNo : 633011259875
     * voucherNo : 1480043062722
     * pan : 621700*********1740
     */

    private BodyBean body;
    /**
     * body : {"amount":"0.01","mchtNo":"848584092236000","tradeTime":"2016-11-25 11:04:00","termNo":"58500009","acquirer":"48480000","issBank":"建设银行","mchtName":"云刷科技","referenceNo":"633011259875","voucherNo":"1480043062722","pan":"621700*********1740"}
     * errMsg : 交易成功
     * retCode : 00
     */

    private String errMsg;
    private String retCode;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class BodyBean {
        private String amount;
        private String mchtNo;
        private String tradeTime;
        private String termNo;
        private String acquirer;
        private String issBank;
        private String mchtName;
        private String referenceNo;
        private String voucherNo;
        private String pan;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMchtNo() {
            return mchtNo;
        }

        public void setMchtNo(String mchtNo) {
            this.mchtNo = mchtNo;
        }

        public String getTradeTime() {
            return tradeTime;
        }

        public void setTradeTime(String tradeTime) {
            this.tradeTime = tradeTime;
        }

        public String getTermNo() {
            return termNo;
        }

        public void setTermNo(String termNo) {
            this.termNo = termNo;
        }

        public String getAcquirer() {
            return acquirer;
        }

        public void setAcquirer(String acquirer) {
            this.acquirer = acquirer;
        }

        public String getIssBank() {
            return issBank;
        }

        public void setIssBank(String issBank) {
            this.issBank = issBank;
        }

        public String getMchtName() {
            return mchtName;
        }

        public void setMchtName(String mchtName) {
            this.mchtName = mchtName;
        }

        public String getReferenceNo() {
            return referenceNo;
        }

        public void setReferenceNo(String referenceNo) {
            this.referenceNo = referenceNo;
        }

        public String getVoucherNo() {
            return voucherNo;
        }

        public void setVoucherNo(String voucherNo) {
            this.voucherNo = voucherNo;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }
    }
}
