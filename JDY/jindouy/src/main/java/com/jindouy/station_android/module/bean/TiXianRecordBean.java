package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 *
 * 提现记录
 */

public class TiXianRecordBean {
    /**
     * count : 9
     * list : [{"id":"21","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:32"},{"id":"20","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:27"},{"id":"19","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:26"},{"id":"18","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:13"},{"id":"5","money":2,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-09 09:19"},{"id":"4","money":1,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-08 09:30"},{"id":"3","money":2,"pay_status":"2","pay_status_name":"提现失败","time":"2016-11-08 09:09"},{"id":"2","money":2,"pay_status":"1","pay_status_name":"提现成功","time":"2016-11-08 09:09"},{"id":"1","money":2,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-07 21:12"}]
     * page : 1
     * page_count : 1
     * pagesize : 20
     */
    private InfoBean info;
    /**
     * info : {"count":9,"list":[{"id":"21","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:32"},{"id":"20","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:27"},{"id":"19","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:26"},{"id":"18","money":0.01,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-14 17:13"},{"id":"5","money":2,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-09 09:19"},{"id":"4","money":1,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-08 09:30"},{"id":"3","money":2,"pay_status":"2","pay_status_name":"提现失败","time":"2016-11-08 09:09"},{"id":"2","money":2,"pay_status":"1","pay_status_name":"提现成功","time":"2016-11-08 09:09"},{"id":"1","money":2,"pay_status":"0","pay_status_name":"审核中","time":"2016-11-07 21:12"}],"page":1,"page_count":1,"pagesize":20}
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
        private int count;
        private int page;
        private int page_count;
        private int pagesize;
        /**
         * id : 21
         * money : 0.01
         * pay_status : 0
         * pay_status_name : 审核中
         * time : 2016-11-14 17:32
         */

        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String id;
            private String money;
            private String pay_status;
            private String pay_status_name;
            private String time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getPay_status_name() {
                return pay_status_name;
            }

            public void setPay_status_name(String pay_status_name) {
                this.pay_status_name = pay_status_name;
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
