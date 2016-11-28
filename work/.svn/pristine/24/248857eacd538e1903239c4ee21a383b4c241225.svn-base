package com.fajuary.xiyishop_android;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.fajuary.xiyishop_android.mymodule.fragment.MyFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void createLayout() {
        setContentView(R.layout.activity_main);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.activity_main_framelayout,  MyFragment.newInstance("my"));
        beginTransaction.commit();
    }
    @Override
    public void initEvent() {

    }
    @Override
    public void widgetClick(View view) {

    }
    @Override
    public boolean getIsFastClick() {
        return false;
    }

}
