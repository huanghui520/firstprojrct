package com.fajuary.xiyishop_big_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/11/22 23:51
 * 扫码详情
 */
public class ScanDetailsActivity extends Activity implements View.OnClickListener {
    private LinearLayout mActivity_scan_details;
    private LinearLayout mIv_back;
    private RelativeLayout mRl_dingdan_a;
    private TextView mTv_tuikuan_statar;
    private RelativeLayout mRl_dingdan_b;
    private TextView mTv_dingdan_name;
    private TextView mTv_dingdan_shuliang;
    private TextView mTv_dingdan_shuliang_shiy;
    private TextView mTv_dingdan_number;
    private TextView mTv_advance;
    private TextView mTv_weikuan;

    private String result;
    private String token;
    private String money;
    private String shopName;
    private String ordercode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_details);
        token = CacheUtils.getstr(this, "id");
        result = getIntent().getStringExtra("result");
        bindViews();
    }

    /**
     * 掉接口  获取数据
     */
    private void initData() {
        XY_DApi.posisorder(token, result, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "扫码订单返回的数据");
                    ScanBean scanBean = JSON.parseObject(new String(responseBody), ScanBean.class);
                    if (scanBean.getCode().equals("1")) {
                        Toast.makeText(ScanDetailsActivity.this, scanBean.getMsg(), Toast.LENGTH_SHORT).show();
                        mTv_dingdan_name.setText(scanBean.getDatas().getGoodsName());
                        mTv_dingdan_shuliang.setText("购买数量："+scanBean.getDatas().getNum());
                        mTv_dingdan_shuliang_shiy.setText("使用次数："+scanBean.getDatas().getCount());
                        ordercode = scanBean.getDatas().getOrdercode();
                        mTv_dingdan_number.setText("订单编号："+scanBean.getDatas().getOrdercode());
                        ordercode = scanBean.getDatas().getOrdercode();
                        CacheUtils.putstr(ScanDetailsActivity.this,"ordercode",ordercode);
                        money = scanBean.getDatas().getMoney();
                        mTv_advance.setText("金额："+ scanBean.getDatas().getMoney());
                        shopName = scanBean.getDatas().getShopName();

                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::扫码订单返回的数据" + statusCode + error.toString());
            }
        });
    }

    private void bindViews() {
        mActivity_scan_details = (LinearLayout) findViewById(R.id.activity_scan_details);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRl_dingdan_a = (RelativeLayout) findViewById(R.id.rl_dingdan_a);
        mTv_tuikuan_statar = (TextView) findViewById(R.id.tv_tuikuan_statar);
        mRl_dingdan_b = (RelativeLayout) findViewById(R.id.rl_dingdan_b);
        mTv_dingdan_name = (TextView) findViewById(R.id.tv_dingdan_name);
        mTv_dingdan_shuliang = (TextView) findViewById(R.id.tv_dingdan_shuliang);
        mTv_dingdan_shuliang_shiy = (TextView) findViewById(R.id.tv_dingdan_shuliang_shiy);
        mTv_dingdan_number = (TextView) findViewById(R.id.tv_dingdan_number);
        mTv_advance = (TextView) findViewById(R.id.tv_advance);
        mTv_weikuan = (TextView) findViewById(R.id.tv_weikuan);
        initData();
        mTv_weikuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_weikuan:
                Log.e("TAG","尾款===============");
                Intent intent = new Intent(this, MainAct.class);
                intent.putExtra("money",money);
                intent.putExtra("ordercode",ordercode);
                startActivity(intent);
                finish();
                break;
        }
    }
}
