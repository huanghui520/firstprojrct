package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;

/**
 *
 *@author 黄辉
 *@time 2016/10/12 23:31
 * 验证订单页面
 *
*/
public class VerifyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_verify);
        CacheUtils.putstr(this,"code",null);

//        findViewById(R.id.generate_qrcode).setOnClickListener(this);
//        findViewById(R.id.scan_qrcode).setOnClickListener(this);
        startActivity(new Intent(VerifyActivity.this,ScanActivity.class));
        finish();
    }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//
//            case R.id.scan_qrcode:
//
//                startActivity(new Intent(VerifyActivity.this,ScanActivity.class));
//                break;
//
////            case R.id.generate_qrcode:
////                startActivity(new Intent(VerifyActivity.this,GenerateActivity.class));
////                break;
//        }
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
