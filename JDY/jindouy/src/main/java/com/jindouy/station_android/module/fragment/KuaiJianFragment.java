package com.jindouy.station_android.module.fragment;

import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.pager.AllPackPager;
import com.jindouy.station_android.module.activity.pager.BasePager;
import com.jindouy.station_android.module.activity.pager.KuanChengPackPager;
import com.jindouy.station_android.module.activity.pager.LocalPackPager;

import java.util.ArrayList;
/**
 * created by huanghui at 2016/11/3
 * 快件页
 */
public class KuaiJianFragment extends BaseFragment {
    private LinearLayout mIv_back;
    private LinearLayout mRl_talber;
    private RadioGroup mRg_btn;
    private RadioButton mRb_all_pack;
    private RadioButton mRb_local_pack;
    private RadioButton mRb_kuacheng_pack;
    private LinearLayout mLl_movepager;
    private android.support.v4.view.ViewPager mVp_pager_ui;
    private ArrayList<BasePager> lists;
    //两个radiogroup之间的距离
    private double mJuLi;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.kuaijian_mian, null);
        mIv_back = (LinearLayout) view.findViewById(R.id.iv_back);
        mRl_talber = (LinearLayout) view.findViewById(R.id.rl_talber);
        mRg_btn = (RadioGroup) view.findViewById(R.id.rg_btn);
        mRb_all_pack = (RadioButton) view.findViewById(R.id.rb_all_pack);
        mRb_local_pack = (RadioButton) view.findViewById(R.id.rb_local_pack);
        mRb_kuacheng_pack = (RadioButton) view.findViewById(R.id.rb_kuacheng_pack);
        mLl_movepager = (LinearLayout) view.findViewById(R.id.ll_movepager);
        mVp_pager_ui = (android.support.v4.view.ViewPager) view.findViewById(R.id.vp_pager_ui);
        return view;
    }
    //创建RadioButton数组
    private int[] mRadioButton = {R.id.rb_all_pack, R.id.rb_local_pack, R.id.rb_kuacheng_pack};

    /**
     *
     */
    @Override
    public void initData() {
        //准备数据
        lists = new ArrayList<>();
        lists.add(new AllPackPager(context));
        lists.add(new LocalPackPager(context));
        lists.add(new KuanChengPackPager(context));

        mVp_pager_ui.setAdapter(new myPagerAdapter());
        //进来初始化界面
        //选中第一个页面
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

    }
    /**
     * 适配器
     */
    private class myPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basepager = lists.get(position);
            View rootView = basepager.rootView;//子页面继承基类实现的initData方法返回的rootview
            container.addView(rootView);//子类必须都得有返回view  不然报错
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
         * @param position             当前页面的下标
         * @param positionOffset       滑动的百分比
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
            BasePager basepager = lists.get(position);
            basepager.initData();//一定要调用   不然没数据
            mRg_btn.check(mRadioButton[position]);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_all_pack:
                    mVp_pager_ui.setCurrentItem(0);
                    break;
                case R.id.rb_local_pack:
                    mVp_pager_ui.setCurrentItem(1);
                    break;
                case R.id.rb_kuacheng_pack:
                    mVp_pager_ui.setCurrentItem(2);
                    break;
            }
        }
    }
}