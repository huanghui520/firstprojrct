package com.fajuary.xiyishop_android.mymodule.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.BeapakeShopApp;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.utils.SmallFeatureUtils;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity {



    @Bind(R.id.fragment_head_leftlayout)
    LinearLayout leftlayout;
    @Bind(R.id.fragment_head_leftimg)
    ImageView leftimg;

    @Bind(R.id.fragment_head_rightlayout)
    LinearLayout rightlayout;
    @Bind(R.id.fragment_head_rightimg)
    ImageView rightimg;

//    @Bind(R.id.fragment_head_centtxt)
//    ImageView centimg;

    @Bind(R.id.activity_orderdetail_orderStateTxt)
    TextView orderStateTxt;//订单状态

    @Bind(R.id.activity_orderdetail_orderStateLin)
    LinearLayout orderStateLin;

    @Bind(R.id.activity_orderdetail_orderStateTxt2)
    TextView orderStateTxt2;//订单状态

    @Bind(R.id.activity_orderdetail_storeImg)
    ImageView storeImg;//商品图片

    @Bind(R.id.activity_orderdetail_seestoreTxt)
    TextView seestoreTxt;//查看商品

    @Bind(R.id.activity_orderdetail_storeNameTxt)
    TextView storeNameTxt;

    @Bind(R.id.activity_orderdetail_packageNameTxt)
    TextView packageNameTxt;
    @Bind(R.id.activity_orderdetail_buyNumTxt)
    TextView buyNumTxt;
    @Bind(R.id.activity_orderdetail_orderCryTxt)
    TextView orderCryTxt;
    @Bind(R.id.activity_orderdetail_orderNumTxt)
    TextView orderNumTxt;
    @Bind(R.id.activity_orderdetail_orderPayTxt)
    TextView orderPayTxt;
    @Bind(R.id.activity_orderdetail_orderdiscountTxt)
    TextView orderdiscountTxt;
    @Bind(R.id.activity_orderdetail_orderMoneyTxt)
    TextView orderMoneyTxt;

    @Bind(R.id.activity_orderdetail_contactStorLin)
    LinearLayout contactStorLin;

    @Override
    public void createLayout() {
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        leftimg.setImageResource(R.mipmap.blackleft);
        rightlayout.setVisibility(View.INVISIBLE);
//        rightimg.setImageResource(R.mipmap.msg);

//        float densityDpi= SmallFeatureUtils.getDensityDpi(this);
//        Logger.e("densityDpi-->"+densityDpi);
//        ViewGroup.LayoutParams imgparms= SmallFeatureUtils.getParms(centimg);
//        imgparms.height= (int) (17*densityDpi);
//        imgparms.width= (int) (94*densityDpi);
//        centimg.setLayoutParams(imgparms);
//        centimg.setImageResource(R.mipmap.order_details);

    }

    @Override
    public void initEvent() {
        leftlayout.setOnClickListener(this);
        leftimg.setOnClickListener(this);
        seestoreTxt.setOnClickListener(this);
        contactStorLin.setOnClickListener(this);
        int size = SmallFeatureUtils.getWindowWith();

        SmallFeatureUtils.setScreenImgSize(storeImg);
        Logger.e("宽度"+size);
        String imgPath="你好";
        Picasso.with(this)
                      .load(imgPath)
                      .resize(size, size/2)
                      .placeholder(R.mipmap.defaultstorimg)
                      .error(R.mipmap.homeimg)
                      .into(storeImg);
    }
    @Override
    public void widgetClick(View view) {
        switch ( view.getId() ){
            case R.id.fragment_head_leftlayout:
            case R.id.fragment_head_leftimg:
                BeapakeShopApp.getInstance().finishActivity(this);
                break;
            case R.id.activity_orderdetail_seestoreTxt://查看商品
                Intent intent=new Intent();
                intent.setClass(this, ProductDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_orderdetail_contactStorLin://打电话
                SmallFeatureUtils.callPhone(this,"10086");
                break;
        }
    }

    @Override
    public boolean getIsFastClick() {
        return false;
    }

}
