package com.jindouy.station_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.adapter.WareHouseAdapter;
import com.jindouy.station_android.module.bean.WareHouseBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

//import com.jindouy.station_android.module.adapter.WareHouseAdapter;

/**
 *
 *@author 黄辉
 *@time 2016/11/23 14:09
 *
 *仓库列表
*/
public class WarehouseActivity extends BaseActivity {
    private LinearLayout mActivity_warehouse;
    private LinearLayout mIv_back;
    private TextView mTv_city;
    private ImageView mIv_city;
    private TextView mTv_site;
    private ImageView mIv_site;
    private TextView mTv_kuaijian;
    private ImageView mIv_kuaijian;
    private com.cjj.MaterialRefreshLayout mRefresh_warehouse;
    private ListView mLv_list_warehouse;
    private String token;
    private String category = "";
    private  String o_status= "";
    private String ordertype= "";
    private String start_time= "";
    private String end_time= "";
    private String page= "";
    private WareHouseAdapter wareHouseAdapter;
    private List<WareHouseBean.InfoBean.ListBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        bindViews();
        initData();
    }

    private void initData() {
        JDY_JZApi.warehouse(token,category,o_status,ordertype,start_time,end_time ,page,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "仓库列表" + "============================");
                    //解析json
//                    Log.e("TAG",deviceToken+"设备token+++++++++++++++++++++++");
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", "仓库列表:::" + statusCode + error.toString());
            }
        });
    }

    /**
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        WareHouseBean wareHouseBean = JSON.parseObject(Json, WareHouseBean.class);
        int status = wareHouseBean.getStatus();
        if(status==0) {
            Toast.makeText(WarehouseActivity.this,wareHouseBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
        }else if(status==1) {
           datas = wareHouseBean.getInfo().getList();
           wareHouseAdapter = new WareHouseAdapter(WarehouseActivity.this, datas);
            mLv_list_warehouse.setAdapter(wareHouseAdapter);
            wareHouseAdapter.notifyDataSetChanged();
            mLv_list_warehouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CacheUtils.putstr(WarehouseActivity.this, "code", "4");
                    WareHouseBean.InfoBean.ListBean listBean = datas.get(i);
                    String str = JSON.toJSONString(listBean);
                    Log.e("TAG",str+"============== 仓库==============================");
                    String category = listBean.getCategory();
                    if (category.equals("1")) { // 订单
                        Intent intent = new Intent(WarehouseActivity.this, KuaiJianDetailsActivity.class);
                        intent.putExtra("str", str);
                        startActivity(intent);
                    } else if (category.equals("2")) { // 包裹
                        Intent intent = new Intent(WarehouseActivity.this, BaoDetailsActivity.class);
                        intent.putExtra("str", str);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_warehouse = (LinearLayout) findViewById(R.id.activity_warehouse);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mTv_city = (TextView) findViewById(R.id.tv_city);
        mIv_city = (ImageView) findViewById(R.id.iv_city);
        mTv_site = (TextView) findViewById(R.id.tv_site);
        mIv_site = (ImageView) findViewById(R.id.iv_site);
        mTv_kuaijian = (TextView) findViewById(R.id.tv_kuaijian);
        mIv_kuaijian = (ImageView) findViewById(R.id.iv_kuaijian);
        mRefresh_warehouse = (com.cjj.MaterialRefreshLayout) findViewById(R.id.refresh_warehouse);
        mLv_list_warehouse = (ListView) findViewById(R.id.lv_list_warehouse);
    }
}
