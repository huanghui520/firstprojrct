package com.fajuary.xiyishop_big_android;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.esapos.androidpos.IPosAidlInterface;
import com.esapos.plugin.api.PayPluginApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class MainAct extends Activity implements OnItemSelectedListener, View.OnClickListener {
    private static final int DISMISS_DIALOG = 0;
    private static final int SHOW_DIALOG = 1;
    private static final int INIT_SUCC = 2;
    private static final int INIT_FAIL = 3;
    public static final String RET_SUCCESS = "00";
    private String type = null;
    private Button btnInitTrade;

    private Button btnDealTrade;

    private Button btnReleaseTrade;

    private Button btnGetChannelInfo;

    private TextView resTv;

    private View saleLy;

    private View revokeLy;

    private TextView saleAmountEt;

    private EditText revokeVoucherNoEt;

    private EditText revokeReferNoEt;

    private EditText revokeAmountEt;

    private EditText revokeDateEt;

    private Spinner tradeTypeSpr;

    private Spinner channelSpr;

    private int tradeType = 1;

    private String tradeDate;

    private static final int DATA_PICKER_ID = 1;

    private static final String[] CHANNEL_ARRAY = {"UMS_PAY", "ALI_PAY", "WEIXIN_PAY"}; //渠道

    private String mChannel = CHANNEL_ARRAY[0];

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String str = year + "/";
            if ((monthOfYear + 1) < 10) {
                str += "0";
            }
            str += (monthOfYear + 1) + "/";
            if (dayOfMonth < 10) {
                str += "0";
            }
            str += dayOfMonth;
            tradeDate = str;
            revokeDateEt.setText(tradeDate);
        }
    };
    private String money;             //金额
    private String shopName;
    private String token;
    private IPosAidlInterface posAidl;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            posAidl = IPosAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ProgressDialog loadingDlg;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_DIALOG:
                    showDlg();
                    break;
                case DISMISS_DIALOG:
                    dismissDlg();
                    break;
                case INIT_SUCC:
                    dismissDlg();
                    showMsg("初始化成功");
                    // 可以在这里做初始化后直接进入交易流程
                    // dealSale();
                    break;
                case INIT_FAIL:
                    dismissDlg();
                    showMsg("初始化失败，请重试...");
                    break;
            }
        }
    };
    private String amountStr;//消费金额
    private String ordercode;

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showDlg() {
        if (null != loadingDlg) {
            loadingDlg.show();
        }
    }

    private void dismissDlg() {
        if (null != loadingDlg) {
            loadingDlg.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 建立服务连接
        bindServicea();
        ordercode =getIntent().getStringExtra("ordercode");
        token = CacheUtils.getstr(this,"id");
        money = getIntent().getStringExtra("money");
        btnInitTrade = (Button) findViewById(R.id.btn1);//初始化交易
        btnDealTrade = (Button) findViewById(R.id.btn2);//处理交易
        btnReleaseTrade = (Button) findViewById(R.id.btn3);//释放交易
        btnGetChannelInfo = (Button) findViewById(R.id.btn4);//获取渠道信息

        resTv = (TextView) findViewById(R.id.tv);


        tradeTypeSpr = (Spinner) findViewById(R.id.spr1);
        channelSpr = (Spinner) findViewById(R.id.spr2);

        //交易类型
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.trade_type));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tradeTypeSpr.setAdapter(adapter1);
        tradeTypeSpr.setTag("1");
        tradeTypeSpr.setOnItemSelectedListener(this);

        //渠道
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.channel_list));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        channelSpr.setAdapter(adapter2);
        tradeTypeSpr.setTag("2");
        channelSpr.setOnItemSelectedListener(this);

        saleLy = findViewById(R.id.ly1);
        revokeLy = findViewById(R.id.ly2);

        //消费
        saleAmountEt = (TextView) findViewById(R.id.et1);
        saleAmountEt.setText(money);

        //消费撤销
        revokeVoucherNoEt = (EditText) findViewById(R.id.et2);
        revokeReferNoEt = (EditText) findViewById(R.id.et3);
        revokeAmountEt = (EditText) findViewById(R.id.et4);
        revokeDateEt = (EditText) findViewById(R.id.et5);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String str = year + "/";
        if ((monthOfYear + 1) < 10) {
            str += "0";
        }
        str += (monthOfYear + 1) + "/";
        if (dayOfMonth < 10) {
            str += "0";
        }
        str += dayOfMonth;
        tradeDate = str;
        revokeDateEt.setText(tradeDate);

        btnInitTrade.setOnClickListener(this);
        btnDealTrade.setOnClickListener(this);
        btnReleaseTrade.setOnClickListener(this);
        btnGetChannelInfo.setOnClickListener(this);
        revokeDateEt.setOnClickListener(this);
        revokeDateEt.setFocusable(false);
        btnDealTrade.setEnabled(false);
        btnReleaseTrade.setEnabled(false);
        btnGetChannelInfo.setEnabled(false);

        Utils.setAmtType(revokeAmountEt);

        revokeVoucherNoEt.setText("000032");
        revokeReferNoEt.setText("090216699798");
    }

    private void setDialog(String msg) {
        loadingDlg = new ProgressDialog(this);
        loadingDlg.setMessage(msg);
        loadingDlg.setCancelable(false);
        loadingDlg.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: //初始化交易
                new Thread() {
                    public void run() {
                        final String str = PayPluginApi.getInstance().initTrade(MainAct.this);
                        Log.e("TAG", str + "=================================");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnDealTrade.setEnabled(true);
                                btnDealTrade.setBackgroundColor(Color.parseColor("#12B7F5"));
                                btnReleaseTrade.setEnabled(true);
                                btnReleaseTrade.setBackgroundColor(Color.parseColor("#12B7F5"));
                                btnGetChannelInfo.setEnabled(true);
                                btnGetChannelInfo.setBackgroundColor(Color.parseColor("#12B7F5"));
//                            resTv.setText(str);
                            }
                        });
                    }
                }.start();
                bindServicea();
                setDialog("交易初始化...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            mHandler.sendEmptyMessage(SHOW_DIALOG);

                            String result = posAidl.signIn("");
                            try {
                                JSONObject json = new JSONObject(result);
                                String retCode = json.optString("retCode");
                                if (RET_SUCCESS.equalsIgnoreCase(retCode)) {
                                    // 签到成功，请在这里添加后续处理流程
                                    mHandler.sendEmptyMessage(INIT_SUCC);
                                } else {
                                    // 签到失败
                                    mHandler.sendEmptyMessage(INIT_FAIL);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                mHandler.sendEmptyMessage(INIT_FAIL);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mHandler.sendEmptyMessage(INIT_FAIL);
                        }
                    }
                }).start();

                break;
            case R.id.btn2: //处理交易
                final JSONObject jsonObject = new JSONObject();
                if (tradeType == 1 || tradeType == 4) { //消费
                    amountStr = saleAmountEt.getText().toString();
                    if (TextUtils.isEmpty(amountStr)) { //不可为空
                        Toast.makeText(MainAct.this, R.string.et1_not_null, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    float amount = Float.valueOf(amountStr);
                    if (amount <= 0) {  //大于0
                        Toast.makeText(MainAct.this, R.string.et1_not_zero, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        jsonObject.put("amount", amountStr);  //消费金额
                        if (tradeType == 1) {
                            jsonObject.put("trade_type", "SALE");  //交易类型
                        } else if (tradeType == 4) {
                            jsonObject.put("trade_type", "ECSALE");  //电子现金交易类型
                        }
                        jsonObject.put("business_name", "心驿"); //商户名称
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (tradeType == 2 || tradeType == 5) { //查询余额
                    try {
                        if (tradeType == 2) {
                            jsonObject.put("trade_type", "QUERY"); //交易类型
                        } else if (tradeType == 5) {
                            jsonObject.put("trade_type", "ECQUERY"); //交易类型
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (tradeType == 3) { //消费撤销
                    //流水号
                    String voucherNoStr = revokeVoucherNoEt.getText().toString();
                    if (TextUtils.isEmpty(voucherNoStr)) { //流水号不可为空.
                        Toast.makeText(MainAct.this, R.string.et2_not_null, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //系统参考号
                    String referNoStr = revokeReferNoEt.getText().toString();
                    if (TextUtils.isEmpty(referNoStr)) { //系统参考号不可为空.
                        Toast.makeText(MainAct.this, R.string.et3_not_null, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //撤销金额
                    String amountStr = revokeAmountEt.getText().toString();
                    if (TextUtils.isEmpty(amountStr)) { //撤销金额不可为空
                        Toast.makeText(MainAct.this, R.string.et4_not_null, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    float amount = Float.valueOf(amountStr);
                    if (amount <= 0) {  ////撤销金额大于0
                        Toast.makeText(MainAct.this, R.string.et4_not_zero, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        jsonObject.put("business_name", shopName);  //商户名称
                        jsonObject.put("amount", amountStr); //消费撤销金额
                        jsonObject.put("voucher_no", voucherNoStr); //流水号
                        jsonObject.put("refer_no", referNoStr); //系统参考号
                        jsonObject.put("date", tradeDate); //日期
                        jsonObject.put("trade_type", "REVOKE"); //交易类型
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (mChannel.equals("ALI_PAY")) {//支付宝支付
                    type = "2";
                    try {
//                jsonObject.put("channel", "UMS_PAY"); //渠道名称
                        jsonObject.put("channel", mChannel); //微信扫码支付
//                jsonObject.put("channel", "ALIPAY_CREATE_PAY"); //支付宝被扫支付
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Thread() {
                        public void run() {
                            final String str = PayPluginApi.getInstance().dealTrade(jsonObject.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                            resTv.setText(str);
                                    Log.e("TAG", str + "chuli交易返回的数据");
                                    PosJiaoYiBean posJiaoYiBean = JSON.parseObject(str, PosJiaoYiBean.class);
                                    int re_code = posJiaoYiBean.getRe_code();
                                    if (re_code == 0) {
                                        String out_trade_no = posJiaoYiBean.getTrade_data().getOut_trade_no();
                                        XY_DApi.posbigpay(token, ordercode, out_trade_no, type,new AsyncHttpResponseHandler() {
                                            @Override
                                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                if (null != responseBody && responseBody.length > 0) {
                                                    Log.e("TAG", new String(responseBody) + "返回的数据" + "=======zhifubao=====================");
                                                    //解析json
                                                    explainJson(new String(responseBody));
                                                }
                                            }

                                            @Override
                                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                                Log.e("TAG", "w尾款返回的数据:::" + statusCode + error.toString());
                                            }
                                        });
                                    } else if (re_code == 1) {

                                    }
                                }
                            });
                        }
                    }.start();
                } else if (mChannel.equals("WEIXIN_PAY")) {//微信支付
                    type = "3";
                    try {
                        jsonObject.put("channel", mChannel); //微信扫码支付
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Thread() {
                        public void run() {
                            final String str = PayPluginApi.getInstance().dealTrade(jsonObject.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                            resTv.setText(str);
                                    Log.e("TAG", str + "chuli交易返回的数据");
                                    PosJiaoYiBean posJiaoYiBean = JSON.parseObject(str, PosJiaoYiBean.class);
                                    int re_code = posJiaoYiBean.getRe_code();
                                    if (re_code == 0) {
                                        String out_trade_no = posJiaoYiBean.getTrade_data().getOut_trade_no();
                                        XY_DApi.posbigpay(token, ordercode, out_trade_no,type, new AsyncHttpResponseHandler() {
                                            @Override
                                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                if (null != responseBody && responseBody.length > 0) {
                                                    Log.e("TAG", new String(responseBody) + "返回的数据" + "========weixin====================");
                                                    //解析json
                                                    explainJson(new String(responseBody));
                                                }
                                            }

                                            @Override
                                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                                Log.e("TAG", "w尾款返回的数据:::" + statusCode + error.toString());
                                            }
                                        });
                                    } else if (re_code == 1) {

                                    }
                                }
                            });
                        }
                    }.start();

                } else if (mChannel.equals("UMS_PAY")) {//银联支付
                    type = "1";
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject paramJson = new JSONObject();
                                try {
                                    paramJson.put("amount", amountStr);

                                    final String result = posAidl.payCash(paramJson
                                            .toString());

                                    JSONObject json = new JSONObject(result);
                                    String retCode = json.optString("retCode");
                                    if (RET_SUCCESS.equalsIgnoreCase(retCode)) {
                                        // 消费成功，处理交易结果，可以保存、打印凭条等
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                PosYLBean posYLBean = JSON.parseObject(result,PosYLBean.class);
                                                String voucherNo = posYLBean.getBody().getVoucherNo();
                                                XY_DApi.posbigpay(token, ordercode, voucherNo,type, new AsyncHttpResponseHandler() {
                                                    @Override
                                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                        if (null != responseBody && responseBody.length > 0) {
                                                            Log.e("TAG", new String(responseBody) + "返回的数据" + "=====yinlian=======================");
                                                            //解析json
                                                            explainJson(new String(responseBody));
                                                        }
                                                    }
                                                    @Override
                                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                                        Log.e("TAG", "w尾款返回的数据:::" + statusCode + error.toString());
                                                    }
                                                });
                                            }
                                        });
// {"body":{"amount":"0.01","mchtNo":"848584092236000","tradeTime":"2016-11-25 11:04:00","termNo":"58500009","acquirer":"48480000","issBank":"建设银行","mchtName":"云刷科技","referenceNo":"633011259875","voucherNo":"1480043062722","pan":"621700*********1740"},"errMsg":"交易成功","retCode":"00"}


//                                    Log.e("xx", json.toString());
//                                    Log.e("xx", json.optString("voucherNo")+"===================================");
//                                    Log.e("xx", json.optString("referenceNo")+"===================================");
//                                    Log.e("xx", json.optString("tradeTime")+"===================================");
//                                    Log.e("xx", json.optString("mchtNo")+"===================================");
//                                    Log.e("xx", json.optString("termNo")+"===================================");
//                                    Log.e("xx", json.optString("issBank")+"===================================");
//                                    Log.e("xx", json.optString("acquirer")+"===================================");
//                                    Log.e("xx", json.optString("pan")+"===================================");
//                                    Log.e("xx", json.optString("mchtName")+"===================================");

                                    } else {
                                        // 消费失败

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

                break;
            case R.id.btn3: //释放交易
                PayPluginApi.getInstance().releaseTrade();
                btnDealTrade.setEnabled(false);
                btnReleaseTrade.setEnabled(false);
                btnGetChannelInfo.setEnabled(false);
                break;
            case R.id.btn4: //获取渠道信息
                new Thread() {
                    public void run() {
                        final String str = PayPluginApi.getInstance().getChannelInfo(mChannel);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resTv.setText(str);
                            }
                        });
                    }
                }.start();
                break;
            case R.id.et5: //消费撤销日期
                showDialog(DATA_PICKER_ID);
                break;
            default:
                break;
        }
    }

    private void bindServicea() {
        if (null == posAidl) {
            final Intent intent = new Intent("com.esapos.intent.posService");
            bindService(intent, connection, BIND_AUTO_CREATE);
        }
    }

    /**
     * 解析
     *
     * @param Json
     */
    private void explainJson(String Json) {
        PosZhiFuBean posZhiFuBean = JSON.parseObject(Json, PosZhiFuBean.class);
        String code = posZhiFuBean.getCode();
        if (code.equals("1")) {
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View convertView, int position, long id) {
        if (adapterView == tradeTypeSpr) {
            tradeType = position + 1;
            if (tradeType == 1) { //消费
                saleLy.setVisibility(View.VISIBLE);
                revokeLy.setVisibility(View.GONE);
            } else if (tradeType == 2) { //查询余额
                saleLy.setVisibility(View.GONE);
                revokeLy.setVisibility(View.GONE);
            } else if (tradeType == 3) { //消费撤销
                saleLy.setVisibility(View.GONE);
                revokeLy.setVisibility(View.VISIBLE);
            } else if (tradeType == 4) { //电子现金消费
                saleLy.setVisibility(View.VISIBLE);
                revokeLy.setVisibility(View.GONE);
            } else if (tradeType == 5) { //电子现金查余
                saleLy.setVisibility(View.GONE);
                revokeLy.setVisibility(View.GONE);
            }
        } else if (adapterView == channelSpr) {
            mChannel = CHANNEL_ARRAY[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATA_PICKER_ID:
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this, onDateSetListener, year, month, day);
        }
        return super.onCreateDialog(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayPluginApi.getInstance().releaseTrade();
    }


}
