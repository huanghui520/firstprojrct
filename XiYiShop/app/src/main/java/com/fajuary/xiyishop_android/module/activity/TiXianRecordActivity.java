package com.fajuary.xiyishop_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.TIXianJiLuBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.DateUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/10/13 10:33
 * <p>
 * 提现记录页
 */
public class TiXianRecordActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout iv_back;
    private ListView lv_tixianjilu;
    private String token;//商户的token
    private String shopId;//商户的id
    private com.cjj.MaterialRefreshLayout refresh;

    /**
     * 提现记录数据
     */
//    private ArrayList<TIXianJiLuBean> data;
    private List<TIXianJiLuBean.DatasBean> datas;//提现记录数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_ti_xian_record);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        lv_tixianjilu = (ListView) findViewById(R.id.lv_tixianjilu);
        refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
        initData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();

            }

            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refresh.finishRefreshLoadMore();
            }
        });
        iv_back.setOnClickListener(this);
    }
    String  pageNo = "1";

    private void initData() {
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.withdrawal_jilu(token, shopId,pageNo, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("TAG", new String(responseBody) + "提现记录" + token + "llllll" + shopId);
                explainJson(new String(responseBody));
                refresh.finishRefresh();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::提现记录" + statusCode + error.toString());
                refresh.finishRefresh();
            }
        });

//
    }

    private void explainJson(String json) {
        TIXianJiLuBean tiXianJiLuBean = JSON.parseObject(json, TIXianJiLuBean.class);
       datas = tiXianJiLuBean.getDatas();
        //提现记录页 适配器
        lv_tixianjilu.setAdapter(new TiXianRecordAdapter());
        new TiXianRecordAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private class TiXianRecordAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewholder;
            if (convertView == null) {
                viewholder = new ViewHolder();
                convertView = View.inflate(TiXianRecordActivity.this, R.layout.tixianrecord_list_item, null);
                viewholder.mTv_money = (TextView) convertView.findViewById(R.id.tv_money);
                viewholder.mTv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewholder.mTv_shenhe = (TextView) convertView.findViewById(R.id.tv_shenhe);
                viewholder.mTv_succeed = (TextView) convertView.findViewById(R.id.tv_succeed);
                viewholder.mTv_kahao = (TextView) convertView.findViewById(R.id.tv_kahao);
                viewholder.mTv_fail = (TextView) convertView.findViewById(R.id.tv_fail);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            TIXianJiLuBean.DatasBean datasBean = datas.get(i);//获取数据
            viewholder.mTv_money.setText("提现金额：￥"+datasBean.getAmount());
            viewholder.mTv_time.setText(DateUtils.timeslashData(datasBean.getTime()));
            if("1".equals(datasBean.getPresent_state())) {
                viewholder.mTv_shenhe.setVisibility(View.VISIBLE);
                viewholder.mTv_succeed.setVisibility(View.GONE);
            }else if("2".equals(datasBean.getPresent_state())) {
                viewholder.mTv_succeed.setVisibility(View.VISIBLE);
                viewholder.mTv_shenhe.setVisibility(View.GONE);
            }else if("3".equals(datasBean.getPresent_state())) {
                viewholder.mTv_fail.setVisibility(View.VISIBLE);
                viewholder.mTv_kahao.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }


    static class ViewHolder {
          TextView mTv_money;//提现记录金额
          TextView mTv_shenhe;
          TextView mTv_succeed;
          TextView mTv_fail;
          TextView mTv_time;//提现记录时间
          TextView mTv_kahao;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
