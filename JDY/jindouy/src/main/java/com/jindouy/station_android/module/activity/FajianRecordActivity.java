package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 *
 *@author 黄辉
 *@time 2016/11/8 16:21
 *
 *发件记录
*/
public class FajianRecordActivity extends BaseActivity {
    private LinearLayout mActivity_fajian_record;
    private LinearLayout mIv_back;
    private com.cjj.MaterialRefreshLayout mRefresh_fajian;
    private ListView mLv_list_fajian;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fajian_record);
        bindViews();
    }
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_fajian_record = (LinearLayout) findViewById(R.id.activity_fajian_record);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRefresh_fajian = (com.cjj.MaterialRefreshLayout) findViewById(R.id.refresh_fajian);
        mLv_list_fajian = (ListView) findViewById(R.id.lv_list_fajian);
        initData();
        mRefresh_fajian.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mRefresh_fajian.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                mRefresh_fajian.finishRefreshLoadMore();
            }
        });
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        JDY_JZApi.sendOrderList(token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "我的-我的发件记录" + token );
                    //解析json
//                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::我的-我的发件记录" + statusCode + error.toString());
            }
        });
    }


}
