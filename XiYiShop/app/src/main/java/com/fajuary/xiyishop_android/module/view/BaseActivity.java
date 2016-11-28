package com.fajuary.xiyishop_android.module.view;

import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;

/**
 * created by huanghui at 2016/11/3
 * activity 的基类
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
}