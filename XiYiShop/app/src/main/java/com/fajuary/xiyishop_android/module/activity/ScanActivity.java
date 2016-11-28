package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 *
 *@author 黄辉
 *@time 2016/10/21 15:32
 *
 * 扫码
*/

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    private QRCodeView mQR;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_scan);

        mQR = (ZXingView) findViewById(R.id.zx_view);

        //设置结果处理
//        mQR.setResultHandler(this);
        mQR.setDelegate(this);
        //开始读取二维码
        mQR.startSpot();
    }

    /**
     * 扫描二维码方法大全（已知）
     *
     * mQR.startCamera();               开启预览，但是并未开始识别
     * mQR.stopCamera();                停止预览，并且隐藏扫描框
     * mQR.startSpot();                 开始识别二维码
     * mQR.stopSpot();                  停止识别
     * mQR.startSpotAndShowRect();      开始识别并显示扫描框
     * mQR.stopSpotAndHiddenRect();     停止识别并隐藏扫描框
     * mQR.showScanRect();              显示扫描框
     * mQR.hiddenScanRect();            隐藏扫描框
     * mQR.openFlashlight();            开启闪光灯
     * mQR.closeFlashlight();           关闭闪光灯
     *
     * mQR.startSpotDelay(ms)           延迟ms毫秒后开始识别
     */
    /**
     * 扫描二维码成功
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
//        Toast.makeText(ScanActivity.this, result, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ScanActivity.this, VerifyResultActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
        //震动
        vibrate();
        //停止预览
        mQR.stopCamera();
        finish();//销毁

    }

    /**
     * 打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(ScanActivity.this, "打开相机出错！请检查是否开启权限！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 震动
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //启动相机
        mQR.startCamera();
    }

    @Override
    protected void onStop() {
        mQR.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
