package com.fajuary.xiyishop_android;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.fajuary.myapp.utils.PreferenceUtil;
import com.fajuary.myapp.widget.MyBaseFragment;


/**
 * Created 张朋飞 on 2016/9/5.
 * user 864598192
 */
public abstract class BaseFragment extends MyBaseFragment implements View.OnClickListener {
    public abstract void initEvent();

    protected abstract void createView(View view, Bundle bundle);

    public DrawerLayout drawerLayout;
    public ListView actListview;

    @Override
    protected void initView(View view, Bundle bundle) {
        preferenceUtil = new PreferenceUtil(mActivity);
        createView(view, bundle);
//        if(mActivity instanceof MainActivity ){
//            drawerLayout= ((MainActivity)mActivity).getDrawerLayout();
//            actListview= ((MainActivity)mActivity).getListView();
//        }

        initEvent();

    }

    protected PreferenceUtil preferenceUtil;
}
