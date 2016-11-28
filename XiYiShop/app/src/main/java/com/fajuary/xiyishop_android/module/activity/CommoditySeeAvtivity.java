package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.adapter.MyRecyclerView;
import com.fajuary.xiyishop_android.module.bean.ShangPinBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/10/13 12:41
 * <p>
 * 商品查看
 */
public class CommoditySeeAvtivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private LinearLayout iv_back;
    private MyRecyclerView myRecyclerViewAdapter;
    private com.cjj.MaterialRefreshLayout m_refresh;
    /**
     * 商品数据
     */
    private List<ShangPinBean.DatasBean> datas;
    private List<ShangPinBean.DatasBean> data;
    private int pageNo = 1;//页码
    private String token;//商户的token
    private String shopId;//商户的id
    private int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_commodity_see_avtivity);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);//返回
        m_refresh = (MaterialRefreshLayout) findViewById(R.id.m_refresh);//刷新控件
//        //表格布局
        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        initData();
        //刷新的控件
        m_refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            //刷新
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
            }
            //加载更多
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                i = i+1;

                Log.e("TAG","================"+i);
                //联网请求数据
                XYApi.CommoditySee(token,shopId,i+"", new AsyncHttpResponseHandler() {
                    //成功
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (null != responseBody && responseBody.length > 0) {
                            Log.e("TAG", new String(responseBody) + "商品查看" + token + "llllll" + shopId);
                            //解析json
                            explainJsonadd(new String(responseBody));
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("TAG", ":::商品查看" + statusCode + error.toString());
                        m_refresh.finishRefreshLoadMore();
                    }
                });
            }
        });
    }

    //增加的
    private void explainJsonadd(String json) {
        ShangPinBean shangPinBean = JSON.parseObject(json, ShangPinBean.class);
        data = shangPinBean.getDatas();
        datas.addAll(data);
        myRecyclerViewAdapter.notifyDataSetChanged();
        m_refresh.finishRefreshLoadMore();
    }

    //获取数据
    private void initData() {
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.CommoditySee(token,shopId,pageNo+"", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "商品查看" + token + "llllll" + shopId);
                    //解析json
                    explainJson(new String(responseBody));
                    m_refresh.finishRefresh();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::商品查看" + statusCode + error.toString());
                m_refresh.finishRefresh();
            }
        });

        //创建Adapter 适配器
//        myRecyclerViewAdapter = new MyRecyclerView(data);
//        mRecyclerView.setAdapter(myRecyclerViewAdapter);
////
//        myRecyclerViewAdapter.setmOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion, String data) {
////                Toast.makeText(CommoditySeeAvtivity.this, "oooooooooooooooo", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(CommoditySeeAvtivity.this, ShangPinXiangqingActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//
        //点击事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    /**
     * 解析json
     * @param json
     */
    private void explainJson(String json) {
        ShangPinBean shangPinBean = JSON.parseObject(json, ShangPinBean.class);
        datas = shangPinBean.getDatas();
        //创建Adapter 适配器
        myRecyclerViewAdapter = new MyRecyclerView(this,datas);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        //刷新
        myRecyclerViewAdapter.notifyDataSetChanged();
        myRecyclerViewAdapter.setmOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, String data) {
//                Toast.makeText(CommoditySeeAvtivity.this, "oooooooooooooooo", Toast.LENGTH_SHORT).show();
                String goodsId = datas.get(postion).getGoodsId();
                Intent intent = new Intent(CommoditySeeAvtivity.this, ShangPinXiangqingActivity.class);
                intent.putExtra("goodsId",goodsId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
