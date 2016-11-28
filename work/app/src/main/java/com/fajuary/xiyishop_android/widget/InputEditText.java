package com.fajuary.xiyishop_android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created 张朋飞 on 2016/7/27.
 * user 864598192
 */
public class InputEditText extends EditText implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {

    private boolean isIconLeft;
    private Drawable[] drawables;
    private int eventX;
    private int eventY;
    private Rect rect;
    private Drawable drawableLeft;
    /**
     * 是否点击软键盘搜索
     */
    private boolean pressSearch = false;
    public InputEditText(Context context) {
        super(context);
        this.init();
    }

    public InputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public InputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isIconLeft = false;
        this.init();
    }


    /**
     * Called when the focus state of a view has changed.
     *
     * @param v
     *               The view whose state has changed.
     * @param hasFocus
     *               The new focus state of v.
     *
     *               影响首页的效果
     *               待修改
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if( TextUtils.isEmpty(this.getText().toString())) {
            this.isIconLeft = hasFocus;
        }
    }

    /**
     * Called when a hardware key is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     * <p>Key presses in software keyboards will generally NOT trigger this method,
     * although some may elect to do so in some situations. Do not assume a
     * software input method has to be key-based; even if it is, it may use key presses
     * in a different way than you expect, so there is no way to reliably catch soft
     * input key presses.
     *
     * @param v
     *               The view the key has been dispatched to.
     * @param keyCode
     *               The code for the physical key that was pressed
     * @param event
     *               The KeyEvent object containing full information about
     *               the event.
     *
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        this.pressSearch = keyCode == 66;
        if(this.pressSearch && this.listener != null) {
            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService("input_method");
            if(imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }

            this.listener.onSearchClick(v);
        }

        return false;
    }
    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }
    /**
     * 软键盘搜索键监听
     */
    private OnSearchClickListener listener;

    public interface OnSearchClickListener {
        void onSearchClick(View view);
    }
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {


    }

    private Drawable drawableDel;

    private void init() {
        this.setOnFocusChangeListener(this);
        this.setOnKeyListener(this);
        this.addTextChangedListener(this);
    }

    protected void onDraw(Canvas canvas) {
        if ( this.isIconLeft ) {
            if ( this.length() < 1 ) {
                this.drawableDel = null;
            }

//            this.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.drawableDel, (Drawable) null);
            super.onDraw(canvas);
        } else {
//            if ( this.drawables == null ) {
//                this.drawables = this.getCompoundDrawables();
//            }
//
////            if(this.drawableLeft == null) {
////                this.drawableLeft = this.drawables[0];
////            }
//
//            float textWidth = this.getPaint().measureText(this.getHint().toString());
////            int drawablePadding = this.getCompoundDrawablePadding();
////            int drawableWidth = this.drawableLeft.getIntrinsicWidth();
//            float bodyWidth = textWidth;// + (float)drawableWidth + (float)drawablePadding;
//
//            canvas.translate(((float) this.getWidth() - bodyWidth - (float) this.getPaddingLeft() - (float) this.getPaddingRight()) / 3.0F, 0.0F);
            super.onDraw(canvas);
        }

    }
    public boolean onTouchEvent(MotionEvent event) {
        if(this.drawableDel != null && event.getAction() == 1) {
            this.eventX = (int)event.getRawX();
            this.eventY = (int)event.getRawY();
            Log.i("SearchEditText", "eventX = " + this.eventX + "; eventY = " + this.eventY);
            if(this.rect == null) {
                this.rect = new Rect();
            }

            this.getGlobalVisibleRect(this.rect);
            this.rect.left = this.rect.right - this.drawableDel.getIntrinsicWidth();
            if(this.rect.contains(this.eventX, this.eventY)) {
                this.setText("");
            }
        }

        if(this.drawableDel != null && event.getAction() == 0) {
            this.eventX = (int)event.getRawX();
            this.eventY = (int)event.getRawY();
            Log.i("SearchEditText", "eventX = " + this.eventX + "; eventY = " + this.eventY);
            if(this.rect == null) {
                this.rect = new Rect();
            }

            this.getGlobalVisibleRect(this.rect);
            this.rect.left = this.rect.right - this.drawableDel.getIntrinsicWidth();
            if(this.rect.contains(this.eventX, this.eventY)) {
//                this.drawableDel = this.getResources().getDrawable(com.fajuary.myapplibrary.R.mipmap.edit_delete_pressed_icon);
            }
        } else {
//            this.drawableDel = this.getResources().getDrawable(com.fajuary.myapplibrary.R.mipmap.edit_delete_icon);
        }

        return super.onTouchEvent(event);
    }

    public void afterTextChanged(Editable s) {
        if ( this.length() < 1 ) {
            this.drawableDel = null;
        } else {
//            this.drawableDel = this.getResources().getDrawable(com.fajuary.myapplibrary.R.mipmap.edit_delete_icon);
        }
    }
}
