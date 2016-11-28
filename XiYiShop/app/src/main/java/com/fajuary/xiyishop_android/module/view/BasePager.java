package com.fajuary.xiyishop_android.module.view;

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
    public View rootView;//获取的视图
    public Context context;
    //构造方法
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
