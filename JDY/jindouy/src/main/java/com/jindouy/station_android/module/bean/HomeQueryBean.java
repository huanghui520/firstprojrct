package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/24
 */

public class HomeQueryBean {


    /**
     * category : 1
     * order_id : 25
     * ordernum : 14793836755423281
     * username : 张小
     * appear : 收件
     * weight : 2kg
     * o_class : 数码产品
     * s_city : 北京 东城区
     * e_city : 北京 东城区
     * s_address : 北新桥地铁站
     * e_address : 安定门地铁站
     * add_time : 2016-11-17 19:53
     * logistics : []
     */

    private InfoBean info;
    /**
     * info : {"category":1,"order_id":25,"ordernum":"14793836755423281","username":"张小","appear":"收件","weight":"2kg","o_class":"数码产品","s_city":"北京 东城区","e_city":"北京 东城区","s_address":"北新桥地铁站","e_address":"安定门地铁站","add_time":"2016-11-17 19:53","logistics":[]}
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
        private int category;
        private int order_id;
        private String ordernum;
        private String username;
        private String appear;
        private String weight;
        private String o_class;
        private String s_city;
        private String e_city;
        private String s_address;
        private String e_address;
        private String add_time;
        private List<LogisticsBean> logistics;
        private String msg;
        private String packname;
        private String site_name;
        private String order_total;

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

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAppear() {
            return appear;
        }

        public void setAppear(String appear) {
            this.appear = appear;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getO_class() {
            return o_class;
        }

        public void setO_class(String o_class) {
            this.o_class = o_class;
        }

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
