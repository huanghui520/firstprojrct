package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.adapter.BaoDetailsAdapter;
import com.jindouy.station_android.module.bean.DelBankCardBean;
import com.jindouy.station_android.module.bean.HomeQueryBaoBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.jindouy.station_android.R.id.refresh;

//import com.jindouy.station_android.module.adapter.BaoDetailsAdapter;

/**
 * @author 黄辉
 * @time 2016/11/16 16:37
 * <p>
 * 包详情
 */
public class BaoDetailsActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mIv_back;
    private com.cjj.MaterialRefreshLayout mRefresh;
    private ListView mList_myorder;
    private TextView tv_ruku;
    private TextView tv_send;
    private LinearLayout ll_status;
    private TextView tv_bao_addressee;
    private TextView tv_bao_getpiece;

    private View headview;                      //头
    private ImageView mIv_bao_icon;
    private TextView mTv_bao_name;
    private TextView mTv_bao_number;
    private TextView mTv_bao_weight;
    private TextView mTv_bao_time;
    private TextView mTv_bao_address;
    private TextView mTv_bao_number_to;
    private TextView mTv_bao_message;
    private String code;                                                     //1 不显示入库  2 显示入库
    private String ordernum;                                                  //包裹号
    private LoadingAlertDialog loadingAlertDialog;                            //加载框
    private String token;
    private HomeQueryBaoBean homeQueryBaoBean;
    private List<HomeQueryBaoBean.LogisticsBean> logistics;
    private BaoDetailsAdapter baoDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_details);
        code = CacheUtils.getstr(this, "code");
        bindViews();
    }

    /**
     * 初始化
     */
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");

        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRefresh = (com.cjj.MaterialRefreshLayout) findViewById(refresh);
        mList_myorder = (ListView) findViewById(R.id.list_myorder);
        tv_ruku = (TextView) findViewById(R.id.tv_ruku);
        tv_send = (TextView) findViewById(R.id.tv_send);
        ll_status = (LinearLayout) findViewById(R.id.ll_status);
        tv_bao_addressee = (TextView) findViewById(R.id.tv_bao_addressee);
        tv_bao_getpiece = (TextView) findViewById(R.id.tv_bao_getpiece);
        //头部
        headview = View.inflate(this, R.layout.bao_details_tou, null);
        mIv_bao_icon = (ImageView) headview.findViewById(R.id.iv_bao_icon);
        mTv_bao_name = (TextView) headview.findViewById(R.id.tv_bao_name);
        mTv_bao_number = (TextView) headview.findViewById(R.id.tv_bao_number);
        mTv_bao_weight = (TextView) headview.findViewById(R.id.tv_bao_weight);
        mTv_bao_time = (TextView) headview.findViewById(R.id.tv_bao_time);
        mTv_bao_address = (TextView) headview.findViewById(R.id.tv_bao_address);
        mTv_bao_number_to = (TextView) headview.findViewById(R.id.tv_bao_number_to);
        mTv_bao_message = (TextView) headview.findViewById(R.id.tv_bao_message);
        //添加头
        mList_myorder.addHeaderView(headview);
        initData();
        //判断 1 显示收件取件  2 显示入库
        if (code.equals("1")) {
            ll_status.setVisibility(View.VISIBLE);
        } else if (code.equals("2")) {//显示入库
            tv_ruku.setVisibility(View.VISIBLE);
        } else if (code.equals("3")) {//显示发包
            tv_send.setVisibility(View.VISIBLE);
        }
        tv_bao_addressee.setOnClickListener(this);//点击收包
        tv_bao_getpiece.setOnClickListener(this);//点击取包
        tv_ruku.setOnClickListener(this);
        //下拉刷新
        mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initData();
                mRefresh.finishRefresh();
            }
            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mRefresh.finishRefreshLoadMore();
            }
        });
    }
    private void initData() {
        String json = getIntent().getStringExtra("str");
        Log.e("TAG",json+"包详情========================vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv==================");
        homeQueryBaoBean = JSON.parseObject(json, HomeQueryBaoBean.class);
////        HomeQueryBean.InfoBean info = homeQueryBean.getInfo();
//        //包名
        mTv_bao_name.setText(homeQueryBaoBean.getPackname());
//        //包编号
        ordernum = homeQueryBaoBean.getOrdernum();
        mTv_bao_number.setText("包裹号："+ordernum);
//        //包重量
        mTv_bao_weight.setText(homeQueryBaoBean.getWeight());
//        //时间
        mTv_bao_time.setText(homeQueryBaoBean.getAdd_time());
//        //打包站点
        mTv_bao_address.setText(homeQueryBaoBean.getSite_name());
//        //快件数量
        mTv_bao_number_to.setText(homeQueryBaoBean.getOrder_total());
//        //适配器
        logistics = homeQueryBaoBean.getLogistics();
        baoDetailsAdapter = new BaoDetailsAdapter(this, logistics);
        mList_myorder.setAdapter(baoDetailsAdapter);
        baoDetailsAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_bao_addressee://收包
                tv_ruku.setVisibility(View.VISIBLE);//显示入库
                ll_status.setVisibility(View.GONE);//隐藏 收包 取包
                tv_send.setVisibility(View.GONE);//隐藏 发包
                break;
            case R.id.tv_bao_getpiece://取包
                tv_send.setVisibility(View.VISIBLE);//显示发包
                tv_ruku.setVisibility(View.GONE);//隐藏入库
                ll_status.setVisibility(View.GONE);//隐藏收包 取包
                break;
            case R.id.tv_ruku:
                loadingAlertDialog = new LoadingAlertDialog(this);
                loadingAlertDialog.show("正在入库");
                JDY_JZApi.receiveorder(token, ordernum, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (null != responseBody && responseBody.length > 0) {
                            loadingAlertDialog.dismiss();
                            Log.e("TAG", new String(responseBody) + "快件入库" + "============================");
                            //解析json
                            explainJson(new String(responseBody));
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("TAG", "快件入库:::" + statusCode + error.toString());
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
        String msg = delBankCardBean.getInfo().getMsg();
        int status = delBankCardBean.getStatus();
        if(status==0) {
            Toast.makeText(BaoDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }else if(status==1) {
            Toast.makeText(BaoDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
