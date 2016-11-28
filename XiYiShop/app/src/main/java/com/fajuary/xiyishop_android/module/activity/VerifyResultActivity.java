package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.CodeBean;
import com.fajuary.xiyishop_android.module.bean.SendorderBean;
import com.fajuary.xiyishop_android.module.bean.VerifyResultBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.DateUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static com.fajuary.xiyishop_android.R.id.tv_dingdan_shuliang;


/**
 * @author 黄辉
 * @time 2016/10/23 23:10
 * <p>
 * 扫描结果---》验证订单
 */
public class VerifyResultActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_verify_result;
    private LinearLayout mIv_back;
    private RelativeLayout rl_dingdan_b;
    private RelativeLayout rl_dingdan_a;
    private RelativeLayout rl_code_yanz;
    private TextView mTv_dingdan_name;
    private TextView mTv_dingdan_see;
    private TextView mTv_dingdan_shuliang;
    private TextView mTv_dingdan_time;
    private TextView mTv_dingdan_number;
    private TextView mTv_advance;
    private TextView mTv_prepaid;
    private TextView tv_tuikuan_statar;
    private TextView tv_yanzheng_dingdan;
    private TextView mTv_weikuan;
    private TextView tv_dingdan_shuliang_shiy;
    private Button tv_huqu_code;
    private EditText et_code;
    private String usercode;//扫码结果
    private String token;
    private String shopId;//商户id
    private VerifyResultBean.DatasBean datas;//返回来的数据
    private String code;
    private String sparea;
    private String spare_status;
    private String phone;//手机号码
    private CodeBean codeBean;
    private String num;//购买的服务的数量
    private String count;//以服务的次数
    private TimeCount time;//倒计时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_verify_result);
        code = CacheUtils.getstr(this, "code");

        bindViews();
        initData();
    }
    private void initData() {
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.verification(token, shopId, usercode, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "验证订单页面" + token + "====" + shopId + "===" + usercode);
//                    //解析json
                    explainJson(new String(responseBody));
//                    refresh.finishRefresh();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", "验证订单页面:::" + statusCode + error.toString());
            }
        });
    }
    //验证订单 解析方法
    private void explainJson(String json) {
        VerifyResultBean verifyResultBean = JSON.parseObject(json, VerifyResultBean.class);
        String refund = verifyResultBean.getDatas().getRefund();
        if(refund.equals("0")) {

        }else if(refund.equals("1")) {
            tv_tuikuan_statar.setText("退款中");
            rl_code_yanz.setVisibility(View.GONE);//验证码隐藏
            tv_yanzheng_dingdan.setVisibility(View.GONE);
        }else if(refund.equals("2")) {
            tv_tuikuan_statar.setText("已退款");
            rl_code_yanz.setVisibility(View.GONE);//验证码隐藏
            tv_yanzheng_dingdan.setVisibility(View.GONE);
        }else if(refund.equals("3")) {

        }
        phone = verifyResultBean.getDatas().getPhone();
        if (verifyResultBean.getCode().equals("1")) {
            datas = verifyResultBean.getDatas();
            num = datas.getNum();
            mTv_dingdan_name.setText(datas.getGoodsName());
            mTv_dingdan_shuliang.setText("购买数量：" + num);
            count = datas.getCount();
            if (!count.equals("0")) {
                tv_dingdan_shuliang_shiy.setText("使用次数：" + count);
                mTv_weikuan.setEnabled(true);
                mTv_weikuan.setBackgroundColor(Color.parseColor("#12B7F5"));
            } else if (count.equals("0")) {
                tv_dingdan_shuliang_shiy.setText("使用次数：" + "0");
            }

            mTv_dingdan_time.setText("下单时间：" + DateUtils.timeslash(datas.getBuytime()));
            mTv_dingdan_number.setText("订单编号：" + datas.getOrdercode());
            mTv_advance.setText("金额：￥" + datas.getMoney());
            String spare = datas.getSpare();
            String bigpay = datas.getBigpay();
            if(bigpay.equals("1")) {//未付款
                mTv_prepaid.setText("未付款");
                mTv_weikuan.setEnabled(true);
                mTv_weikuan.setBackgroundColor(Color.parseColor("#12B7F5"));
                tv_yanzheng_dingdan.setVisibility(View.GONE);
                rl_code_yanz.setVisibility(View.GONE);
            }else {
                if (spare.equals("1")) {
                    mTv_prepaid.setText("全款");
                    mTv_weikuan.setVisibility(View.GONE);
                } else if (spare.equals("2")) {
                    mTv_prepaid.setText("预付款");
                }
            }
            /**
             * 购买服务的数量 等于是用的数量  就没有验证此单和获取验证码
             */
            if(count.equals(num)) {
                tv_yanzheng_dingdan.setVisibility(View.GONE);
                rl_code_yanz.setVisibility(View.GONE);
            }
        } else if (verifyResultBean.getCode().equals("2")) {
            rl_dingdan_b.setVisibility(View.GONE);//影藏
            rl_dingdan_a.setVisibility(View.GONE);
            tv_yanzheng_dingdan.setVisibility(View.GONE);
            mTv_weikuan.setVisibility(View.GONE);
            AlertDialog.Builder dialog = new AlertDialog.Builder(VerifyResultActivity.this);
            dialog.setMessage("没有该服务");
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            //不关闭写法
            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }
    }
    private void bindViews() {
        sparea = CacheUtils.getstr(this, "spare");
        spare_status = CacheUtils.getstr(this, "spare_status");
        usercode = getIntent().getStringExtra("result");
        mActivity_verify_result = (LinearLayout) findViewById(R.id.activity_verify_result);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mTv_dingdan_name = (TextView) findViewById(R.id.tv_dingdan_name);
        rl_dingdan_b = (RelativeLayout) findViewById(R.id.rl_dingdan_b);
        rl_dingdan_a = (RelativeLayout) findViewById(R.id.rl_dingdan_a);
        mTv_dingdan_see = (TextView) findViewById(R.id.tv_dingdan_see);
        mTv_dingdan_shuliang = (TextView) findViewById(tv_dingdan_shuliang);
        mTv_dingdan_time = (TextView) findViewById(R.id.tv_dingdan_time);
        mTv_dingdan_number = (TextView) findViewById(R.id.tv_dingdan_number);
        mTv_advance = (TextView) findViewById(R.id.tv_advance);
        mTv_prepaid = (TextView) findViewById(R.id.tv_prepaid);
        tv_tuikuan_statar = (TextView) findViewById(R.id.tv_tuikuan_statar);
        tv_yanzheng_dingdan = (TextView) findViewById(R.id.tv_yanzheng_dingdan);
        mTv_weikuan = (TextView) findViewById(R.id.tv_weikuan);
        tv_dingdan_shuliang_shiy = (TextView) findViewById(R.id.tv_dingdan_shuliang_shiy);
        rl_code_yanz = (RelativeLayout) findViewById(R.id.rl_code_yanz);
        tv_huqu_code = (Button) findViewById(R.id.tv_huqu_code);
        et_code= (EditText) findViewById(R.id.et_code);
        if (!TextUtils.isEmpty(code)) {
            tv_yanzheng_dingdan.setVisibility(View.GONE);
            rl_code_yanz.setVisibility(View.GONE);
        }
        //验证码倒计时
        time = new TimeCount(60000, 1000);
        mIv_back.setOnClickListener(this);
        mTv_dingdan_see.setOnClickListener(this);
        mTv_weikuan.setOnClickListener(this);
        tv_yanzheng_dingdan.setOnClickListener(this);
        tv_huqu_code.setOnClickListener(this);
        if (code.equals("1")) {
            mTv_weikuan.setEnabled(true);
            mTv_weikuan.setBackgroundColor(Color.parseColor("#12B7F5"));
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_weikuan:
                String goodsName = datas.getGoodsName();
                String orderGoodsId = datas.getOrderGoodsId();
                Intent intent1 = new Intent(this, WeiKuanActivity.class);
                intent1.putExtra("token", token);
                intent1.putExtra("shopId", shopId);
                intent1.putExtra("goodsName", goodsName);
                intent1.putExtra("orderGoodsId", orderGoodsId);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_dingdan_see://查看商品
                final Intent intent = new Intent(this, ShangPinXiangqingActivity.class);
                intent.putExtra("goodsId", datas.getGoodsId());
                startActivity(intent);
                break;
            case R.id.tv_yanzheng_dingdan:
                String code = et_code.getText().toString().trim();
                if(!TextUtils.isEmpty(code)) {
                tv_yanzheng_dingdan.setClickable(false);
                    Log.e("TAG","验证此订单页面" + token + "====" + shopId + "===" + usercode);
                    XYApi.sendorder(token, shopId, usercode,phone,code, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG", new String(responseBody) + "验证此订单页面" + token + "====" + shopId + "===" + usercode);
                                SendorderBean sendorderBean = JSON.parseObject(new String(responseBody), SendorderBean.class);
                                String code = sendorderBean.getCode();
                                if (code.equals("1")) {//验证成功   设置验证此单不能点击
                                    tv_yanzheng_dingdan.setClickable(false);
                                    tv_yanzheng_dingdan.setText("此单已验证");
                                    tv_yanzheng_dingdan.setBackgroundColor(Color.parseColor("#CCCCCC"));

                                    mTv_weikuan.setEnabled(true);
                                    mTv_weikuan.setBackgroundColor(Color.parseColor("#12B7F5"));
                                    initData();
                                    Toast.makeText(VerifyResultActivity.this, sendorderBean.getDatas(), Toast.LENGTH_SHORT).show();
                                } else if (code.equals("2")) {//没有成功     显示提示消息
                                    Toast.makeText(VerifyResultActivity.this, sendorderBean.getDatas(), Toast.LENGTH_SHORT).show();
                                    tv_yanzheng_dingdan.setClickable(true);
                                }else if(code.equals("403")) {
                                    Toast.makeText(VerifyResultActivity.this, "验证码输入有误!", Toast.LENGTH_SHORT).show();
                                    tv_yanzheng_dingdan.setClickable(true);
                                }

                                Log.e("TAG", statusCode + "");
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", "验证此订单页面:::" + statusCode + error.toString());
                            Toast.makeText(VerifyResultActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                            tv_yanzheng_dingdan.setClickable(true);
                        }
                    });
                }else {
                    tv_yanzheng_dingdan.setClickable(true);
                    Toast.makeText(VerifyResultActivity.this, "用户的验证码不能为空", Toast.LENGTH_SHORT).show();
                }
            case R.id.tv_huqu_code://获取验证码
                time.start();// 开始计时
                if (!TextUtils.isEmpty(phone)) {
                    XYApi.myphone(phone, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG", new String(responseBody) + "验证返回的数据");
                                codeBean = JSON.parseObject(new String(responseBody), CodeBean.class);
                                if (codeBean.getCode().equals("200")) {
                                    Toast.makeText(VerifyResultActivity.this, codeBean.getDate(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", ":::验证返回的数据" + statusCode + error.toString());
                        }
                    });
                } else {

                    Toast.makeText(VerifyResultActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_huqu_code.setClickable(false);//防止重复点击
            tv_huqu_code.setBackgroundColor(Color.parseColor("#e8e8e8"));
            tv_huqu_code.setText(millisUntilFinished / 1000 + "秒");
        }
        @Override
        public void onFinish() {
            tv_huqu_code.setText("获取验证码");
            tv_huqu_code.setClickable(true);
        }
    }
}
