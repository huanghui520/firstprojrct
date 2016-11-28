package com.fajuary.jdy_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.jdy_android.R;
import com.fajuary.jdy_android.module.bean.LogInBean;
import com.fajuary.jdy_android.tools.ActivityManager;
import com.fajuary.jdy_android.tools.CacheUtils;
import com.fajuary.jdy_android.tools.JDY_JZApi;
import com.fajuary.jdy_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static com.fajuary.jdy_android.R.id.et_phone;
import static com.fajuary.jdy_android.R.id.et_psw;

/**
 * @author 黄辉
 * @time 2016/11/8 10:57
 * <p>
 * 登陆
 */
public class LogInActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_log_in;
    private EditText mEt_phone;
    private EditText mEt_psw;
    private TextView mTv_langding;
    private String deviceToken;//友盟设备token
    private String loginname;//登陆 输入的用户名
    private String loginpwd;//登陆输入的用户密码
    private LoadingAlertDialog loadingAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加到
        ActivityManager.getInstance().pushActivity(LogInActivity.this);
        setContentView(R.layout.activity_log_in);
        bindViews();
    }

    private void bindViews() {
        deviceToken = CacheUtils.getstr(this, "deviceToken");
        mActivity_log_in = (LinearLayout) findViewById(R.id.activity_log_in);
        mEt_phone = (EditText) findViewById(et_phone);
        mEt_psw = (EditText) findViewById(et_psw);
        mTv_langding = (TextView) findViewById(R.id.tv_langding);
        mTv_langding.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有的activity
        ActivityManager.getInstance().popAllActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_langding:
                loginname = mEt_phone.getText().toString().trim();
                loginpwd = mEt_psw.getText().toString().trim();
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
        }
    }

    /**
     * @author 黄辉
     * @time 2016/11/8 11:22
     * <p>
     * 登陆
     */
    private void login() {
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在登陆");
        JDY_JZApi.login(loginname, loginpwd, deviceToken, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    loadingAlertDialog.dismiss();
                    Log.e("TAG", new String(responseBody) + "登陆成功返回的数据" + "============================");
                    //解析json
//                    Log.e("TAG",deviceToken+"设备token+++++++++++++++++++++++");
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                loadingAlertDialog.dismiss();
                Log.e("TAG", "登陆返回的数据失败:::" + statusCode + error.toString());
            }
        });
    }
    /**
     * 解析json
     *
     * @param json
     */
    private void explainJson(String json) {
        LogInBean logInBean = JSON.parseObject(json, LogInBean.class);
        String status = logInBean.getStatus();
        if (status.equals("0")) {//登陆失败
            Toast.makeText(LogInActivity.this, "登录失败，请检查用户或者密码是否正确", Toast.LENGTH_SHORT).show();
        } else if (status.equals("1")) {//登陆成功
            String token = logInBean.getInfo().getToken();
            String type = logInBean.getInfo().getType();
            CacheUtils.putstr(this, "token", token);
            CacheUtils.putstr(this, "type", type);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
