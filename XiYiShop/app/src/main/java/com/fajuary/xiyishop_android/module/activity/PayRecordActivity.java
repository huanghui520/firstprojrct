package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.fajuary.xiyishop_android.module.bean.PayRecordBean;
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
 * @time 2016/10/30 10:13
 * 支付记录页
 */
public class PayRecordActivity extends BaseActivity implements View.OnClickListener {
    private ListView lv_list;
    private com.cjj.MaterialRefreshLayout refresh;
    private LinearLayout mIv_back;
    private TextView tv_tishi_a;
    private String pageNo;
    private String token;
    private String shopId;
    private List<PayRecordBean.DatasBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_pay_record);
        lv_list = (ListView) findViewById(R.id.lv_list);
        refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        tv_tishi_a = (TextView) findViewById(R.id.tv_tishi_a);
        initData();
        mIv_back.setOnClickListener(this);
    }

    public void initData() {
        getData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
                refresh.finishRefresh();

            }

            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refresh.finishRefreshLoadMore();
            }
        });

    }

    private void getData() {
        pageNo = "1";
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.pay_record(token, shopId, pageNo, new AsyncHttpResponseHandler() {
            /**
             * 成功
             * @param statusCode
             * @param headers
             * @param responseBody
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Log.e("TAG", new String(responseBody) + "发起支付记录页" + token + "llllll" + shopId);
                if (null != responseBody && responseBody.length > 0) {
                    //解析json
                    explainJson(new String(responseBody));
                    Log.e("TAG", new String(responseBody) + "发起支付记录页" + token + "llllll" + shopId);
                }
            }

            /**
             * 失败
             * @param statusCode
             * @param headers
             * @param responseBody
             * @param error
             */
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.e("TAG", new String(responseBody) + "完成订单页" + token + "llllll" + shopId);
                Log.e("TAG", ":::发起支付记录页" + statusCode + error.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 解析json
     *
     * @param json
     */
    private void explainJson(String json) {
        PayRecordBean payRecordBean = JSON.parseObject(json, PayRecordBean.class);
        datas = payRecordBean.getDatas();
        int size = datas.size();
        if(size<1) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(PayRecordActivity.this);
            dialog.setMessage("暂无数据");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        finish();


                }
            });
            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
//            tv_tishi_a.setVisibility(View.VISIBLE);
        }
        lv_list.setAdapter(new PayRecordAdapter());
        //刷新
        new PayRecordAdapter().notifyDataSetChanged();

    }

    private class PayRecordAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();//获取数量
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
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(PayRecordActivity.this, R.layout.pay_record_item, null);//加载item布局
                viewHolder = new ViewHolder();
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                viewHolder.tv_wx = (TextView) convertView.findViewById(R.id.tv_wx);
                viewHolder.tv_zfb = (TextView) convertView.findViewById(R.id.tv_zfb);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            PayRecordBean.DatasBean datasBean = datas.get(i);//获取对象
            viewHolder.tv_content.setText(datasBean.getUserName()+" 于"+ DateUtils.times(datasBean.getBuytime())+"  支付"+datasBean.getTitle()+"  "+datasBean.getMoney()+"元");
            String payment = datasBean.getPayment();
            if(payment.equals("1")) {//支付宝支付
                viewHolder.tv_zfb.setVisibility(View.VISIBLE);
                viewHolder.tv_wx.setVisibility(View.GONE);
            }else if(payment.equals("2")) {//微信支付
                viewHolder.tv_zfb.setVisibility(View.GONE);
                viewHolder.tv_wx.setVisibility(View.VISIBLE);
            }


            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv_content;
        TextView tv_wx;
        TextView tv_zfb;

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }


}