package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.DelBankCardBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;
/**
 *
 *@author 黄辉
 *@time 2016/11/15 12:09
 *
 *意见反馈
*/
public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_feed_back;
    private LinearLayout mIv_back;
    private LinearLayout mLl_feed_back_leixin;
    private EditText mEt_feed_back_content;
    private TextView mTv_feed_back_tijiao;
    private String token;                                                      //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        bindViews();
    }
    /**
     * 初始化
     */
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_feed_back = (LinearLayout) findViewById(R.id.activity_feed_back);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mLl_feed_back_leixin = (LinearLayout) findViewById(R.id.ll_feed_back_leixin);
        mEt_feed_back_content = (EditText) findViewById(R.id.et_feed_back_content);
        mTv_feed_back_tijiao = (TextView) findViewById(R.id.tv_feed_back_tijiao);

        mTv_feed_back_tijiao.setOnClickListener(this);
        mLl_feed_back_leixin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_feed_back_tijiao:
                String content = mEt_feed_back_content.getText().toString().trim();
                if(!TextUtils.isEmpty(content)) {
                    JDY_JZApi.feedback(token, 1, content, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG",new String(responseBody)+"反馈提交");
                                //解析json
                                explainJson(new String(responseBody));
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG",statusCode+error.toString()+"反馈提交失败");
                        }
                    });
                }else {
                    Toast.makeText(FeedBackActivity.this, "请输入需要反馈的类容", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ll_feed_back_leixin:
                JDY_JZApi.feedbacktype(token, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (null != responseBody && responseBody.length > 0) {
                            Log.e("TAG",new String(responseBody)+"反馈类型");
                            //解析json
//                                explainJson(new String(responseBody));
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("TAG",statusCode+error.toString()+"反馈类型失败");
                    }
                });
                break;
        }
    }
    /**
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        DelBankCardBean delBankCardBean = JSON.parseObject(Json, DelBankCardBean.class);
        int status = delBankCardBean.getStatus();
        if(status == 1) {
            Toast.makeText(FeedBackActivity.this,  delBankCardBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
            mEt_feed_back_content.setText(null);
        }else if(status==0) {
            Toast.makeText(FeedBackActivity.this,  delBankCardBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
}
