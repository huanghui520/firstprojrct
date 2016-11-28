package com.jindouy.station_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.BankCardBean;
import com.jindouy.station_android.module.bean.MyTiXianBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.FirstEvent2;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;
import de.greenrobot.event.EventBus;

import static com.jindouy.station_android.R.id.tv_tixian_queding;

/**
 *
 *@author 黄辉
 *@time 2016/11/9 13:24
 *
 *提现
*/
public class MyTiXianActivity extends BaseActivity implements View.OnClickListener {
    private static final int SUCCEED = 1;

    private LinearLayout mActivity_my_ti_xian;
    private LinearLayout mIv_back;
    private TextView mTv_my_balance;
    private EditText mEt_tixian_money;
    private LinearLayout mLl_bank_card;
    private TextView mTv_bank_card;
    private TextView mTv_tixian_queding;
    private String content;                                    //evetbus传过来的数据
    private String[] split;
    private String card_id;                                    //银行卡id
    private String token;                                      //
    private String bank_name;                                  //银行卡名称
    private String card_num;                                   //银行卡号
    private String account;                                    //账户余额
    private String money;
    private LoadingAlertDialog loadingAlertDialog;
    //handler   消息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case  SUCCEED:
                    Intent intent1 = new Intent(MyTiXianActivity.this, TiXianRecordActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ti_xian);
        //1.注册
        EventBus.getDefault().register(this);
        bindViews();
        initData();
    }

    private void initData() {
        token = CacheUtils.getstr(this, "token");
        JDY_JZApi.bankcardlist(token, 1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG",new String(responseBody)+"银行卡列表获取成功");
                    //解析json
                    explainJson(new String(responseBody));
                }
            }



            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG",statusCode+error.toString()+"银行卡列表获取失败");
            }
        });
    }

    /**
     * 解析Json
     * @param Json
     */
    private void explainJson(String Json) {
        BankCardBean bankCardBean = JSON.parseObject(Json, BankCardBean.class);
        String status = bankCardBean.getStatus();
        if(status.equals("1")) {
            List<BankCardBean.InfoBean.ListBean> list = bankCardBean.getInfo().getList();
            bank_name = list.get(0).getBank_name();
            card_num = list.get(0).getCard_num();
            card_id = list.get(0).getCard_id();
            mTv_bank_card.setText(bank_name+" "+card_num);
        }
    }
    private void bindViews() {

        account = getIntent().getStringExtra("account");
        mActivity_my_ti_xian = (LinearLayout) findViewById(R.id.activity_my_ti_xian);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mTv_my_balance = (TextView) findViewById(R.id.tv_my_balance);
        mEt_tixian_money = (EditText) findViewById(R.id.et_tixian_money);
        mLl_bank_card = (LinearLayout) findViewById(R.id.ll_bank_card);
        mTv_bank_card = (TextView) findViewById(R.id.tv_bank_card);
        mTv_tixian_queding = (TextView) findViewById(tv_tixian_queding);
        //点击 选择银行卡
        mIv_back.setOnClickListener(this);
        mLl_bank_card.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
        mTv_tixian_queding.setOnClickListener(this);
        mTv_my_balance.setText(account);

        //设置小数点后两位
        mEt_tixian_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEt_tixian_money.setText(s);
                        mEt_tixian_money.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEt_tixian_money.setText(s);
                    mEt_tixian_money.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEt_tixian_money.setText(s.subSequence(0, 1));
                        mEt_tixian_money.setSelection(1);
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
            case  R.id.ll_bank_card://选择银行卡
                Intent intent = new Intent(this, BankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back://返回
                finish();
                break;
            case tv_tixian_queding://提现
                money = mEt_tixian_money.getText().toString().trim();// 提现金额
                token = CacheUtils.getstr(this, "token");
                if (!TextUtils.isEmpty(money)) {
                    if(!TextUtils.isEmpty(card_num)) {
                        double amount_a = Double.parseDouble(money);//提现金额
                        double balance_b = Double.parseDouble(account);//账户余额
                        if (amount_a <= balance_b) {
                            loadingAlertDialog = new LoadingAlertDialog(this);
                            loadingAlertDialog.show("正在提现");
                            JDY_JZApi.aplywith(token, card_id, money, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    if (null != responseBody && responseBody.length > 0) {
                                        Log.e("TAG", new String(responseBody) + "提现申请1" + token + "bankId" + card_id + "amount");
                                        MyTiXianBean myTiXianBean = JSON.parseObject(new String(responseBody), MyTiXianBean.class);
                                        String status = myTiXianBean.getStatus();
                                        if(status.equals("1")) {//成功
                                            loadingAlertDialog.dismiss();
                                            mEt_tixian_money.setText(null);
                                            mTv_tixian_queding.setClickable(false);
                                            Toast.makeText(MyTiXianActivity.this, myTiXianBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
                                            handler.sendEmptyMessage(SUCCEED);//提现成功发送消息
                                        }else if(status.equals("0")) {
                                            Toast.makeText(MyTiXianActivity.this, myTiXianBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
                                            loadingAlertDialog.dismiss();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Log.e("TAG", ":::提现申请2" + statusCode + error.toString());
                                    loadingAlertDialog.dismiss();
                                }
                            });
                        }else {
                            Toast.makeText(MyTiXianActivity.this, "提现金额应小于账户余额 ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MyTiXianActivity.this, "请选择银行卡 ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyTiXianActivity.this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(MyTiXianActivity.this, "暂无此功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
    //2.写方法
    public void onEventMainThread(FirstEvent2 event) {
        content = event.getMsg();
        split = content.split("\\|");
        card_num= split[0];
        card_id = split[1];
        bank_name = split[2];
        mTv_bank_card.setText(bank_name+" "+card_num);//银行名称  加银行卡号
    }
}