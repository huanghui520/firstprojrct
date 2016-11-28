package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.GuanyuWeBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 *
 *@author 黄辉
 *@time 2016/11/8 16:23
 *
 *关于我们
*/
public class GuanyuWeActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_guanyu_we;
    private LinearLayout mIv_back;
    private WebView mWv_guanyu_we;
    private String token;                                            //
    private LoadingAlertDialog loadingAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu_we);
        bindViews();
        initData();
    }
    /**
     * 获取数据
     */
    private void initData() {
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在加载...");
        JDY_JZApi.aboutus(token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "关于我们" + token );
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
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        GuanyuWeBean guanyuWeBean = JSON.parseObject(Json, GuanyuWeBean.class);
        String status = guanyuWeBean.getStatus();
        if(status.equals("1")) {
            mWv_guanyu_we.loadDataWithBaseURL(null, guanyuWeBean.getInfo(), "text/html", "utf-8", null);
            mWv_guanyu_we.getSettings().setJavaScriptEnabled(true);
            mWv_guanyu_we.setWebChromeClient(new WebChromeClient());
            mWv_guanyu_we.setBackgroundColor(0);
            loadingAlertDialog.dismiss();
        }else if(status.equals("0")) {
            loadingAlertDialog.dismiss();
        }
    }
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_guanyu_we = (LinearLayout) findViewById(R.id.activity_guanyu_we);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mWv_guanyu_we = (WebView) findViewById(R.id.wv_guanyu_we);
        mIv_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.iv_back:
                finish();
                break;
        }
    }
}
