package com.jindouy.station_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jindouy.station_android.R;
import com.jindouy.station_android.tools.ActivityManager;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.view.LoadingAlertDialog;


/**
 *
 *@author 黄辉
 *@time 2016/11/8 16:24
 *
 *系统设置
*/
public class SystemSettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_system_setting;
    private LinearLayout mIv_back;
    private LinearLayout mLl_feedback;
    private LinearLayout mLl_clear;
    private LinearLayout mLl_exit;
    private String token;                                                         //
    //延时消息
    private static final int ZIDONG = 1;
    private LoadingAlertDialog loadingAlertDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ZIDONG:
                    loadingAlertDialog.dismiss();
                    Toast.makeText(SystemSettingActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        bindViews();
    }
    /**
     * 初始化
     */
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_system_setting = (LinearLayout) findViewById(R.id.activity_system_setting);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mLl_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        mLl_clear = (LinearLayout) findViewById(R.id.ll_clear);
        mLl_exit = (LinearLayout) findViewById(R.id.ll_exit);

        mLl_feedback.setOnClickListener(this);
        mLl_clear.setOnClickListener(this);
        mLl_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.ll_feedback://意见反馈
                Intent intent = new Intent(this, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_clear://清除缓存
                loadingAlertDialog = new LoadingAlertDialog(SystemSettingActivity.this);
                loadingAlertDialog.show("正在清理缓存");
                handler.sendEmptyMessageDelayed(ZIDONG,3000);
                break;
            case R.id.ll_exit://退出登录
                AlertDialog.Builder dialog = new AlertDialog.Builder(SystemSettingActivity.this);
                dialog.setMessage("退出登录");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CacheUtils.putstr(SystemSettingActivity.this,"token",null);
                        String token = CacheUtils.getString(SystemSettingActivity.this, "tokent");
                        if(TextUtils.isEmpty(token)) {
                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
//                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
                            Toast.makeText(SystemSettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SystemSettingActivity.this, LogInActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create();
                dialog.setCancelable(false);
                dialog.show();
                break;
        }
    }
}
