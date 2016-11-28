package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.DetailsBean;
import com.fajuary.xiyishop_android.module.bean.DetailsListBean;
import com.fajuary.xiyishop_android.module.bean.RefundBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.DateUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static com.fajuary.xiyishop_android.R.id.tv_number_shiyong;
/**
 * @author 黄辉
 * @time 2016/10/12 16:32
 * <p>
 * 订单详情
 */
public class DetailsActivtiy extends BaseActivity implements View.OnClickListener {
    private String orderId;//商品id
    private String goodsId;//商品id
    private String goodsName;//套餐名
    private String token;
    private LinearLayout mIv_back;
    private TextView mRl_into;
    private TextView tv_taocan;
    private TextView mTv_name_dingdanxiangqin;
    private TextView mTv_number;
    private TextView tv_tongyi;
    private TextView tv_jujue;
    private TextView tv_tuikuan_statar;
    private TextView mTv_number_shiyong;
    private TextView mTv_time;
    private TextView mTv_dindan_number;
    private TextView mTv_dindan_money;
    private TextView mTv_pay;
    private TextView mZhuangtai;
    private TextView mTv_zhuangtai_one;
    private TextView mTv_zhuangtai_two;
    private TextView tv_zhuangtai_san;
    private ListView lv_list_xiangqiang;
    private RelativeLayout yanzheng_jilu;
    private RelativeLayout weikuan_jilu;
    private List<DetailsListBean.ListBean> datas;
    private DetailsBean detailsBean;
    private TextView tv_queding_dindan;//确定
    private String excisetime;//时间
    private String sparea;
    private String spare_status;
    private String standardName;
    private String refund;//退款状态
    private String count;

