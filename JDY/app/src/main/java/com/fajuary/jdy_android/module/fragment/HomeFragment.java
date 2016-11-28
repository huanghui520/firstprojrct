package com.fajuary.jdy_android.module.fragment;

import android.view.View;

import com.fajuary.jdy_android.R;

/**
 * created by huanghui at 2016/11/3
 * 首页
 */

public class HomeFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_mian, null);
        return view;
    }
}
