package com.fajuary.xiyishop_android;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bugtags.library.Bugtags;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fajuary.myapp.MyBaseActivity;
import com.fajuary.myapp.utils.PreferenceUtil;
import com.fajuary.xiyishop_android.utils.SmallFeatureUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.orhanobut.logger.Logger;


/**
 * Created 张朋飞 on 2016/7/25.
 * user 864598192
 * <p/>
 * 没网状态返回值-1
 * 数据网络连接为0
 * wifi连接为1
 */
public abstract class BaseActivity extends MyBaseActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    protected PreferenceUtil preferenceUtil;
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
                  Manifest.permission.RECORD_AUDIO,
                  Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    private GestureDetector mGestureDetector;
    public RequestManager glideRequest;

    public void createView() {
        glideRequest = Glide.with(this);

        getSupportActionBar().hide();
        // 缺少权限时, 进入权限配置页面
        BeapakeShopApp.getInstance().addActivity(this);
        preferenceUtil = new PreferenceUtil(this);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);

        createLayout();
        initEvent();
    }


    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
//        Bugtags.onPause(this);
    }


    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if ( getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
//        Bugtags.onResume(this);

        super.onResume();
    }

    public abstract void createLayout();

    public abstract void initEvent();

    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
        BeapakeShopApp.getInstance().finishActivity(this);

    }

    public interface OnNetStatesInterface {
        void onFragmentNetChange(int netModile);
    }

    @Override
    public void onNetChange(int netModile) {
        super.onNetChange(netModile); //没网-1  数据网络连接为0 wifi连接1
        if ( netModile == -1 ) {
            SmallFeatureUtils.getNetDialog(this);
        }
        if ( onNetStatesInterface != null ) {
            onNetStatesInterface.onFragmentNetChange(netModile);

        }
    }


    public static OnNetStatesInterface onNetStatesInterface;

    public static void setOnNetStatesInterface(OnNetStatesInterface onNetStatesInterface) {
        BaseActivity.onNetStatesInterface = onNetStatesInterface;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if ( ev.getAction() == MotionEvent.ACTION_DOWN ) {
            View v = getCurrentFocus();
            if ( isShouldHideInput(v, ev) ) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if ( imm != null ) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if ( getWindow().superDispatchTouchEvent(ev) ) {
            return true;
        }
//        Bugtags.onDispatchTouchEvent(this, ev);

        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if ( v != null && (v instanceof EditText) ) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if ( event.getX() > left && event.getX() < right
                          && event.getY() > top && event.getY() < bottom ) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Notified when a tap occurs with the down {@link MotionEvent}
     * that triggered it. This will be triggered immediately for
     * every down event. All other events should be preceded by this.
     *
     * @param e
     *               The down motion event.
     */
    @Override
    public boolean onDown(MotionEvent e) {
        Logger.e("手势onDown");
        return false;
    }

    /**
     * The user has performed a down {@link MotionEvent} and not performed
     * a move or up yet. This event is commonly used to provide visual
     * feedback to the user to let them know that their action has been
     * recognized i.e. highlight an element.
     *
     * @param e
     *               The down motion event
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Logger.e("手势onShowPress");
    }

    /**
     * Notified when a tap occurs with the up {@link MotionEvent}
     * that triggered it.
     *
     * @param e
     *               The up motion event that completed the first tap
     *
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Logger.e("手势onSingleTapUp");
        return false;
    }

    /**
     * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
     * current move {@link MotionEvent}. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1
     *               The first down motion event that started the scrolling.
     * @param e2
     *               The move motion event that triggered the current onScroll.
     * @param distanceX
     *               The distance along the X axis that has been scrolled since the last
     *               call to onScroll. This is NOT the distance between {@code e1}
     *               and {@code e2}.
     * @param distanceY
     *               The distance along the Y axis that has been scrolled since the last
     *               call to onScroll. This is NOT the distance between {@code e1}
     *               and {@code e2}.
     *
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * Notified when a long press occurs with the initial on down {@link MotionEvent}
     * that trigged it.
     *
     * @param e
     *               The initial on down motion event that started the longpress.
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Logger.e("手势onLongPress");
    }

    private int verticalMinDistance = 20;
    private int minVelocity = 0;

    /**
     * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
     * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
     * the x and y axis in pixels per second.
     *
     * @param e1
     *               The first down motion event that started the fling.
     * @param e2
     *               The move motion event that triggered the current onFling.
     * @param velocityX
     *               The velocity of this fling measured in pixels per second
     *               along the x axis.
     * @param velocityY
     *               The velocity of this fling measured in pixels per second
     *               along the y axis.
     *
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if ( e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity ) {

            Logger.e("向左手势");

        } else if ( (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) ) {

            Logger.e("向右手势");
//            Activity activity=rec
//            RecruitToJoinApp.getInstance().finishActivity(this);

//            List<AppCompatActivity> activities=RecruitToJoinApp.getInstance().getActivities();
//            if ( activities!=null ){
//                for ( int i=0;i<activities.size();i++ ){
//                    AppCompatActivity activity=activities.get(i);
//                    if ( activity instanceof MainActivity ){
//                    }else {
//                        Logger.e(activity.toString());
//                        RecruitToJoinApp.getInstance().finishActivity(activity);
//
//                    }
//
//                }
//            }


        }

        return false;
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v
     *               The view the touch event has been dispatched to.
     * @param event
     *               The MotionEvent object containing full information about
     *               the event.
     *
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

}
