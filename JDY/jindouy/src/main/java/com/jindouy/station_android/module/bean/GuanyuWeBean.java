package com.jindouy.station_android.module.bean;

/**
 * created by huanghui at 2016/11/14
 * 关于我们
 */

public class GuanyuWeBean {

    /**
     * info : 斤斗云（北京）网络科技有限公司（www.jindouy.cn），成立于2016年8月3日，是中国第一家基于众包模式，由多人合作配送的快递公司。
     斤斗云倡导节能减排、绿化环保，在极大提高运输时效的基础上，更是大大降低了运输成本。
     斤斗云的出现不仅增加了社会上闲散人员的收入，整合了社会的资源，更是对众包模式的创新。通过提供更加灵活接单路径，让承运人的接单几率大大提高。
     众多的承运人给用户带来的不仅是极速、低廉的配送服务，更是突破了快递的配送时间和成本，为快递行业的发展注入了新的力量。
     * status : 1
     * url :
     */

    private String info;
    private String status;
    private String url;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
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
}
