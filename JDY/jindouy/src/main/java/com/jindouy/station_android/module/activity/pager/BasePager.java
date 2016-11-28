package com.jindouy.station_android.module.activity.pager;

import android.content.Context;
import android.view.View;

/**
 *
 *@author 黄辉
 *@time 2016/10/11 22:37
 *
 *
*/
public abstract class BasePager {
    public View rootView;
    public   Context context;

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    //加载布局的方法
    public abstract View initView();

    //子类需要时调用的方法
    public void initData() {

    }
}
