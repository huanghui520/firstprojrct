package com.fajuary.xiyishop_android.module.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.BankCardStateBean;
import com.fajuary.xiyishop_android.module.bean.TiXianBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.module.view.FirstEvent2;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;
import de.greenrobot.event.EventBus;

/**
 * @author 黄辉
 * @time 2016/10/13 10:26
 * <p>
 * 提现页面
 */
public class TiXianActivity extends BaseActivity implements View.OnClickListener {
    private static final int SUCCEED = 1;
    private TextView tv_tixianjilu;//提现记录
    private RelativeLayout rl_select;//选择银行卡
    private LinearLayout iv_back;//返回
    private TextView tv_account;//卡号
    private String zhanghao;//账号
    private EditText et_money;
    private TextView tv_present;
    private TextView tv_balance;
    private TextView tv_name_tixian_card;


    private String token;//商户的token
    private String bankId;//银行卡id
    private String msg;
    private String balance;//账户余额
    private String[] split;//银行卡列表传过来的数据
    private String amount;
    private String bankcard;
    private String myname;//银行卡户名

    //handler   消息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case  SUCCEED:
                    Intent intent1 = new Intent(TiXianActivity.this, TiXianRecordActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };
    private String shopId;
    private List<BankCardStateBean.DatasBean> datas;
    private String card_num;// 卡号
    private String cardholdername;//持卡人姓名


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_ti_xian);
        balance = getIntent().getStringExtra("balance");
        //1.注册
        EventBus.getDefault().register(this);
        getData();
        initData();
    }
    private void getData() {
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.bankcard(token, shopId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "银行卡列表" + token + "llllll" + shopId);
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", "银行卡列表:::" + statusCode + error.toString());
            }
        });
    }

    /**
     * 解析json   卡号
     * @param json
     */
    private void explainJson(String json) {
        BankCardStateBean bankCardStateBean = JSON.parseObject(json, BankCardStateBean.class);
        datas = bankCardStateBean.getDatas();
        int size = datas.size();
        if(size>0) {
            cardholdername = datas.get(0).getCardholdername();
            card_num = datas.get(0).getCard_num();
            bankId = datas.get(0).getId();
            tv_account.setText(card_num);
            tv_name_tixian_card.setText(cardholdername);
        }

    }

    private void initData(){
        token = CacheUtils.getString(this, "token");
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        tv_tixianjilu = (TextView) findViewById(R.id.tv_tixianjilu);//提现记录
        rl_select = (RelativeLayout) findViewById(R.id.rl_select);//选择银行卡
        iv_back = (LinearLayout) findViewById(R.id.iv_back);//返回
        tv_account = (TextView) findViewById(R.id.tv_account);//卡号
        et_money = (EditText) findViewById(R.id.et_money);//输入的金额
        tv_name_tixian_card= (TextView) findViewById(R.id.tv_name_tixian_card);
        tv_present = (TextView) findViewById(R.id.tv_present);//提交申请
        tv_tixianjilu.setOnClickListener(this);
        rl_select.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_present.setOnClickListener(this);//提交申请
        tv_balance.setText(balance);



        //设置小数点后两位
        et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_money.setText(s);
                        et_money.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_money.setText(s);
                    et_money.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_money.setText(s.subSequence(0, 1));
                        et_money.setSelection(1);
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
            case R.id.tv_tixianjilu://提现记录页面
                Intent intent = new Intent(this, TiXianRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_select://银行卡列表页面
                Intent intent1 = new Intent(this, BankCardActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_back:
//                Intent intent2 = new Intent(this, MainActivity.class);
//                startActivity(intent2);
                this.finish();
                break;
            case R.id.tv_present:
                bankcard = tv_account.getText().toString().toString();
                amount = et_money.getText().toString().trim();
                token = CacheUtils.getstr(this, "token");
                if (!TextUtils.isEmpty(amount)) {
                    if(!TextUtils.isEmpty(bankcard)) {
                    double amount_a = Double.parseDouble(amount);
                    double balance_b = Double.parseDouble(balance);
                    if (amount_a <= balance_b) {
                            XYApi.withdrawal(token, bankId, amount, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    if (null != responseBody && responseBody.length > 0) {
                                        Log.e("TAG", new String(responseBody) + "提现申请1" + token + "bankId" + bankId + "amount");
                                        TiXianBean tiXianBean = JSON.parseObject(new String(responseBody), TiXianBean.class);
//                                        Toast.makeText(TiXianActivity.this, tiXianBean.getDatas(), Toast.LENGTH_SHORT).show();
                                        if (tiXianBean.getCode().equals("1")) {
                                            et_money.setText(null);
                                            tv_present.setClickable(false);
                                            handler.sendEmptyMessage(SUCCEED);//提现成功发送消息
                                            Toast.makeText(TiXianActivity.this, tiXianBean.getDatas(), Toast.LENGTH_SHORT).show();
                                        } else if (tiXianBean.getCode().equals("0")) {
                                            Toast.makeText(TiXianActivity.this, tiXianBean.getDatas(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Log.e("TAG", ":::提现申请2" + statusCode + error.toString());
                                    Log.e("TAG", "提现申请3" + token + "bankId" + bankId + "amount");
                                }
                            });
                        }else {
                            Toast.makeText(TiXianActivity.this, "提现金额应小于账户余额 ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TiXianActivity.this, "请选择银行卡 ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TiXianActivity.this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }

    //2.写方法
    public void onEventMainThread(FirstEvent2 event) {
        msg = event.getMsg();
        split = msg.split("\\|");
        tv_account.setText(split[0]);
        bankId = split[1];
        tv_name_tixian_card.setText(split[2]);
//        myname = split[2];
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
