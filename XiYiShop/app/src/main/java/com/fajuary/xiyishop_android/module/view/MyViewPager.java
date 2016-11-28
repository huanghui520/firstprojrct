package com.fajuary.xiyishop_android.module.view;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
/**
 * created by huanghui at 2016/11/7
 */
public class MyViewPager extends ViewPager {
    private static final int DEFAULT_OFFSCREEN_PAGES = 0;
    public MyViewPager(Context context) {
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
