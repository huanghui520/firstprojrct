package com.fajuary.xiyishop_big_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 大额支付  首页
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout mActivity_main;
    private TextView mTv_pay;
    private TextView mTv_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }
    private void bindViews() {
        mActivity_main = (LinearLayout) findViewById(R.id.activity_main);
        mTv_pay = (TextView) findViewById(R.id.tv_pay);
        mTv_exit = (TextView) findViewById(R.id.tv_exit);
        mTv_pay.setOnClickListener(this);
        mTv_exit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_pay://收款
                Intent intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_exit://退出登录
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("退出登录");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CacheUtils.putstr(MainActivity.this,"id",null);
                        String token = CacheUtils.getstr(MainActivity.this, "id");
                        if(TextUtils.isEmpty(token)) {
//                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
//                            ActivityManager.getInstance().popOtherActivity(LogInActivity.class);
                            Toast.makeText(MainActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
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
