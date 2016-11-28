package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.adapter.TradeRecordAdapter;
import com.jindouy.station_android.module.bean.TradeRecordBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/11/9 13:34
 * 交易记录
 */
public class TradeRecordActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_trade_record;
    private LinearLayout mIv_back;
    private com.cjj.MaterialRefreshLayout mRefresh;
    private ListView mLv_list_trade_record;
    private String token;                                           //
    private int page = 1;
    private LoadingAlertDialog loadingAlertDialog;
    private List<TradeRecordBean.InfoBean.ListBean> datas;
    private TradeRecordAdapter tradeRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_record);
        bindViews();
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在加载");
        JDY_JZApi.tradelist(token, page, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "交易记录");
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", statusCode + error.toString() + "交易记录");
                loadingAlertDialog.dismiss();
            }
        });
    }

    /**
     * 解析
     *
     * @param Json
     */
    private void explainJson(String Json) {
        TradeRecordBean tradeRecordBean = JSON.parseObject(Json, TradeRecordBean.class);
        String status = tradeRecordBean.getStatus();
        if (status.equals("1")) {
            datas = tradeRecordBean.getInfo().getList();
            tradeRecordAdapter = new TradeRecordAdapter(this, datas);
            mLv_list_trade_record.setAdapter(tradeRecordAdapter);
            tradeRecordAdapter.notifyDataSetChanged();
            loadingAlertDialog.dismiss();
        } else if (status.equals("0")) {

        }
    }

    /**
     * 初始化
     */
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_trade_record = (LinearLayout) findViewById(R.id.activity_trade_record);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRefresh = (com.cjj.MaterialRefreshLayout) findViewById(R.id.refresh);
        mLv_list_trade_record = (ListView) findViewById(R.id.lv_list_trade_record);
        mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mRefresh.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mRefresh.finishRefreshLoadMore();
            }
        });
        mIv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
