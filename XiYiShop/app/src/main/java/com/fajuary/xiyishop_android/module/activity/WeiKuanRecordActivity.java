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
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.DetailsListBean;
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
 * @time 2016/10/29 20:52
 * <p>
 * 尾款记录页
 */
public class WeiKuanRecordActivity extends BaseActivity implements View.OnClickListener {
    private ListView lv_list_weikuan;
    private TextView tv_tishi;
    private LinearLayout iv_back;
    private String orderId;//订单id
    private String token;
    private List<DetailsListBean.ListBean> datas;
    private DetailsActivtiyAdapter detailsActivtiyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_wei_kuan_record);
        orderId = getIntent().getStringExtra("orderId");
        token = CacheUtils.getstr(this, "token");
        lv_list_weikuan = (ListView) findViewById(R.id.lv_list_weikuan);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        tv_tishi = (TextView) findViewById(R.id.tv_tishi);
        iv_back.setOnClickListener(this);
        initData();
    }
    private void initData() {
        XYApi.details_list(token,orderId , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("TAG", new String(responseBody) + "尾款记录" + token + "llllll" + orderId);
                if (null != responseBody && responseBody.length > 0) {
                    explainJsonlist(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", new String(responseBody) + "尾款记录" + token + "llllll" + orderId);
            }
        });
    }

    /*
    解析json
     */
    private void explainJsonlist(String json) {
        DetailsListBean detailsListBean = JSON.parseObject(json, DetailsListBean.class);
        datas = detailsListBean.getDatas();
        int size = datas.size();
        Log.e("TAG",size+"-------------------------------------------------------------");
        if(size<1) {
            tv_tishi.setVisibility(View.VISIBLE);
//            lv_list_weikuan.setVisibility(View.GONE);
        }
        detailsActivtiyAdapter = new DetailsActivtiyAdapter();
        lv_list_weikuan.setDivider(null);
        lv_list_weikuan.setAdapter(detailsActivtiyAdapter);
        detailsActivtiyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private class DetailsActivtiyAdapter extends BaseAdapter {
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
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(WeiKuanRecordActivity.this, R.layout.activity_details_list_item, null);//加载布局
                viewHolder.tv_details_list_item = (TextView) convertView.findViewById(R.id.tv_details_list_item);//绑定id
                viewHolder.tv_details_list_item_time = (TextView) convertView.findViewById(R.id.tv_details_list_item_time);//绑定id
                convertView.setTag(viewHolder);//设置tag
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            DetailsListBean.ListBean listBean = datas.get(i);
            viewHolder.tv_details_list_item.setText("向" + listBean.getUserName() + "收尾款" + listBean.getMoney());
            viewHolder.tv_details_list_item_time.setText(DateUtils.timedate(listBean.getTime()));
            return convertView;
        }
    }
        static class ViewHolder {
            TextView tv_details_list_item;
            TextView tv_details_list_item_time;
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
