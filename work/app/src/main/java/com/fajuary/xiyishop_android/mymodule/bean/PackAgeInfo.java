package com.fajuary.xiyishop_android.mymodule.bean;

/**
 * Created 张朋飞 on 2016/9/6.
 * user 864598192
 */
public class PackAgeInfo {
    private String name;
    private String states;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "PackAgeInfo{" +
                      "name='" + name + '\'' +
                      ", states='" + states + '\'' +
                      '}';
    }
}
