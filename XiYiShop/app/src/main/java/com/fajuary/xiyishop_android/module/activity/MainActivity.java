package com.fajuary.xiyishop_android.module.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.fragment.MyFragment;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;

/**
 *
 *@author 黄辉
 *@time 2016/10/21 15:32
 *
 * 首页
*/
public class MainActivity extends BaseActivity {

    private FrameLayout fl_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.fg_activity_main);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        initFragment();
    }
    private void initFragment() {
        //得到fragment管理
        FragmentManager fm =  getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main,new MyFragment());
        transaction.commit();

    }
    /**
     * 调用finish方法，或者系统回收资源时调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popAllActivity();

    }


}
