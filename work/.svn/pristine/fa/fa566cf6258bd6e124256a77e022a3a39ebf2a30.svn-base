package com.fajuary.xiyishop_android.mymodule.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.BeapakeShopApp;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.utils.SmallFeatureUtils;
import com.rbj.zxing.decode.QrcodeDecode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 验证订单
 */
public class VerifyOrderActivity extends BaseActivity {




    @Bind(R.id.fragment_head_leftlayout)
    LinearLayout leftlayout;
    @Bind(R.id.fragment_head_leftimg)
    ImageView leftimg;

    @Bind(R.id.fragment_head_rightlayout)
    LinearLayout rightlayout;
    @Bind(R.id.fragment_head_rightimg)
    ImageView rightimg;

//    @Bind(R.id.fragment_head_centtxt)
//    ImageView centImg;

    @Bind(R.id.capture_preview)
    SurfaceView scanPreview;//相机
    @Bind(R.id.capture_crop_view)
    RelativeLayout scanCropView;//扫描框
    @Bind(R.id.capture_scan_line)
    ImageView scanLine;//扫描框中间的线

    private QrcodeDecode qrcodeDecode;

    public void createLayout() {
        setContentView(R.layout.activity_verify_order);
        ButterKnife.bind(this);
        leftimg.setImageResource(R.mipmap.blackleft);
        rightimg.setImageResource(R.mipmap.msg);
//        centImg.setImageResource(R.mipmap.servicesuccess_icon);

        qrcodeDecode = new QrcodeDecode(this,scanPreview,scanCropView) {
            @Override
            public void handleDecode(Bundle bundle) {
                //扫描成功后调用
                SmallFeatureUtils.Toast("扫描成功");
//                startActivity(new Intent(PayOrderActivity.this, BuyCommActivity.class).putExtras(bundle));
            }
        };
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                      0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在此处开起扫描
        qrcodeDecode.onResume();
    }

    @Override
    protected void onPause() {
        //
        qrcodeDecode.onPause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        qrcodeDecode.onDestroy();
    }

    @Override
    public void initEvent() {
        leftlayout.setOnClickListener(this);
        leftimg.setOnClickListener(this);
    }
    @Override
    public void widgetClick(View view) {
        Intent intent=new Intent();
        switch ( view.getId() ) {
            case R.id.fragment_head_leftlayout:
            case R.id.fragment_head_leftimg:
                BeapakeShopApp.getInstance().finishActivity(this);
                break;
        }
    }

    @Override
    public boolean getIsFastClick() {
        return false;
    }



}
