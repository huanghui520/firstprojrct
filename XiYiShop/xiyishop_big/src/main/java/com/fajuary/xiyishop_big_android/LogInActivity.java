package com.fajuary.xiyishop_big_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import static com.fajuary.xiyishop_big_android.R.id.et_phone;
import static com.fajuary.xiyishop_big_android.R.id.et_psw;

public class LogInActivity extends Activity implements View.OnClickListener {
    private LinearLayout mActivity_log_in;
    private EditText mEt_phone;
    private EditText mEt_psw;
    private RelativeLayout mRl_code_yanz;
    private EditText mEt_code;
    private Button mTv_huqu_codea;
    private TextView mTv_langding;
    private String loginname;
    private String loginpwd;
    private String code;
    private TimeCount TimeCount;
    private String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ipAddress = getIpAddress(this);
        Log.e("TAG", ipAddress + "---------------------");
//        使用token来判断是否登录过   直接跳转
        String id = CacheUtils.getstr(this, "id");
        if (!TextUtils.isEmpty(id)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        bindViews();
    }

    private void bindViews() {
        TimeCount = new TimeCount(60000, 1000);
        mActivity_log_in = (LinearLayout) findViewById(R.id.activity_log_in);
        mEt_phone = (EditText) findViewById(et_phone);
        mEt_psw = (EditText) findViewById(et_psw);
        mRl_code_yanz = (RelativeLayout) findViewById(R.id.rl_code_yanz);
        mEt_code = (EditText) findViewById(R.id.et_code);
        mTv_huqu_codea = (Button) findViewById(R.id.tv_huqu_codea);
        mTv_langding = (TextView) findViewById(R.id.tv_langding);
        mTv_langding.setOnClickListener(this);
        mTv_huqu_codea.setOnClickListener(this);
    }
    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTv_huqu_codea.setClickable(false);//防止重复点击
            mTv_huqu_codea.setBackgroundColor(Color.parseColor("#e8e8e8"));
            mTv_huqu_codea.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            mTv_huqu_codea.setText("获取验证码");
            mTv_huqu_codea.setClickable(true);
            mTv_huqu_codea.setBackgroundColor(Color.parseColor("#12B7F5"));
        }
    }

    /**
     * @param context
     * @return 获取手机ip地址
     */
    public static String getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        int[] ipAddr = new int[4];
        ipAddr[0] = ipAddress & 0xFF;
        ipAddr[1] = (ipAddress >> 8) & 0xFF;
        ipAddr[2] = (ipAddress >> 16) & 0xFF;
        ipAddr[3] = (ipAddress >> 24) & 0xFF;
        return new StringBuilder().append(ipAddr[0]).append(".").append(ipAddr[1]).append(".").append(ipAddr[2])
                .append(".").append(ipAddr[3]).append(".").toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_langding:
                loginname = mEt_phone.getText().toString().trim();
                loginpwd = mEt_psw.getText().toString().trim();
                code = mEt_code.getText().toString().trim();
                if (!TextUtils.isEmpty(loginname)) {
                    if (!TextUtils.isEmpty(loginpwd)) {
                        if (!TextUtils.isEmpty(code)) {
                            login();
                        } else {
                            Toast.makeText(LogInActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LogInActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_huqu_codea://获取验证码
                loginname = mEt_phone.getText().toString().trim();
                if (!TextUtils.isEmpty(loginname)) {
                    Log.e("TAG",loginname+"账号==================");
                    TimeCount.start();// 开始计时
                    XY_DApi.sendsms(loginname, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG", new String(responseBody) + "验证返回的数据");
//                                codeBean = JSON.parseObject(new String(responseBody), CodeBean.class);
//                                if (codeBean.getCode().equals("200")) {
//                                    Toast.makeText(VerifyResultActivity.this, codeBean.getDate(), Toast.LENGTH_SHORT).show();
//                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", ":::验证返回的数据" + statusCode + error.toString());
                        }
                    });
                } else {
                    Toast.makeText(LogInActivity.this, "请输入账号账号不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        XY_DApi.login(loginname, loginpwd, ipAddress, code, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "登录返回的数据");
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::登陆返回的数据" + statusCode + error.toString());
            }
        });
    }

    /**
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        LogInBean logInBean = JSON.parseObject(Json, LogInBean.class);
        String code = logInBean.getCode();
        if(code.equals("0")) {
            String id = logInBean.getDatas().getId();
            CacheUtils.putstr(this,"id",id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LogInActivity.this, logInBean.getDatas()+"", Toast.LENGTH_SHORT).show();
        }
    }
}
