package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 */

public class BankCardBean {

    /**
     * page : 1
     * count : 98
     * pagesize : 20
     * page_count : 5
     * list : [{"card_id":"110","bank_name":"uuuu","card_num":"4444","own_name":"dddd"},{"card_id":"109","bank_name":"yyyyyyyy","card_num":"1111","own_name":"tyyuy"},{"card_id":"108","bank_name":"建设","card_num":"6217002990101401952","own_name":"黄辉"},{"card_id":"107","bank_name":"啊啊啊","card_num":"5553","own_name":"嘿嘿"},{"card_id":"106","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"102","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"103","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"104","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"105","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"99","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"100","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"101","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"98","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"97","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"92","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"93","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"94","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"95","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"96","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"88","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"}]
     */

    private InfoBean info;
    /**
     * info : {"page":1,"count":98,"pagesize":20,"page_count":5,"list":[{"card_id":"110","bank_name":"uuuu","card_num":"4444","own_name":"dddd"},{"card_id":"109","bank_name":"yyyyyyyy","card_num":"1111","own_name":"tyyuy"},{"card_id":"108","bank_name":"建设","card_num":"6217002990101401952","own_name":"黄辉"},{"card_id":"107","bank_name":"啊啊啊","card_num":"5553","own_name":"嘿嘿"},{"card_id":"106","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"102","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"103","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"104","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"105","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"99","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"100","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"101","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"98","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"97","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"92","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"93","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"94","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"95","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"96","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"},{"card_id":"88","bank_name":"中国建设","card_num":"6217002990101401952","own_name":"黄大帅"}]}
     * status : 1
     * url :
     */

    private String status;
    private String url;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class InfoBean {
        private int page;
        private int count;
        private int pagesize;
        private int page_count;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * card_id : 110
         * bank_name : uuuu
         * card_num : 4444
         * own_name : dddd
         */

        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String card_id;
            private String bank_name;
            private String card_num;
            private String own_name;

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getOwn_name() {
                return own_name;
            }

            public void setOwn_name(String own_name) {
                this.own_name = own_name;
            }
        }
    }
}
