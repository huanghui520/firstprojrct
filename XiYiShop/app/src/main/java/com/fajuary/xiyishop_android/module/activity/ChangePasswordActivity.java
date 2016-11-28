package com.fajuary.xiyishop_android.module.activity;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.CodeBean;
import com.fajuary.xiyishop_android.module.bean.RetrieveBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static com.fajuary.xiyishop_android.R.id.tv_huqu_code;

/**
 * @author 黄辉
 * @time 2016/10/14 17:05
 * <p>
 * 找回密码页面
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_change_password;
    private LinearLayout mIv_back;//返回
    private EditText et_phone_yz;//手机号
    private EditText mEt_code;//验证码
    private TextView mEt_huqu_code;//获取验证码
    private EditText mEt_pwd;//密码
    private EditText mEt_queding_pwd;//确认新密码
    private TextView mTv_retrieve;//提交
    private String phone;//手机号码
    private CodeBean codeBean;//获取验证码返回的
    private String myphone;
    private String code;
    private String pwd;
    private String queding_pwd;
    private String ip;
    private String deviceToken;
    private TimeCount TtimeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_change_password);
        bindViews();
    }

    private void bindViews() {
        TtimeCount = new TimeCount(60000, 1000);
        mActivity_change_password = (LinearLayout) findViewById(R.id.activity_change_password);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        et_phone_yz = (EditText) findViewById(R.id.et_phone_yz);
        mEt_code = (EditText) findViewById(R.id.et_code);
        mEt_huqu_code = (TextView) findViewById(tv_huqu_code);
        mEt_pwd = (EditText) findViewById(R.id.et_pwd);
        mEt_queding_pwd = (EditText) findViewById(R.id.et_queding_pwd);
        mTv_retrieve = (TextView) findViewById(R.id.tv_retrieve);
        mEt_huqu_code.setOnClickListener(this);
        mTv_retrieve.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case tv_huqu_code://获取验证码
                TtimeCount.start();
                phone = et_phone_yz.getText().toString().trim();//获取手机号码
                if (!TextUtils.isEmpty(phone)) {
                    XYApi.myphone(phone, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                            Log.e("TAG", new String(responseBody) + "验证返回的数据");
                                codeBean = JSON.parseObject(new String(responseBody), CodeBean.class);
                                if (codeBean.getCode().equals("200")) {
                                    Toast.makeText(ChangePasswordActivity.this, codeBean.getDate(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", ":::验证返回的数据" + statusCode + error.toString());
                        }
                    });

                } else {

                    Toast.makeText(ChangePasswordActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_retrieve://找回密码
                deviceToken = CacheUtils.getstr(this, "deviceToken");
                ip = getIpAddress(this);//获取手机的ip
                myphone = et_phone_yz.getText().toString().trim();
                code = mEt_code.getText().toString().trim();
                pwd = mEt_pwd.getText().toString().trim();
                queding_pwd = mEt_queding_pwd.getText().toString().trim();
                if (!TextUtils.isEmpty(myphone)) {
                    if (!TextUtils.isEmpty(code)) {
                        if (!TextUtils.isEmpty(pwd)) {
                            if (!TextUtils.isEmpty(queding_pwd)) {
                                if (pwd.equals(queding_pwd)) {
                                    Log.e("TAG","找回密码"+"phone"+myphone+"code"+code+"ip"+ip+"pwd"+pwd);
                                    XYApi.retrieve(myphone,code,ip,pwd,deviceToken, new AsyncHttpResponseHandler() {
                                        //成功
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                            Log.e("TAG", new String(responseBody)+"找回密码"+"phone"+phone+"code"+code+"ip"+ip+"pwd"+pwd);
                                            RetrieveBean retrieveBean = JSON.parseObject(new String(responseBody), RetrieveBean.class);
                                            Toast.makeText(ChangePasswordActivity.this, retrieveBean.getDatas().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                            Log.e("TAG", "找回密码:::" + statusCode + error.toString());
                                        }
                                    });
                                }else {
                                    Toast.makeText(ChangePasswordActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(ChangePasswordActivity.this, "确定密码不能为空", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ChangePasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ChangePasswordActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangePasswordActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.iv_back:
                finish();
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mEt_huqu_code.setClickable(false);//防止重复点击
            mEt_huqu_code.setBackgroundColor(Color.parseColor("#e8e8e8"));
            mEt_huqu_code.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            mEt_huqu_code.setText("获取验证码");
            mEt_huqu_code.setClickable(true);
        }
    }
}
