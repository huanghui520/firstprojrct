package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.WeiKuanBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.fajuary.xiyishop_android.widget.LoadingAlertDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * @author 黄辉
 * @time 2016/10/24 21:15
 * <p>
 * 收尾款页面
 */
public class WeiKuanActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mActivity_wei_kuan;
    private LinearLayout mIv_back;
    private EditText mEt_money_weikuan;
    private TextView mTv_weikuan_bufen;
    private TextView tv_money_weikuan;
    private CheckBox mCb_section_one;
    private TextView mTv_weikuan_quanbu;
    private CheckBox mCb_section_two;
    private TextView mTv_weikuan_queding;
    private TextView tv_weikuan_shouqu;
    private String orderGoodsId;//服务id
    private String shopId;//商户id
    private String goodsName;//商品名称
    private String money;//尾款金额
    private String title;//尾款类型
    private String shopName;//商户名称
    private String type = "2";//1.发起支付 2.收尾款
    private String s;//二维码生成的所需的信息
    private String token;
    private LoadingAlertDialog loadingAlertDialog;                  //加载狂框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_wei_kuan);
        orderGoodsId = getIntent().getStringExtra("orderGoodsId");
        shopId = getIntent().getStringExtra("shopId");
        goodsName = getIntent().getStringExtra("goodsName");
        bindViews();
    }

    private void bindViews() {
        mActivity_wei_kuan = (LinearLayout) findViewById(R.id.activity_wei_kuan);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mEt_money_weikuan = (EditText) findViewById(R.id.et_money_weikuan);
        mTv_weikuan_bufen = (TextView) findViewById(R.id.tv_weikuan_bufen);
        mCb_section_one = (CheckBox) findViewById(R.id.cb_section_one);
        mTv_weikuan_quanbu = (TextView) findViewById(R.id.tv_weikuan_quanbu);
        mCb_section_two = (CheckBox) findViewById(R.id.cb_section_two);
        mTv_weikuan_queding = (TextView) findViewById(R.id.tv_weikuan_queding);
//        tv_money_weikuan = (TextView) findViewById(R.id.tv_money_weikuan);
        mCb_section_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCb_section_one.isChecked()) {
                    mCb_section_two.setChecked(false);
                    title = "1";
                }
            }
        });
        mCb_section_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCb_section_two.isChecked()) {
                    mCb_section_one.setChecked(false);
                }
                title = "2";
            }
        });
        mIv_back.setOnClickListener(this);
        mTv_weikuan_queding.setOnClickListener(this);
//        tv_weikuan_shouqu.setOnClickListener(this);
        mEt_money_weikuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEt_money_weikuan.setText(s);
                        mEt_money_weikuan.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEt_money_weikuan.setText(s);
                    mEt_money_weikuan.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEt_money_weikuan.setText(s.subSequence(0, 1));
                        mEt_money_weikuan.setSelection(1);
                        return;
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_weikuan_queding:
                token = CacheUtils.getstr(this, "token");
                shopId = CacheUtils.getstr(this, "shopId");
                shopName = CacheUtils.getstr(this, "shopName");
                money = mEt_money_weikuan.getText().toString().trim();
                if (!TextUtils.isEmpty(money)) {
                    if (!TextUtils.isEmpty(title)) {
//                        PayWeiKuanBean payWeiKuanBean = new PayWeiKuanBean(shopName, orderGoodsId, shopId, goodsName, money, title, type);
//                        s = toString(payWeiKuanBean);
//                        Intent intent = new Intent(WeiKuanActivity.this, WeiKuanPayActivity.class);
//                        intent.putExtra("content", s);
//                        intent.putExtra("money",money);
//                        startActivity(intent);
                        loadingAlertDialog = new LoadingAlertDialog(this);
                        loadingAlertDialog.show("正在加载");
                        XYApi.pospay(token, money, title, orderGoodsId, shopId, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                if (null != responseBody && responseBody.length > 0) {

                                    Log.e("TAG", new String(responseBody) + "收尾款初始化"+"============================");
                                    //解析json
//                    Log.e("TAG",deviceToken+"设备token+++++++++++++++++++++++");
                                    explainJson(new String(responseBody));
                                }
                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                loadingAlertDialog.dismiss();
                                Log.e("TAG", "收尾款初始化:::" + statusCode + error.toString());
                                Toast.makeText(WeiKuanActivity.this, "加载异常" ,Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(WeiKuanActivity.this, "收尾款类型不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WeiKuanActivity.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
//            case R.id.tv_weikuan_shouqu://收取尾款
//
//                break;
        }
    }

    /**
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        WeiKuanBean weiKuanBean = JSON.parseObject(Json, WeiKuanBean.class);
        String code = weiKuanBean.getCode();
        String orderSpareId = weiKuanBean.getOrderSpareId();
        CacheUtils.putstr(this,"orderSpareId",orderSpareId);
        if(code.equals("0")) {
            loadingAlertDialog.dismiss();
        }else if(code.equals("1")) {
            Intent intent = new Intent(this, MainAct.class);
            intent.putExtra("money",money);
            startActivity(intent);
            finish();
            loadingAlertDialog.dismiss();
//            Log.e("TAG", new String(s) + "初始化"+"==============pos==============");
        }
        
    }

    public String toString(Object obj) {
        Gson gson = new Gson();
        String s = gson.toJson(obj);
        return s;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
