package com.fajuary.xiyishop_android.module.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.module.view.BasePager;
import com.fajuary.xiyishop_android.module.view.CompletePager;
import com.fajuary.xiyishop_android.module.view.MyViewPager;
import com.fajuary.xiyishop_android.module.view.UnfinsheedPager;
import com.fajuary.xiyishop_android.tools.ActivityManager;

import java.util.ArrayList;

/**
 *
 *@author 黄辉
 *@time 2016/10/21 15:31
 *
 * 订单管理
*/
public class ToOrderManagerActivty extends BaseActivity {
    private LinearLayout mRl_talber;
    private LinearLayout mLl_movepager;
    private RadioGroup mRg_btn;
    private RadioButton mRy_btn;
    private RadioButton mDy_btn;
    private MyViewPager mVp_pager_ui;
    private LinearLayout iv_back;

    //两个radiogroup之间的距离
    private double mJuLi;
    //创建RadioButton数组
    private ArrayList<BasePager> mViewList ;//页卡视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_to_order_manager_activty);
        mRl_talber = (LinearLayout) findViewById(R.id.rl_talber);
        mLl_movepager = (LinearLayout) findViewById(R.id.ll_movepager);
        mRg_btn = (RadioGroup) findViewById(R.id.rg_btn);
        mRy_btn = (RadioButton) findViewById(R.id.wwc_btn);
        mDy_btn = (RadioButton) findViewById(R.id.wc_btn);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        mVp_pager_ui = (MyViewPager) findViewById(R.id.vp_pager_ui);
        initData();
    }
    //创建RadioButton数组
    private int[]mRadioButton = {R.id.wwc_btn, R.id.wc_btn};

    public void initData() {
        //准备数据
        mViewList = new ArrayList<>();
        mViewList.add(new UnfinsheedPager(this));
        mViewList.add(new CompletePager(this));
        mVp_pager_ui.setAdapter(new myPagerAdapter());
        //进来初始化界面
//        mViewList.get(0).initData();
        //选中
        mRg_btn.check(mRadioButton[0]);
        //设置viewpager滑动时候的监听
//        vp_pager_ui.setOnPageChangeListener();
        mVp_pager_ui.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置RadioGroup改变的监听
        mRg_btn.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //获取第一个点到第二个点需要移动的距离
        mLl_movepager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //只执行一次就可以了
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mLl_movepager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mJuLi = mRg_btn.getChildAt(1).getLeft() - mRg_btn.getChildAt(0).getLeft();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private class myPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViewList.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basepager = mViewList.get(position);
            View rootView = basepager.rootView;//子页面继承基类实现的initData方法返回的rootview
            container.addView(rootView);
            return rootView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * viewpager滑动的监听  得到屏幕滑动的百分比
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         *
         * @param position  当前页面的下标
         * @param positionOffset 滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int i = (int) ((position + positionOffset) * mJuLi);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.MATCH_PARENT);
            params.leftMargin = i;
            //没移动一点就需要重新绘制
            mLl_movepager.setLayoutParams(params);

        }
        @Override
        public void onPageSelected(int position) {
            BasePager basepager = mViewList.get(position);
            basepager.initData();//一定要调用   不然没数据
            //rg_main.check(position);不行
            mRg_btn.check(mRadioButton[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.wwc_btn:
                    mVp_pager_ui.setCurrentItem(0);
                    break;
                case R.id.wc_btn:
                    mVp_pager_ui.setCurrentItem(1);
                    break;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
