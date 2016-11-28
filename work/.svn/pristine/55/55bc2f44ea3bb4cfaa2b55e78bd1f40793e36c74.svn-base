package com.fajuary.xiyishop_android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created 张朋飞 on 2016/9/5.
 * user 864598192
 */
public class MyViewGroup extends ViewGroup {

    private final static int VIEW_MARGIN=15;

    public MyViewGroup(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public MyViewGroup(Context context) {
        super(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int stages = 1;
        int stageHeight = 0;
        int stageWidth = 0;

        int wholeWidth = MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            // measure
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            stageWidth += (child.getMeasuredWidth() + VIEW_MARGIN);
            stageHeight = child.getMeasuredHeight();
            if (stageWidth >= wholeWidth) {
                stages++;
                //reset stageWidth
                stageWidth = child.getMeasuredWidth();
            }


        }



        int wholeHeight = (stageHeight + VIEW_MARGIN) * stages;

        // report this final dimension
        setMeasuredDimension(resolveSize(wholeWidth, widthMeasureSpec),
                      resolveSize(wholeHeight, heightMeasureSpec));
    }
    private int jiange = 10;//按钮之间的间隔
    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        View child=getChildAt(arg1);
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();


        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        int lengthX = arg1;    // right position of child relative to parent
        int lengthY = arg2;    // bottom position of child relative to parent
        for ( int i = 0; i < count; i++ ) {
            lengthX = width + VIEW_MARGIN + arg1;
            row++;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height + arg2;
        }
        child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
    }
}
