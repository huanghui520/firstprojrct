package com.jindouy.station_android.module.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.BaoDetailsActivity;
import com.jindouy.station_android.module.activity.KuaiJianDetailsActivity;
import com.jindouy.station_android.module.adapter.AllPackPagerAdapter;
import com.jindouy.station_android.module.bean.AllPackPagerBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.jindouy.station_android.R.id.refresh;

//import com.jindouy.station_android.module.adapter.AllPackPagerAdapter;

/**
 * created by huanghui at 2016/11/18
 * 全部包裹
 */

public class AllPackPager extends BasePager {
    private TextView mTv_tishi;
    private com.cjj.MaterialRefreshLayout mRefresh;
    private ListView mLv_all_list;
    private String token;
    private LoadingAlertDialog loadingAlertDialog;                            //加载框
    private AllPackPagerAdapter allPackPagerAdapter;                        //适配器
    private List<AllPackPagerBean.InfoBean.ListBean> datas;                 //列表数据
    private String data;

    public AllPackPager(Context context) {
        super(context);
        initData();
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.all_pack_pager, null);
        mTv_tishi = (TextView) view.findViewById(R.id.tv_tishi);
        mRefresh = (com.cjj.MaterialRefreshLayout) view.findViewById(refresh);
        mLv_all_list = (ListView) view.findViewById(R.id.lv_all_list);
        return view;
    }

    @Override
    public void initData() {
        getData();
        //下拉刷新
        mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }

            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mRefresh.finishRefreshLoadMore();
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        token = CacheUtils.getstr(context, "token");
//        loadingAlertDialog = new LoadingAlertDialog(context);
//        loadingAlertDialog.show("正在加载");
        JDY_JZApi.searchmyorder(token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
//                    loadingAlertDialog.dismiss();
                    mRefresh.finishRefresh();
                    Log.e("TAG", new String(responseBody) + "快件 所有包裹" + "============================");
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                loadingAlertDialog.dismiss();
                mRefresh.finishRefresh();
                Log.e("TAG", "快件 所有包裹失败:::" + statusCode + error.toString());
            }
        });
    }

    /**
     * 解析
     *
     * @param JSon
     */
    private void explainJson(final String JSon) {
        AllPackPagerBean allPackPagerBean = JSON.parseObject(JSon, AllPackPagerBean.class);
        int status = allPackPagerBean.getStatus();
        if (status == 0) {
            Toast.makeText(context, allPackPagerBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
        } else if (status == 1) {
            datas = allPackPagerBean.getInfo().getList();
            allPackPagerAdapter = new AllPackPagerAdapter(context, datas);
            mLv_all_list.setAdapter(allPackPagerAdapter);
            allPackPagerAdapter.notifyDataSetChanged();
            mLv_all_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CacheUtils.putstr(context, "code", "3");
                    AllPackPagerBean.InfoBean.ListBean listBean = datas.get(i);
                   data = JSON.toJSONString(listBean);
                    Log.e("TAG",data+"============================================");
                    String category = listBean.getCategory();
                    if (category.equals("1")) { // 订单
                        Intent intent = new Intent(context, KuaiJianDetailsActivity.class);
                        intent.putExtra("str", data);
                        context.startActivity(intent);
                    } else if (category.equals("2")) { // 包裹
                        Intent intent = new Intent(context, BaoDetailsActivity.class);
                        intent.putExtra("str", data);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}