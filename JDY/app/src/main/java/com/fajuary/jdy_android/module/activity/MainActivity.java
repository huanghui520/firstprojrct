package com.fajuary.jdy_android.module.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.fajuary.jdy_android.R;
import com.fajuary.jdy_android.module.fragment.BaseFragment;
import com.fajuary.jdy_android.module.fragment.HomeFragment;
import com.fajuary.jdy_android.module.fragment.KuaiJianFragment;
import com.fajuary.jdy_android.module.fragment.MyFragment;
import com.fajuary.jdy_android.module.fragment.StorehouseFragment;

import java.util.ArrayList;
/**
 *
 *@author 黄辉
 *@time 2016/11/3 15:36
 *
 * 首页
 *
*/
public class MainActivity extends FragmentActivity {
    private FrameLayout fl_main;
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> list;
    private int mPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_main = (FrameLayout)findViewById(R.id.fl_main);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new KuaiJianFragment());
        list.add(new StorehouseFragment());
        list.add(new MyFragment());
        //RadioGroup 赋值
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_move:
                        mPosition = 0;
                        break;
                    case R.id.rg_cinema:
                        mPosition = 1;
                        break;
                    case R.id.rb_find:
                        mPosition = 2;
                        break;
                    case R.id.rb_my:
                        mPosition = 3;
                        break;
                }
                initFragment();//在点击的时候就调用
            }
        });
        rg_main.check(R.id.rb_move);//默认为首页
    }
    private void initFragment() {
        //得到fragment管理
        FragmentManager fm =  getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main,list.get(mPosition));
        transaction.commit();

    }
}
