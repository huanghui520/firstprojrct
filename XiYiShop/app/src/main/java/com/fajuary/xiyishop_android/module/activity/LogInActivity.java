package com.fajuary.xiyishop_android.module.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.LogInBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.fajuary.xiyishop_android.widget.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;


/**
 * @author 黄辉
 * @time 2016/10/14 16:17
 * <p>
 * 登陆页面
 */
public class LogInActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_langding, tv_zhaohui;
    private EditText et_phone;
    private EditText et_psw;
    private String loginname;//账号
    private String loginpwd;//密码
    private String ip;//手机ip
    private String deviceToken;//设备token
    private String token;
    private String shopId;
    private LoadingAlertDialog loadingAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(LogInActivity.this);
        setContentView(R.layout.activity_log_in);
        String token = CacheUtils.getstr(this, "token");
        String shopId = CacheUtils.getstr(this, "shopId");
        Log.e("TAG", token+"========"+null);
        //使用token来判断是否登录过   直接跳转
        if (!TextUtils.isEmpty(token)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        inatData();
    }
    private void inatData() {
        tv_langding = (TextView) findViewById(R.id.tv_langding);
        tv_zhaohui = (TextView) findViewById(R.id.tv_zhaohui);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_psw = (EditText) findViewById(R.id.et_psw);
        //点击事件
        tv_langding.setOnClickListener(this);//登陆
        tv_zhaohui.setOnClickListener(this);//忘记密码
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
    /**
     * 登录接口
     */
    private void login() {
        ip = getIpAddress(this);//获取手机的ip
        deviceToken = CacheUtils.getstr(this, "deviceToken");//获取手机的token
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在登陆");
        //联网请求数据
        XYApi.login(loginname, loginpwd, ip, deviceToken, new AsyncHttpResponseHandler() {
            //成功
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    loadingAlertDialog.dismiss();
                    Log.e("TAG", new String(responseBody) + "登陆返回的数据"+"============================");
                    //解析json
//                    Log.e("TAG",deviceToken+"设备token+++++++++++++++++++++++");
                    explainJson(new String(responseBody));
                }
            }
            //失败
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                loadingAlertDialog.dismiss();
                Log.e("TAG", "登陆返回的数据:::" + statusCode + error.toString());
                Toast.makeText(LogInActivity.this, "登陆异常" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_langding://登陆
                loginname = et_phone.getText().toString().trim();
                loginpwd = et_psw.getText().toString().trim();
                if (!TextUtils.isEmpty(loginname)) {
                    if (!TextUtils.isEmpty(loginpwd)) {
                        login();
                    } else {
                        Toast.makeText(LogInActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_zhaohui://找回密码
                Intent intent1 = new Intent(this, ChangePasswordActivity.class);
                startActivity(intent1);
                break;
        }
    }
    /**
     * 解释登录json
     *
     * @param json
     */
    private void explainJson(String json) {
        LogInBean bean = JSON.parseObject(json, LogInBean.class);
        if (bean != null && bean.getCode().equals("2")) {//登陆失败
            Toast.makeText(LogInActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
        } else if (bean != null && bean.getCode().equals("1")) {//登陆成功
            Toast.makeText(LogInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            //登陆成功后保存数据 token 及用户状态
            otherOperation(bean);
        } else if (bean != null && bean.getCode().equals("3")){
            Toast.makeText(LogInActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void otherOperation(LogInBean bean) {
        String id = bean.getDatas().getToken();
        String shopId = bean.getDatas().getShopId();
        String shopName = bean.getDatas().getShopName();
        CacheUtils.putstr(this, "token", id);
        CacheUtils.putstr(this, "shopId", shopId);
        CacheUtils.putstr(this, "shopName", shopName);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有的activity
        ActivityManager.getInstance().popActivity(this);
    }
}
