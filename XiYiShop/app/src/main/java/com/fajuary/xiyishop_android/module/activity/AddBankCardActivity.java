package com.fajuary.xiyishop_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.AddCardBean;
import com.fajuary.xiyishop_android.module.bean.BankCardStateBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/10/14 15:19
 * <p>
 * 添加银行卡页面
 */
public class AddBankCardActivity extends BaseActivity implements View.OnClickListener {
    private String token;                     //商户的token
    private String shopId;                     //商户的id
    private String number;                     //银行卡
    private String kaihuhan;                  //开户行
    private String name;                      //户名

    private LinearLayout iv_back;            //返回
    private EditText et_zhanghao;            //账号
    private EditText et_kaihuhan;            //开户行
    private EditText et_name;                //姓名
    private TextView tv_queding;             //确定
    private BankCardStateBean bankCardBean;  //银行卡信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_add_bank_card);
        initData();
    }
    /**
     * @author 黄辉
     * @time 2016/10/18 10:25
     */
    private void initData() {
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        et_zhanghao = (EditText) findViewById(R.id.et_zhanghao);
        et_kaihuhan = (EditText) findViewById(R.id.et_kaihuhan);
        tv_queding = (TextView) findViewById(R.id.tv_queding);
        et_name = (EditText) findViewById(R.id.et_name);

        iv_back.setOnClickListener(this);//点击事件
        tv_queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_queding:
                token = CacheUtils.getstr(this, "token");
                shopId = CacheUtils.getstr(this, "shopId");
                //获取输入框中的类容
                number = et_zhanghao.getText().toString().trim();//银行账号
                kaihuhan = et_kaihuhan.getText().toString().trim();//开户行
                name = et_name.getText().toString().trim();//姓名
                XYApi.addbankcard(token,shopId,number,kaihuhan,name,new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("TAG", new String(responseBody) + "添加银行卡列表" + token + "llllll" + shopId);
                        AddCardBean addCardBean = JSON.parseObject(new String(responseBody), AddCardBean.class);
                        String code = addCardBean.getCode();
                        if(code.equals("1")) {
                            Intent intent = new Intent(AddBankCardActivity.this, BankCardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                            Toast.makeText(AddBankCardActivity.this, addCardBean.getDatas(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("TAG", "添加银行卡列表:::" + statusCode + error.toString());
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().popActivity(this);
    }
}
