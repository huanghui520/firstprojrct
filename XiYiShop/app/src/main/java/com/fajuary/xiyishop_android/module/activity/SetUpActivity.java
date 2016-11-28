package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.widget.LoadingAlertDialog;

/**
 *
 *@author 黄辉
 *@time 2016/10/13 9:13
 *
 * 设置页面
*/
public class SetUpActivity extends BaseActivity {

    private RelativeLayout rl_update;
    private RelativeLayout rl_gaimi;
    private RelativeLayout rl_clear;
    private LinearLayout iv_back;
    private RelativeLayout rl_exit;

    //延时消息
    private static final int ZIDONG = 1;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ZIDONG:
                    loadingAlertDialog.dismiss();
                    Toast.makeText(SetUpActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private LoadingAlertDialog loadingAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_set_up);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        rl_update= (RelativeLayout) findViewById(R.id.rl_update);
        rl_clear= (RelativeLayout) findViewById(R.id.rl_clear);
        rl_gaimi= (RelativeLayout) findViewById(R.id.rl_gaimi);
        rl_exit= (RelativeLayout) findViewById(R.id.rl_exit);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           finish();
            }
        });
        //版本更新 点击事件
        rl_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetUpActivity.this, EditionActivity.class);
                startActivity(intent);
            }
        });
        //修改密码
        rl_gaimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SetUpActivity.this, ModificationActivty.class);
                startActivity(intent1);

            }
        });
        //退出登录
        rl_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SetUpActivity.this);
                dialog.setMessage("退出登录");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CacheUtils.putstr(SetUpActivity.this,"token",null);
                        String token = CacheUtils.getString(SetUpActivity.this, "tokent");
                        if(TextUtils.isEmpty(token)) {
                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
//                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
                            Toast.makeText(SetUpActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SetUpActivity.this, LogInActivity.class);
                            startActivity(intent);
//                            finish();

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
            }
        });
        //清楚缓存
        rl_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              loadingAlertDialog = new LoadingAlertDialog(SetUpActivity.this);
                loadingAlertDialog.show("正在清理缓存");
                handler.sendEmptyMessageDelayed(ZIDONG,3000);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
//        Intent intent = new Intent(SetUpActivity.this, LogInActivity.class);
//        startActivity(intent);
    }
}
