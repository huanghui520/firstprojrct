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
import com.jindouy.station_android.module.adapter.TiXianRecordAdapter;
import com.jindouy.station_android.module.bean.TiXianRecordBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/11/9 13:24
 * 提现记录
 */
public class TiXianRecordActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_ti_xian_record;
    private LinearLayout mIv_back;
    private com.cjj.MaterialRefreshLayout mRefresh;
    private ListView mLv_list_tixian_record;
    private String token;                                                       //
    private int page = 1;
    private List<TiXianRecordBean.InfoBean.ListBean> datas;                     //数据
    private TiXianRecordAdapter tiXianRecordAdapter;                            //适配器
    private LoadingAlertDialog loadingAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian_record);
        bindViews();
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show("正在加载");
        JDY_JZApi.aplywithlist(token, page, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "提现记录");
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", statusCode + error.toString() + "提现记录");
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
        TiXianRecordBean tiXianRecordBean = JSON.parseObject(Json, TiXianRecordBean.class);
        String status = tiXianRecordBean.getStatus();
        if (status.equals("1")) {
            datas = tiXianRecordBean.getInfo().getList();
            tiXianRecordAdapter = new TiXianRecordAdapter(this, datas);
            mLv_list_tixian_record.setAdapter(tiXianRecordAdapter);
            //适配器刷新
            tiXianRecordAdapter.notifyDataSetChanged();
            loadingAlertDialog.dismiss();
        } else if (status.equals("0")) {

        }


    }

    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_ti_xian_record = (LinearLayout) findViewById(R.id.activity_ti_xian_record);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRefresh = (com.cjj.MaterialRefreshLayout) findViewById(R.id.refresh);
        mLv_list_tixian_record = (ListView) findViewById(R.id.lv_list_tixian_record);
        //刷新
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
