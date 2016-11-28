package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.ShangPingXiangQingBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 *
 *@author 黄辉
 *@time 2016/10/13 17:25
 *
 * 商品详情页面
 *
*/
public class ShangPinXiangqingActivity extends BaseActivity {
    private LinearLayout iv_back;//返回
    private ImageView iv_goods_icon;
    private TextView tv_goods_name;
    private TextView tv_goods_money;
    private TextView tv_goods_jieshao;
    private WebView tv_goods_content;

    private String goodsId;//商品的id
    private String token;//商户的token
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_shang_pin_xiangqing);
        goodsId = getIntent().getStringExtra("goodsId");//获取商品的id
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        iv_goods_icon= (ImageView) findViewById(R.id.iv_goods_icon);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_goods_money = (TextView) findViewById(R.id.tv_goods_money);
        tv_goods_jieshao = (TextView) findViewById(R.id.tv_goods_jieshao);
        tv_goods_content = (WebView) findViewById(R.id.tv_goods_content);
//        tv_goods_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        token = CacheUtils.getstr(this, "token");
        XYApi.shangpingxiangqing(token,goodsId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "商品详情" + token + "llllll" + goodsId);
                    //解析json
                    explainJson(new String(responseBody));
//                    refresh.finishRefresh();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", new String(responseBody) + "商品详情信息列表" + token + "llllll" );
            }
        });
    }

    private void explainJson(String json) {
        ShangPingXiangQingBean shangPingXiangQingBean = JSON.parseObject(json, ShangPingXiangQingBean.class);
        String goodsName = shangPingXiangQingBean.getDatas().getGoodsName();
        if(!TextUtils.isEmpty(goodsName)) {
            Glide.with(this).load(shangPingXiangQingBean.getDatas().getThumbnail()).into(iv_goods_icon);
            TextPaint paint =tv_goods_name.getPaint();
            paint.setFakeBoldText(true);//加粗
            tv_goods_name.setText(shangPingXiangQingBean.getDatas().getGoodsName());
            tv_goods_money.setText("价格:￥"+shangPingXiangQingBean.getDatas().getPrice());//价格
//        tv_goods_content.setText(shangPingXiangQingBean.getDatas().getIntroduction());
//        tv_goods_content.setText(Html.fromHtml(shangPingXiangQingBean.getDatas().getIntroduction()));

            tv_goods_content.loadDataWithBaseURL(null, shangPingXiangQingBean.getDatas().getIntroduction(), "text/html", "utf-8", null);
            tv_goods_content.getSettings().setJavaScriptEnabled(true);
            tv_goods_content.setWebChromeClient(new WebChromeClient());
            Log.e("TAG",shangPingXiangQingBean.getDatas().getThumbnail() + "商品详情" + token + "llllll" + goodsId);
        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ShangPinXiangqingActivity.this);
            dialog.setMessage("该商品已被删除");
            dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            //不关闭写法
            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
