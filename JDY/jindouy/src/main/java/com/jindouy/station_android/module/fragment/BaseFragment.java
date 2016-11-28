package com.jindouy.station_android.module.fragment;/**
 * 作者：huanghui on 2016/11/3 15:45
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * created by huanghui at 2016/11/3
 */

public abstract class BaseFragment extends Fragment {
    public Context context;

    //当fragment创建时调用此方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    //当view创建时调用此方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    //强制实现的方法
    public  abstract View initView();

    /**
     * 当Activity被创建的时候回调这个方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当孩子需要初始化数据的时候，重新该方法
     */
    public void initData() {

    }
}
