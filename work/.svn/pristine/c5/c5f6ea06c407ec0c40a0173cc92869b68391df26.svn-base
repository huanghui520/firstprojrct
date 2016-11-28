package com.fajuary.xiyishop_android.mymodule.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerAdapter;
import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.BeapakeShopApp;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.adapter.SelectCommAttrAdapter;
import com.fajuary.xiyishop_android.mymodule.bean.PackAgeInfo;
import com.fajuary.xiyishop_android.utils.SmallFeatureUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商品详情
 */
public class ProductDetailsActivity extends BaseActivity {

    @Bind(R.id.activity_prodetail_leftlayout)
    LinearLayout leftlayout;
    @Bind(R.id.activity_prodetail_leftimg)
    ImageView leftimg;

    @Bind(R.id.activity_prodetail_rightlayout)
    LinearLayout rightlayout;
    @Bind(R.id.activity_prodetail_rightimg)
    ImageView rightimg;



    @Bind(R.id.activity_prodetail_proimg)
    ImageView proimg;

    @Bind(R.id.activity_prodetail_proNames)
    TextView proNames;
    @Bind(R.id.activity_prodetail_proadress)
    TextView proadress;

    @Bind(R.id.activity_prodetail_proshareLin)
    LinearLayout proshareLin;
    @Bind(R.id.activity_prodetail_procollectLin)
    LinearLayout procollectLin;

    @Bind(R.id.activity_prodetail_provalue)
    TextView provalue;
    @Bind(R.id.activity_prodetail_prodiscount)
    TextView prodiscount;
    @Bind(R.id.activity_prodetail_proprice)
    TextView proprice;

    @Bind(R.id.activity_prodetail_prodetailadrs)
    TextView prodetailadrs;
    @Bind(R.id.activity_prodetail_proName)
    TextView proName;

    @Bind(R.id.activity_prodetail_prodescrip)
    TextView prodescrip;

    @Bind(R.id.activity_prodetail_proselectadrsLin)
    LinearLayout proselectadrsLin;

    @Bind(R.id.activity_prodetail_proselectstoreLin)
    LinearLayout proselectstoreLin;


    @Override
    public void createLayout() {
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

    }

    @Override
    public void initEvent() {
        leftlayout.setOnClickListener(this);
        leftimg.setOnClickListener(this);
        proselectstoreLin.setOnClickListener(this);
        proshareLin.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch ( view.getId() ) {
            case R.id.activity_prodetail_leftimg:
            case R.id.activity_prodetail_leftlayout:
                BeapakeShopApp.getInstance().finishActivity(this);
                break;
            case R.id.activity_prodetail_proshareLin://分享
//                Config.OpenEditor = true;
//                new ShareAction(this).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,
//                              SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.SMS)
//                              .setShareboardclickCallback(shareBoardlistener)
//                              .open();
                break;
            case R.id.activity_prodetail_procollectLin://收藏

                break;
            case R.id.activity_prodetail_proselectstoreLin://酒店详情
//                intent.setClass(this, LifeDetailsActivity.class);
//                startActivity(intent);
                break;

        }
    }
    private int goodsNum=1;
    private  Dialog dialog;




    private int mColumnCount = 3;
    private GridLayoutManager manager;
    private SelectCommAttrAdapter commAttrAdapter;
    private List<PackAgeInfo> allList;
    @Override
    public boolean getIsFastClick() {
        return false;
    }



    private String withTxtStr;
    private String shareType;
    private String actName;
//    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {
//
//        @Override
//        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
////            switch (carState) {
////                case "cart":
////                    if (id.equals("1")) {
//            withTxtStr = "各种各式的房车出租了";
//            shareType = "index_list";
//            actName = "活动";
////            }
//            new ShareAction(ProductDetailsActivity.this).setPlatform(share_media).setCallback(umShareListener)
//                          .withTitle(actName)
//                          .withText(withTxtStr)
////                          .withMedia(image)
////                          .withExtra(image)
//                          .withFollow(getString(R.string.app_name))
////                          .withTargetUrl(PartenerConfig.baseShareUrl+shareType+"&id="+carId)
//                          .share();
//        }
//    };
//    private UMShareListener umShareListener = new UMShareListener() {
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            SmallFeatureUtils.Toast( platform + " 分享成功啦");
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//            SmallFeatureUtils.Toast( platform + " 分享失败啦");
//
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//            SmallFeatureUtils.Toast( platform + " 分享取消了");
//        }
//    };


}
