package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.Person;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.google.gson.Gson;

import cz.msebera.android.httpclient.util.TextUtils;

/**
 * @author 黄辉
 * @time 2016/10/13 9:05
 * 发起支付页面
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_tijiao;
    private LinearLayout iv_back;
    private EditText et_title, et_money;
    private TextView tv_zhifujilu;

    private String str;
    private String str1;
    private String neirong = null;
    private String shopId;//商户id
    private String s;
    private String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_pay);
        tv_tijiao = (TextView) findViewById(R.id.tv_tijiao);
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        et_title = (EditText) findViewById(R.id.et_title);
        et_money = (EditText) findViewById(R.id.et_money);
        tv_zhifujilu = (TextView) findViewById(R.id.tv_zhifujilu);
        /**
         * 设置小数点后只能有两位数
         */
        et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_money.setText(s);
                        et_money.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_money.setText(s);
                    et_money.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_money.setText(s.subSequence(0, 1));
                        et_money.setSelection(1);
                        return;
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        initData();
    }

    private void initData() {

        tv_tijiao.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_zhifujilu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tijiao://发起二维码支付申请
                shopId = CacheUtils.getstr(this, "shopId");
                shopName = CacheUtils.getstr(this, "shopName");
                str = et_title.getText().toString().trim() + "";
                str1 = et_money.getText().toString().trim() + "";
                Log.e("TAG", shopId + "-" + str + "-" + str1);
//                {"code":"1","msg":"查询成功！","datas":{"buytime":1477530437,"count":0,"goodsId":117,"money":0.1,"orderCode":"16102709070001","purchasequantity":1,"spare":2}}
                Person person = new Person(str, str1, shopId, "1", shopName);//类型为1    全款
                s = toString(person);
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str1)) {
                        Intent intent = new Intent(this, TwoPayActivity.class);
                        intent.putExtra("ti", s);
                        intent.putExtra("to", str1);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PayActivity.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PayActivity.this, "商品标题不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_zhifujilu://支付记录
                Intent intent = new Intent(this, PayRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }

    public String toString(Object obj) {
        Gson gson = new Gson();
        String s = gson.toJson(obj);
        return s;
    }

}
