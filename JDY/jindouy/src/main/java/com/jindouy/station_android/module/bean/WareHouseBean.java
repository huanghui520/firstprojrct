package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/23
 */

public class WareHouseBean {

    /**
     * page : 1
     * count : 2
     * pagesize : 20
     * page_count : 1
     * list : [{"id":"2","order_id":"3","ordernum":"1232","category":"2","packname":"dd","weight":"4kg","usertype":"1","site_name":"北京昌平回龙观基站2","order_total":"2件","o_status":"1","o_status_name":"正在本站","logistics":[{"date_num":"2016-11-15","time_num":"20:20","info":"快件到达霍营小区代售点"},{"date_num":"2016-11-14","time_num":"18:46","info":"快件到达"},{"date_num":"2016-10-17","time_num":"18:41","info":"快件到达霍营小区代售点"}],"add_time":"2016-10-24 15:29"},{"id":"3","order_id":"26","ordernum":"14793836755423281","category":"1","username":"小花","appear":"张小","weight":"2kg","o_class":"数码产品","s_city":"北京 东城区","e_city":"北京 东城区","s_address":"安定门地铁站","e_address":"北新桥地铁站","o_status":"1","o_status_name":"正在本站","add_time":"2016-11-17 19:54","logistics":[]}]
     */

    private InfoBean info;
    /**
     * info : {"page":1,"count":2,"pagesize":20,"page_count":1,"list":[{"id":"2","order_id":"3","ordernum":"1232","category":"2","packname":"dd","weight":"4kg","usertype":"1","site_name":"北京昌平回龙观基站2","order_total":"2件","o_status":"1","o_status_name":"正在本站","logistics":[{"date_num":"2016-11-15","time_num":"20:20","info":"快件到达霍营小区代售点"},{"date_num":"2016-11-14","time_num":"18:46","info":"快件到达"},{"date_num":"2016-10-17","time_num":"18:41","info":"快件到达霍营小区代售点"}],"add_time":"2016-10-24 15:29"},{"id":"3","order_id":"26","ordernum":"14793836755423281","category":"1","username":"小花","appear":"张小","weight":"2kg","o_class":"数码产品","s_city":"北京 东城区","e_city":"北京 东城区","s_address":"安定门地铁站","e_address":"北新桥地铁站","o_status":"1","o_status_name":"正在本站","add_time":"2016-11-17 19:54","logistics":[]}]}
     * status : 1
     * url :
     */

    private int status;
    private String url;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         *
         * id : 2
         * order_id : 3
         * ordernum : 1232
         * category : 2
         * packname : dd
         * weight : 4kg
         * usertype : 1
         * site_name : 北京昌平回龙观基站2
         * order_total : 2件
         * o_status : 1
         * o_status_name : 正在本站
         * logistics : [{"date_num":"2016-11-15","time_num":"20:20","info":"快件到达霍营小区代售点"},{"date_num":"2016-11-14","time_num":"18:46","info":"快件到达"},{"date_num":"2016-10-17","time_num":"18:41","info":"快件到达霍营小区代售点"}]
         * add_time : 2016-10-24 15:29
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
            private String order_id;
            private String ordernum;
            private String category;
            private String packname;
            private String weight;
            private String usertype;
            private String site_name;
            private String order_total;
            private String o_status;
            private String o_status_name;
            private String add_time;
            private String username;
            private String appear;
            private String s_city;
            private String e_city;
            private String o_class;

            public String getS_city() {
                return s_city;
            }
            public void setS_city(String s_city) {
                this.s_city = s_city;
            }
            public String getE_city() {
                return e_city;
            }
            public void setE_city(String e_city) {
                this.e_city = e_city;
            }

            public String getO_class() {
                return o_class;
            }

            public void setO_class(String o_class) {
                this.o_class = o_class;
            }

            public String getAppear() {
                return appear;
            }

            public void setAppear(String appear) {
                this.appear = appear;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            /**
             * date_num : 2016-11-15
             * time_num : 20:20
             * info : 快件到达霍营小区代售点
             */

            private List<LogisticsBean> logistics;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrdernum() {
                return ordernum;
            }

            public void setOrdernum(String ordernum) {
                this.ordernum = ordernum;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPackname() {
                return packname;
            }

            public void setPackname(String packname) {
                this.packname = packname;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getSite_name() {
                return site_name;
            }

            public void setSite_name(String site_name) {
                this.site_name = site_name;
            }

            public String getOrder_total() {
                return order_total;
            }

            public void setOrder_total(String order_total) {
                this.order_total = order_total;
            }

            public String getO_status() {
                return o_status;
            }

            public void setO_status(String o_status) {
                this.o_status = o_status;
            }

            public String getO_status_name() {
                return o_status_name;
            }

            public void setO_status_name(String o_status_name) {
                this.o_status_name = o_status_name;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public List<LogisticsBean> getLogistics() {
                return logistics;
            }

            public void setLogistics(List<LogisticsBean> logistics) {
                this.logistics = logistics;
            }

            public static class LogisticsBean {
                private String date_num;
                private String time_num;
                private String info;

                public String getDate_num() {
                    return date_num;
                }

                public void setDate_num(String date_num) {
                    this.date_num = date_num;
                }

                public String getTime_num() {
                    return time_num;
                }

                public void setTime_num(String time_num) {
                    this.time_num = time_num;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }
        }
    }
}
