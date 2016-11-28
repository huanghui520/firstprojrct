package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/19
 *
 * 设备的id
 */

public class PushAgentBean {
    private String token;//集成友盟推送获取设备的id

    public PushAgentBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PushAgentBean{" +
                "token='" + token + '\'' +
                '}';
    }
}