    //    private com.cjj.MaterialRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_details_activtiy_list);
        bindViews();
    }
    private void bindViews() {
        orderId = getIntent().getStringExtra("orderId");
        goodsName = getIntent().getStringExtra("goodsName");//商品名
        standardName = getIntent().getStringExtra("standardName");//套餐
        refund = getIntent().getStringExtra("refund");//退款的状态
        sparea = CacheUtils.getstr(this, "spare");
        spare_status = CacheUtils.getstr(this, "spare_status");
        token = CacheUtils.getstr(this, "token");
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
//        refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mRl_into = (TextView) findViewById(R.id.rl_into);
        tv_taocan = (TextView) findViewById(R.id.tv_taocan);
        mTv_name_dingdanxiangqin = (TextView) findViewById(R.id.tv_name_dingdanxiangqin);
        mTv_number = (TextView) findViewById(R.id.tv_number);
        mTv_number_shiyong = (TextView) findViewById(tv_number_shiyong);
        mTv_time = (TextView) findViewById(R.id.tv_time);
        mTv_dindan_number = (TextView) findViewById(R.id.tv_dindan_number);
        mTv_dindan_money = (TextView) findViewById(R.id.tv_dindan_money);
        mTv_pay = (TextView) findViewById(R.id.tv_pay);
        mZhuangtai = (TextView) findViewById(R.id.zhuangtai);
        mTv_zhuangtai_one = (TextView) findViewById(R.id.tv_zhuangtai_one);
        tv_queding_dindan = (TextView) findViewById(R.id.tv_queding_dindan);
        yanzheng_jilu = (RelativeLayout) findViewById(R.id.yanzheng_jilu);
        weikuan_jilu = (RelativeLayout) findViewById(R.id.weikuan_jilu);
        tv_tongyi = (TextView) findViewById(R.id.tv_tongyi);
        tv_jujue = (TextView) findViewById(R.id.tv_jujue);
        tv_tuikuan_statar = (TextView) findViewById(R.id.tv_tuikuan_statar);
        //判断退款的状态
        if(refund.equals("0")) {
//            tv_tuikuan_statar.setText("此订单用户没有发起任何退款");
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
        }else if(refund.equals("1")) {
            tv_tuikuan_statar.setText("此订单用户发起退款");
            tv_jujue.setVisibility(View.VISIBLE);
            tv_tongyi.setVisibility(View.VISIBLE);
        }else if(refund.equals("2")) {
            tv_tuikuan_statar.setText("商家已确认");
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
        }else if(refund.equals("3")) {
            tv_tuikuan_statar.setText("退款失败");
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
        }else if(refund.equals("4")) {
            tv_tuikuan_statar.setText("退款成功");
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
        }
        initData();
        mRl_into.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
        yanzheng_jilu.setOnClickListener(this);
        weikuan_jilu.setOnClickListener(this);
        tv_queding_dindan.setOnClickListener(this);
        tv_tongyi.setOnClickListener(this);
        tv_jujue.setOnClickListener(this);
    }
    private void initData() {
        XYApi.details(token, orderId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "订单详情信息" + token + "llllll" + orderId);
                    //解析json
                    explainJson(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", new String(responseBody) + "订单详情信息" + token + "llllll" + orderId);
            }
        });
    }
    /**
     * 解析json
     *
     * @param Json
     */
    private void explainJson(String Json) {
        detailsBean = JSON.parseObject(Json, DetailsBean.class);
        String code = detailsBean.getCode();
        if(code.equals("1")) {
            if(!TextUtils.isEmpty(standardName)) {
                tv_taocan.setText(standardName);
            }
            excisetime = detailsBean.getDatas().getExcisetime();
            goodsId = detailsBean.getDatas().getGoodsId();
            mTv_number.setText("服务数量：" + detailsBean.getDatas().getPurchasequantity() + "");
            mTv_time.setText("下单时间："+ DateUtils.timedate(detailsBean.getDatas().getBuytime()));
            mTv_dindan_number.setText("订单编号："+detailsBean.getDatas().getOrderCode());
            mTv_dindan_money.setText("金额：￥"+detailsBean.getDatas().getMoney());
            mTv_name_dingdanxiangqin.setText(goodsName+"");
//        detailsBean.getDatas().
            count = detailsBean.getDatas().getCount();
            if(!count.equals("0")){
                yanzheng_jilu.setVisibility(View.VISIBLE);
                mTv_number_shiyong.setText("使用次数："+count);
            }else if(count.equals("0")) {
                yanzheng_jilu.setVisibility(View.GONE);
                mTv_number_shiyong.setText("使用次数："+"0");
            }
            //判断订单 是预付款 还是全款
            String bigpay = detailsBean.getDatas().getBigpay();
            String spare = detailsBean.getDatas().getSpare();
            //为 3时   表示为大额支付
            if(sparea.equals("3")&&spare_status.equals("2")) {
                if(bigpay.equals("1")) {//表示未付款
                    mTv_zhuangtai_one.setText("未付款");
                tv_queding_dindan.setVisibility(View.VISIBLE);
                }else if(bigpay.equals("0")) {//已付款
                    mTv_zhuangtai_one.setText("已付款");
                    tv_queding_dindan.setVisibility(View.VISIBLE);
                }
            }else if(sparea.equals("3")&&spare_status.equals("1")) {
                if(bigpay.equals("1")) {//表示未付款
                    mTv_zhuangtai_one.setText("未付款");
                    tv_queding_dindan.setVisibility(View.VISIBLE);
                }else if(bigpay.equals("0")) {//已付款
                    mTv_zhuangtai_one.setText("已付款");
                    tv_queding_dindan.setVisibility(View.VISIBLE);
                }
            }
            else if(!sparea.equals("3")){
                if(spare.equals("1")) {//全款时   就没有尾款
                    tv_queding_dindan.setVisibility(View.GONE);
                    mTv_zhuangtai_one.setText("全款");
                }else if(spare.equals("2")) {//预付款时   就有收尾款
                    mTv_zhuangtai_one.setText("预付款");
                    tv_queding_dindan.setVisibility(View.VISIBLE);
                }
            }
        }else {

        }
    }
    private String code = "1";
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_into ://商品查看
                Intent intent = new Intent(this, ShangPinXiangqingActivity.class);
                intent.putExtra("goodsId",goodsId);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_queding_dindan:
                CacheUtils.putstr(this,"code",code);
                Intent intent1 = new Intent(this, ScanActivity.class);
                startActivity(intent1);
                break;
            case R.id.yanzheng_jilu://验证记录
                Intent intent3 = new Intent(this, ValidationRecordActivity.class);
                intent3.putExtra("excisetime",excisetime);
                startActivity(intent3);
                break;
            case R.id.weikuan_jilu://尾款记录
                Intent intent2 = new Intent(this, WeiKuanRecordActivity.class);
                intent2.putExtra("orderId",orderId);
                startActivity(intent2);
                break;
            case R.id.tv_tongyi://同意退款1
                if(!count.equals("0")) {//已验证
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailsActivtiy.this);
                    dialog.setTitle("已验证订单退款");
                    dialog.setMessage("确定退款后，退款金额会从商户余额直接扣除");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();


                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.create();
                    dialog.setCancelable(false);
                    dialog.show();
                }else {//没有验证
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailsActivtiy.this);
                    dialog.setTitle("未验证订单退款");
                    dialog.setMessage("确定退款后，退款金额会从平台直接反给用户");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();

                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.create();
                    dialog.setCancelable(false);
                    dialog.show();
                }
                break;
            case R.id.tv_jujue ://拒绝退款2
                XYApi.refund(token, orderId, "2", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (null != responseBody && responseBody.length > 0) {
                            Log.e("TAG", new String(responseBody) + "拒绝退款" + token + "llllll" + orderId);
                            //解析json
                            explainJsonjj(new String(responseBody));
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("TAG", new String(responseBody) + "拒绝退款" + token + "llllll" + orderId);
                    }
                });
                break;
        }
    }

    private void getData() {
        XYApi.refund(token, orderId, "1", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "同意退款" + token + "llllll" + orderId);
                    //解析json
                    explainJsonty(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", new String(responseBody) + "同意退款" + token + "llllll" + orderId);
            }
        });
    }

    /**
     * 拒绝退款
     * @param Json
     */
    private void explainJsonjj(String Json) {
        RefundBean refundBean = JSON.parseObject(Json, RefundBean.class);
        String code = refundBean.getCode();
        if(code.equals("1")) {
            Toast.makeText(DetailsActivtiy.this, refundBean.getDatas(), Toast.LENGTH_SHORT).show();
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
            tv_tuikuan_statar.setText(refundBean.getDatas());
        }else if(code.equals("2")) {
            Toast.makeText(DetailsActivtiy.this, refundBean.getDatas(), Toast.LENGTH_SHORT).show();
            tv_tuikuan_statar.setText(refundBean.getDatas());
        }
    }

    /**
     * 同意退款
     * @param Json
     */
    private void explainJsonty(String Json) {
        RefundBean refundBean = JSON.parseObject(Json, RefundBean.class);
        String code = refundBean.getCode();
        if(code.equals("1")) {
            Toast.makeText(DetailsActivtiy.this, refundBean.getDatas(), Toast.LENGTH_SHORT).show();
            tv_jujue.setVisibility(View.GONE);
            tv_tongyi.setVisibility(View.GONE);
            tv_tuikuan_statar.setText(refundBean.getDatas());
        }else if(code.equals("2")) {
            Toast.makeText(DetailsActivtiy.this, refundBean.getDatas(), Toast.LENGTH_SHORT).show();
            tv_tuikuan_statar.setText(refundBean.getDatas());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }

}
