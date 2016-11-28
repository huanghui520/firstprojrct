package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/19
 * 所有包裹
 */
public class AllPackPagerBean {

    /**
     * count : 2
     * list : [{"add_time":"2016-10-24 15:29","category":"2","id":"2","logistics":[{"date_num":"2016-11-15","info":"快件到达霍营小区代售点","time_num":"20:20"},{"date_num":"2016-11-14","info":"快件到达","time_num":"18:46"},{"date_num":"2016-10-17","info":"快件到达霍营小区代售点","time_num":"18:41"}],"order_total":"2件","packname":"dd","site_name":"北京昌平回龙观基站2","usertype":"1","weight":"4kg"},{"add_time":"2016-11-17 19:54","appear":"张小","category":"1","e_address":"北新桥地铁站","e_city":"北京 东城区","id":"3","logistics":[],"o_class":"数码产品","s_address":"安定门地铁站","s_city":"北京 东城区","username":"小花","weight":"2kg"}]
     * page : 1
     * page_count : 1
     * pagesize : 20
     */
    private InfoBean info;
    /**
     * info : {"count":2,"list":[{"add_time":"2016-10-24 15:29","category":"2","id":"2","logistics":[{"date_num":"2016-11-15","info":"快件到达霍营小区代售点","time_num":"20:20"},{"date_num":"2016-11-14","info":"快件到达","time_num":"18:46"},{"date_num":"2016-10-17","info":"快件到达霍营小区代售点","time_num":"18:41"}],"order_total":"2件","packname":"dd","site_name":"北京昌平回龙观基站2","usertype":"1","weight":"4kg"},{"add_time":"2016-11-17 19:54","appear":"张小","category":"1","e_address":"北新桥地铁站","e_city":"北京 东城区","id":"3","logistics":[],"o_class":"数码产品","s_address":"安定门地铁站","s_city":"北京 东城区","username":"小花","weight":"2kg"}],"page":1,"page_count":1,"pagesize":20}
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
        private int count;
        private int page;
        private int page_count;
        private int pagesize;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * add_time : 2016-10-24 15:29
         * category : 2
         * id : 2
         * logistics : [{"date_num":"2016-11-15","info":"快件到达霍营小区代售点","time_num":"20:20"},{"date_num":"2016-11-14","info":"快件到达","time_num":"18:46"},{"date_num":"2016-10-17","info":"快件到达霍营小区代售点","time_num":"18:41"}]
         * order_total : 2件
         * packname : dd
         * site_name : 北京昌平回龙观基站2
         * usertype : 1
         * weight : 4kg
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
            private String add_time;
            private String category;
            private String id;
            private String order_total;
            private String packname;
            private String site_name;
            private String usertype;
            private String weight;
            private String ordernum;
            private String username;
            private String appear;
            private String o_class;
            private String s_city;
            private String e_city;
            private String s_address;
            private String e_address;

            public String getS_address() {
                return s_address;
            }

            public void setS_address(String s_address) {
                this.s_address = s_address;
            }

            public String getE_address() {
                return e_address;
            }

            public void setE_address(String e_address) {
                this.e_address = e_address;
            }

            public String getE_city() {
                return e_city;
            }

            public void setE_city(String e_city) {
                this.e_city = e_city;
            }

            public String getS_city() {
                return s_city;
            }

            public void setS_city(String s_city) {
                this.s_city = s_city;
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

            public String getOrdernum() {
                return ordernum;
            }

            public void setOrdernum(String ordernum) {
                this.ordernum = ordernum;
            }

            /**
             * date_num : 2016-11-15
             * info : 快件到达霍营小区代售点
             * time_num : 20:20
             */

            private List<LogisticsBean> logistics;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_total() {
                return order_total;
            }

            public void setOrder_total(String order_total) {
                this.order_total = order_total;
            }

            public String getPackname() {
                return packname;
            }

            public void setPackname(String packname) {
                this.packname = packname;
            }

            public String getSite_name() {
                return site_name;
            }

            public void setSite_name(String site_name) {
                this.site_name = site_name;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public List<LogisticsBean> getLogistics() {
                return logistics;
            }

            public void setLogistics(List<LogisticsBean> logistics) {
                this.logistics = logistics;
            }

            public static class LogisticsBean {
                private String date_num;
                private String info;
                private String time_num;

                public String getDate_num() {
                    return date_num;
                }

                public void setDate_num(String date_num) {
                    this.date_num = date_num;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getTime_num() {
                    return time_num;
                }

                public void setTime_num(String time_num) {
                    this.time_num = time_num;
                }
            }
        }
    }
}
