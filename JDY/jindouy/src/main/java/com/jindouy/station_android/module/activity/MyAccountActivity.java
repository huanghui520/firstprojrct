package com.jindouy.station_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.MyAccountBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 *
 *@author 黄辉
 *@time 2016/11/8 16:22
 *
 *我的账户
*/
public class MyAccountActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_my_account;                  //页面的
    private LinearLayout mIv_back;                              //返回
    private TextView mTv_account_balance;                       //账户余额
    private TextView mTv_general_income;                        //总收入
    private LinearLayout mLl_my_tixian;                         //我要提现
    private LinearLayout mLl_tixian_record;                     //提现记录
    private LinearLayout mLl_trade_record;                      //交易记录
    private String token;                                       //登陆返回的token
    private LoadingAlertDialog loadingAlertDialog;
    private MyAccountBean myAccountBean;

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
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在加载...");
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
                loadingAlertDialog.dismiss();
            }
        });
    }
    /**
     * 解析 我的-我的账户返回的数据
     * @param json
     */
    private void explainJson(String json) {
        myAccountBean = new Gson().fromJson(json, MyAccountBean.class);
//        MyAccountBean myAccountBean = JSON.parseObject(json, MyAccountBean.class);
        String status = myAccountBean.getStatus();
        if(status.equals("0")) {//失败
            Toast.makeText(MyAccountActivity.this, myAccountBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
            loadingAlertDialog.dismiss();
        }else if(status.equals("1")) {//成功
            mTv_account_balance.setText(myAccountBean.getInfo().getAccount());             //账户余额
            mTv_general_income.setText("总收入："+myAccountBean.getInfo().getTotal());     //总收入
            loadingAlertDialog.dismiss();
        }
    }
    /**
     * 初始化id
     */
    private void bindViews(){
        mActivity_my_account = (LinearLayout) findViewById(R.id.activity_my_account);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mTv_account_balance = (TextView) findViewById(R.id.tv_account_balance);
        mTv_general_income = (TextView) findViewById(R.id.tv_general_income);
        mLl_my_tixian = (LinearLayout) findViewById(R.id.ll_my_tixian);
        mLl_tixian_record = (LinearLayout) findViewById(R.id.ll_tixian_record);
        mLl_trade_record = (LinearLayout) findViewById(R.id.ll_trade_record);
        /**
         * 点击事件
         */
        mIv_back.setOnClickListener(this);
        mLl_my_tixian.setOnClickListener(this);
        mLl_tixian_record.setOnClickListener(this);
        mLl_trade_record.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_tixian ://我要提现
                String account = myAccountBean.getInfo().getAccount();
                Intent intent = new Intent(this, MyTiXianActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
                break;
            case R.id.ll_tixian_record://提现记录
                Intent intent1 = new Intent(this, TiXianRecordActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_trade_record://交易记录
                Intent intent2 = new Intent(this, TradeRecordActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}