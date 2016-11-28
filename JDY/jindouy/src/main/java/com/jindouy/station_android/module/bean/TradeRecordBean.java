package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 *
 * 交易记录
 */

public class TradeRecordBean {

    /**
     * page : 1
     * count : 2
     * pagesize : 20
     * page_count : 1
     * list : [{"id":"5","rel_type":"1","rel_type_name":"收取快件","money":1,"money_name":"+1元","time":"2016-11-08 09:30"},{"id":"6","rel_type":"1","rel_type_name":"收取快件","money":1.1,"money_name":"+1.1元","time":"2016-11-08 09:30"}]
     */

    private InfoBean info;
    /**
     * info : {"page":1,"count":2,"pagesize":20,"page_count":1,"list":[{"id":"5","rel_type":"1","rel_type_name":"收取快件","money":1,"money_name":"+1元","time":"2016-11-08 09:30"},{"id":"6","rel_type":"1","rel_type_name":"收取快件","money":1.1,"money_name":"+1.1元","time":"2016-11-08 09:30"}]}
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
        /**
         * id : 5
         * rel_type : 1
         * rel_type_name : 收取快件
         * money : 1
         * money_name : +1元
         * time : 2016-11-08 09:30
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
            private String id;
            private String rel_type;
            private String rel_type_name;
            private int money;
            private String money_name;
            private String time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRel_type() {
                return rel_type;
            }

            public void setRel_type(String rel_type) {
                this.rel_type = rel_type;
            }

            public String getRel_type_name() {
                return rel_type_name;
            }

            public void setRel_type_name(String rel_type_name) {
                this.rel_type_name = rel_type_name;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getMoney_name() {
                return money_name;
            }

            public void setMoney_name(String money_name) {
                this.money_name = money_name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
