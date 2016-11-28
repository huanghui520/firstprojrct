package com.jindouy.station_android.module.bean;

import java.util.List;

/**
 * created by huanghui at 2016/11/24
 *
 */

public class HomeQueryKuaiJianBean {

    /**
     * add_time : 2016-11-17 19:53
     * appear : 收件
     * category : 1
     * e_address : 安定门地铁站
     * e_city : 北京 东城区
     * logistics : []
     * o_class : 数码产品
     * order_id : 25
     * ordernum : 14793836755423281
     * s_address : 北新桥地铁站
     * s_city : 北京 东城区
     * username : 张小
     * weight : 2kg
     */

    private String add_time;
    private String appear;
    private int category;
    private String e_address;
    private String e_city;
    private String o_class;
    private int order_id;
    private String ordernum;
    private String s_address;
    private String s_city;
    private String username;
    private String weight;
    private List<LogisticsBean> logistics;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAppear() {
        return appear;
    }

    public void setAppear(String appear) {
        this.appear = appear;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public String getO_class() {
        return o_class;
    }

    public void setO_class(String o_class) {
        this.o_class = o_class;
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

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_city() {
        return s_city;
    }

    public void setS_city(String s_city) {
        this.s_city = s_city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
