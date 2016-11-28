package com.jindouy.station_android.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.AddBankCardBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.common.util.LogUtil;

import cz.msebera.android.httpclient.Header;

/**
 * @author 黄辉
 * @time 2016/11/9 16:06
 * <p>
 * 添加银行卡
 */
public class AddBankCardActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_add_bank_card;
    private LinearLayout mIv_back;
    private EditText mEt_card_name;                                        //银行
    private EditText mEt_card_number;                                       //卡号
    private EditText mEt_card_my_name;                                      //持卡人姓名
    private TextView mTv_add_card_queding;
    private String bank_name;                                               //银行名称
    private String card_num;                                                //银行卡号
    private String own_name;                                                //持卡人姓名
    private String token;                                                   //登陆返回的token
    private LoadingAlertDialog loadingAlertDialog;                          //加载 dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        bindViews();
    }

    private void bindViews() {
        mActivity_add_bank_card = (LinearLayout) findViewById(R.id.activity_add_bank_card);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mEt_card_name = (EditText) findViewById(R.id.et_card_name);
        mEt_card_number = (EditText) findViewById(R.id.et_card_number);
        mEt_card_my_name = (EditText) findViewById(R.id.et_card_my_name);
        mTv_add_card_queding = (TextView) findViewById(R.id.tv_add_card_queding);

        mTv_add_card_queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_card_queding:
                loadingAlertDialog = new LoadingAlertDialog(this);
                loadingAlertDialog.show("正在加载...");
                token = CacheUtils.getstr(this, "token");
                Log.e("TAG", "添加银行卡列表" + token + "llllll");
                bank_name = mEt_card_name.getText().toString().trim();
                card_num = mEt_card_number.getText().toString().trim();
                own_name = mEt_card_my_name.getText().toString().trim();
                JDY_JZApi.addBankCard(token, bank_name, card_num, own_name, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (null != responseBody && responseBody.length > 0) {
                            //解析json
                            explainJson(new String(responseBody));
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        LogUtil.e("添加银行卡成功====" + statusCode + error.toString());
                        loadingAlertDialog.dismiss();
                    }
                });
            break;
        }
    }
    /**
     * 解析Json
     * @param Json
     */
    private void explainJson(String Json){
        AddBankCardBean addBankCardBean = JSON.parseObject(Json, AddBankCardBean.class);
        String status = addBankCardBean.getStatus();
        if(status.equals("0")) {//失败
            loadingAlertDialog.dismiss();//消失
            Toast.makeText(AddBankCardActivity.this, addBankCardBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
        }else if(status.equals("1")) {//成功
            loadingAlertDialog.dismiss();
            Toast.makeText(AddBankCardActivity.this, addBankCardBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
