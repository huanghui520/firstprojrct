package com.fajuary.jdy_android.view;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fajuary.jdy_android.R;


/**
 *
 *@author 黄辉
 *@time 2016/10/19 16:09
 *
 * 网络加载框
 *
*/
public class LoadingAlertDialog extends AlertDialog {
	private Context mContext;
	private ProgressBar mBar;
	private TextView mMessage;

	public LoadingAlertDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public LoadingAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public LoadingAlertDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_loading_layout);

		// 点击imageview外侧区域，动画不会消失
		setCanceledOnTouchOutside(false);

		mBar = (ProgressBar) findViewById(R.id.bar);
		mMessage = (TextView) findViewById(R.id.message);
	}
	
	/**
	 * 设置文本内容,并且显示弹出框
	 * @param msg
	 */
	public void show(String msg) {  
        super.show();  
        if (mMessage != null) {  
            mMessage.setText(msg);  
        }  
    }  
  
    /**
     * 设置进度图片 
     * @param drawable
     */
    public void setIndeterminateDrawable(int drawable) {  
        mBar.setIndeterminateDrawable(mContext.getResources().getDrawable(drawable));  
    }  
  
    /**
     * 设置字体颜色  
     * @param color
     */
    public void setTextColor(int color) {  
        mMessage.setTextColor(color);  
    }  
}
