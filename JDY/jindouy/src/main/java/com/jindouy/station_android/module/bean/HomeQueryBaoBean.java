package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/24
 */

public class HomeQueryBaoBean {
    /**
     * add_time : 2016-10-24 15:29
     * category : 2
     * logistics : [{"date_num":"2016-11-15","info":"快件到达霍营小区代售点","time_num":"20:20"},{"date_num":"2016-11-14","info":"快件到达","time_num":"18:46"},{"date_num":"2016-10-17","info":"快件到达霍营小区代售点","time_num":"18:41"}]
     * order_id : 3
     * order_total : 2件
     * ordernum : 1232
     * packname : dd
     * site_name : 北京昌平回龙观基站2
     * weight : 4kg
     */

    private String add_time;
    private int category;
    private int order_id;
    private String order_total;
    private String ordernum;
    private String packname;
    private String site_name;
    private String weight;
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

    public String getOrder_total() {
        return order_total;
    }

    public void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
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
