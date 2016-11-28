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
import com.jindouy.station_android.module.adapter.KuaiJianDetailsAdapter;
import com.jindouy.station_android.module.bean.DelBankCardBean;
import com.jindouy.station_android.module.bean.HomeQueryKuaiJianBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

//import com.jindouy.station_android.module.adapter.KuaiJianDetailsAdapter;

/**
 * @author 黄辉
 * @time 2016/11/16 16:36
 * <p>
 * 快件详情
 */
public class KuaiJianDetailsActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_kuai_jian_details;
    private LinearLayout mIv_back;
    private com.cjj.MaterialRefreshLayout mRefresh;
    private ListView mList_myorder;
    private TextView tv_ruku;
    private LinearLayout ll_status;
    private ImageView iv_ewm;
    private TextView tv_addresser;
    private TextView tv_kuaijian_addressee;
    private TextView tv_kuaijian_getpiece;
    //
    private ImageView mIv_bao_icon;
    private TextView mTv_addressera;
    private TextView mTv_recipients;
    private TextView mTv_kuaijian_number;
    private TextView mTv_kuaijian_weight;
    private TextView mTv_kuaijian_time;
    private TextView mTv_start;
    private TextView mTv_start_a;
    private TextView mTv_start_b;
    private TextView mTv_destination;
    private TextView mTv_destination_a;
    private TextView mTv_destination_b;
    private TextView mTv_type;
    private TextView mTv_kuaijian_message;
    private View headview;
    private String code;                                                //1.不显示 入库   2显示入库
    private LoadingAlertDialog loadingAlertDialog;                      //弹框
    private String token;                                               //token
    private String ordernum;                                            //快件编号
    private HomeQueryKuaiJianBean homeQueryKuaiJianBean;
    private List<HomeQueryKuaiJianBean.LogisticsBean> logistics;
    private KuaiJianDetailsAdapter kuaiJianDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuai_jian_details);
        bindViews();


    }

    private void bindViews() {
        code = CacheUtils.getstr(this, "code");
        token = CacheUtils.getstr(this, "token");
        mActivity_kuai_jian_details = (LinearLayout) findViewById(R.id.activity_kuai_jian_details);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mRefresh = (com.cjj.MaterialRefreshLayout) findViewById(R.id.refresh);
        mList_myorder = (ListView) findViewById(R.id.list_myorder);
        tv_ruku = (TextView) findViewById(R.id.tv_ruku);
        tv_addresser = (TextView) findViewById(R.id.tv_addresser);
        ll_status = (LinearLayout) findViewById(R.id.ll_status);
        tv_kuaijian_addressee = (TextView) findViewById(R.id.tv_kuaijian_addressee);
        tv_kuaijian_getpiece = (TextView) findViewById(R.id.tv_kuaijian_getpiece);
        //头
        headview = View.inflate(this, R.layout.kuai_jian_details_tou, null);
        mIv_bao_icon = (ImageView) headview.findViewById(R.id.iv_bao_icon);
        mTv_addressera = (TextView) headview.findViewById(R.id.tv_addressera);
        mTv_recipients = (TextView) headview.findViewById(R.id.tv_recipients);
        mTv_kuaijian_number = (TextView) headview.findViewById(R.id.tv_kuaijian_number);
        mTv_kuaijian_weight = (TextView) headview.findViewById(R.id.tv_kuaijian_weight);
        mTv_kuaijian_time = (TextView) headview.findViewById(R.id.tv_kuaijian_time);
        mTv_start = (TextView) headview.findViewById(R.id.tv_start);
        mTv_start_a = (TextView) headview.findViewById(R.id.tv_start_a);
        mTv_start_b = (TextView) headview.findViewById(R.id.tv_start_b);
        mTv_destination = (TextView) headview.findViewById(R.id.tv_destination);
        mTv_destination_a = (TextView) headview.findViewById(R.id.tv_destination_a);
        mTv_destination_b = (TextView) headview.findViewById(R.id.tv_destination_b);
        mTv_type = (TextView) headview.findViewById(R.id.tv_type);
        mTv_kuaijian_message = (TextView) headview.findViewById(R.id.tv_kuaijian_message);
        //添加头
        mList_myorder.addHeaderView(headview);
        //判断 1 显示收件取件  2 显示入库
        initData();
        if(code.equals("1")) {
            ll_status.setVisibility(View.VISIBLE);
        }else if(code.equals("2")) {
            tv_ruku.setVisibility(View.VISIBLE);
        }else if(code.equals("3")) { //发件
            tv_addresser.setVisibility(View.VISIBLE);
        }
        tv_kuaijian_addressee.setOnClickListener(this);//收件
        tv_kuaijian_getpiece.setOnClickListener(this);//取件
        tv_ruku.setOnClickListener(this);//入库

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
        Log.e("TAG",json+"快件详情========================vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv==================");
       homeQueryKuaiJianBean = JSON.parseObject(json, HomeQueryKuaiJianBean.class);
//        HomeQueryBean.InfoBean info = homeQueryKuaiJianBean.getInfo();
        //发件人
        mTv_addressera.setText(homeQueryKuaiJianBean.getUsername());
        //收件人
        mTv_recipients.setText(homeQueryKuaiJianBean.getAppear());
        //快件编号
        ordernum = homeQueryKuaiJianBean.getOrdernum();
        mTv_kuaijian_number.setText(homeQueryKuaiJianBean.getOrdernum());
        //快件重量
        mTv_kuaijian_weight.setText(homeQueryKuaiJianBean.getWeight());
        //时间
        mTv_kuaijian_time.setText(homeQueryKuaiJianBean.getAdd_time());
        //发件地址1
        mTv_start_a.setText(homeQueryKuaiJianBean.getS_city());
        //发件地址2
        mTv_start_b.setText(homeQueryKuaiJianBean.getS_address());
        //收件地址1
        mTv_destination_a.setText(homeQueryKuaiJianBean.getE_city());
        //收件地址2
        mTv_destination_b.setText(homeQueryKuaiJianBean.getE_address());
        //快件类型
        mTv_type.setText(homeQueryKuaiJianBean.getO_class());
       logistics = homeQueryKuaiJianBean.getLogistics();
        kuaiJianDetailsAdapter = new KuaiJianDetailsAdapter(this, logistics);
        mList_myorder.setAdapter(kuaiJianDetailsAdapter);
        kuaiJianDetailsAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_kuaijian_addressee:// 点击 收件
                tv_ruku.setVisibility(View.VISIBLE);//显示入库
                ll_status.setVisibility(View.GONE);//隐藏 收件 取件
                tv_addresser.setVisibility(View.GONE);//隐藏 发件
                break;
            case R.id.tv_kuaijian_getpiece://点击 取件
                tv_addresser.setVisibility(View.VISIBLE);//显示 发件
                tv_ruku.setVisibility(View.GONE);//隐藏 入库
                ll_status.setVisibility(View.GONE);//隐藏 收件 取件
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
     * 解析json
     * @param Json
     */
    private void explainJson(String Json) {
        DelBankCardBean delBankCardBean = JSON.parseObject(Json, DelBankCardBean.class);
        String msg = delBankCardBean.getInfo().getMsg();
        int status = delBankCardBean.getStatus();
        if(status==0) {
            Toast.makeText(KuaiJianDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }else if(status==1) {
            Toast.makeText(KuaiJianDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
