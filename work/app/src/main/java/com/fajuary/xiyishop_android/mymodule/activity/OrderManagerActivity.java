package com.fajuary.xiyishop_android.mymodule.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.BaseFragment;
import com.fajuary.xiyishop_android.BeapakeShopApp;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.adapter.MyFragmentAdapter;
import com.fajuary.xiyishop_android.mymodule.fragment.MyOrderRecordFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 订单管理
 */
public class OrderManagerActivity extends BaseActivity {


    @Bind(R.id.fragment_head_leftlayout)
    LinearLayout leftlayout;
    @Bind(R.id.fragment_head_leftimg)
    ImageView leftimg;

    @Bind(R.id.fragment_head_rightlayout)
    LinearLayout rightlayout;
    @Bind(R.id.fragment_head_rightimg)
    ImageView rightimg;
//    @Bind(R.id.fragment_head_centtxt)
//    ImageView centimg;

    @Bind(R.id.activity_myordertype_rdgrp)
    RadioGroup radioGroup;

//    @Bind(R.id.activity_myordertype_orderallrdbtn)
//    RadioButton orderallrdbtn;
    @Bind(R.id.activity_myordertype_ordernocomplectrdbtn)
    RadioButton ordernocomplectrdbtn;
    @Bind(R.id.activity_myordertype_ordercomplectrdbtn)
    RadioButton ordercomplectrdbtn;

    @Bind(R.id.activity_myordertype_orderLin)
    LinearLayout orderLin;

    @Bind(R.id.activity_myordertype_viewpager)
    ViewPager viewPager;
    private int currIndex;//当前页卡编号
    private ArrayList<BaseFragment> fragmentList;

    public void createLayout() {
        setContentView(R.layout.activity_order_manager);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public void initEvent() {
        leftimg.setImageResource(R.mipmap.blackleft);
//        rightimg.setImageResource(R.mipmap.msg);
        rightlayout.setVisibility(View.INVISIBLE);
        leftlayout.setOnClickListener(this);
        leftimg.setOnClickListener(this);
//        float densityDpi= SmallFeatureUtils.getDensityDpi(this);
//        Logger.e("densityDpi-->"+densityDpi);
//        ViewGroup.LayoutParams imgparms= SmallFeatureUtils.getParms(centimg);
//        imgparms.height= (int) (26*densityDpi);
//        imgparms.width= (int) (99*densityDpi);
//        centimg.setLayoutParams(imgparms);
//        centimg.setImageResource(R.mipmap.order_history);


//        orderallrdbtn.setOnClickListener(this);
        ordernocomplectrdbtn.setOnClickListener(this);
        ordercomplectrdbtn.setOnClickListener(this);
    }
    private void setViewBgcolor(int position){
        for ( int i=0;i<orderLin.getChildCount();i++ ){
            View view=orderLin.getChildAt(i);
            if ( i==position ){
                view.setBackgroundColor(getResources().getColor(R.color.zixun_color));
            }else {
                view.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }
    private void initData(){

        fragmentList = new ArrayList<BaseFragment>();
//        fragmentList.add(MyOrderRecordFragment.newInstance("all"));
        fragmentList.add(MyOrderRecordFragment.newInstance("complete"));
        fragmentList.add(MyOrderRecordFragment.newInstance("nocomplete"));

        //给ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器

    }
    @Override
    public void widgetClick(View view) {
        switch ( view.getId() ){
            case R.id.fragment_head_leftlayout:
                finish();
            case R.id.fragment_head_leftimg:
                BeapakeShopApp.getInstance().finishActivity(this);
                break;
//            case R.id.activity_myordertype_orderallrdbtn:
//                setViewBgcolor(0);
//                viewPager.setCurrentItem(0);
//
//                break;
            case R.id.activity_myordertype_ordernocomplectrdbtn:
                setViewBgcolor(0);
                viewPager.setCurrentItem(0);

                break;
            case R.id.activity_myordertype_ordercomplectrdbtn:
                setViewBgcolor(1);
                viewPager.setCurrentItem(1);
                break;
        }
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            viewPager.setCurrentItem(position);
            setViewBgcolor(position);

        }
    }

    @Override
    public boolean getIsFastClick() {
        return false;
    }


}
