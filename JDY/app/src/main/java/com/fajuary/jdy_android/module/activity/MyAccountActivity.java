package com.fajuary.jdy_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fajuary.jdy_android.R;
import com.fajuary.jdy_android.module.bean.MyAccountBean;
import com.fajuary.jdy_android.tools.CacheUtils;
import com.fajuary.jdy_android.tools.JDY_JZApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 *
 *@author 黄辉
 *@time 2016/11/8 16:22
 *
 *我的账户
*/
public class MyAccountActivity extends BaseActivity {
    private LinearLayout mActivity_my_account;                  //页面的
    private LinearLayout mIv_back;                              //返回
    private TextView mTv_account_balance;                       //账户余额
    private TextView mTv_general_income;                        //总收入
    private LinearLayout mLl_my_tixian;                         //我要提现
    private LinearLayout mLl_tixian_record;                     //提现记录
    private LinearLayout mLl_trade_record;                      //交易记录
    private String token;                                       //登陆返回的token

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        bindViews();
        initData();
    }
    /**
     * 联网获取数据
     */
    private void initData() {
        token = CacheUtils.getstr(this, "token");
        JDY_JZApi.my_account(token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "我的-我的账户返回的数据" + token );
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::我的-我的账户返回的数据" + statusCode + error.toString());
            }
        });
    }

    /**
     * 解析 我的-我的账户返回的数据
     * @param json
     */
    private void explainJson(String json) {
        MyAccountBean myAccountBean = JSON.parseObject(json, MyAccountBean.class);
        String status = myAccountBean.getStatus();
        if(status.equals("0")) {//失败

        }else if(status.equals("1")) {//成功
            mTv_account_balance.setText(myAccountBean.getInfo().getAccount());             //账户余额
            mTv_general_income.setText("总收入："+myAccountBean.getInfo().getTotal());     //总收入
        }

    }

    /**
     * 初始化id
     */
    private void bindViews() {
        mActivity_my_account = (LinearLayout) findViewById(R.id.activity_my_account);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mTv_account_balance = (TextView) findViewById(R.id.tv_account_balance);
        mTv_general_income = (TextView) findViewById(R.id.tv_general_income);
        mLl_my_tixian = (LinearLayout) findViewById(R.id.ll_my_tixian);
        mLl_tixian_record = (LinearLayout) findViewById(R.id.ll_tixian_record);
        mLl_trade_record = (LinearLayout) findViewById(R.id.ll_trade_record);
    }

}
