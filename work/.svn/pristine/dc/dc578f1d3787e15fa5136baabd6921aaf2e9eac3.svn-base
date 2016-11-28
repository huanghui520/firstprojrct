package com.fajuary.xiyishop_android.mymodule.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.widget.InputEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 发起支付
 */
public class InitiatePaymentActivity extends BaseActivity {


    @Bind(R.id.activity_initiatepayment_storeNameTxt)
    InputEditText storeNameTxt;

    @Bind(R.id.activity_initiatepayment_moneyNumTxt)
    InputEditText moneyNumTxt;

    @Bind(R.id.activity_initiatepayment_paySubmitBtn)
    Button paySubmitBtn;


    @Override
    public void createLayout() {
        setContentView(R.layout.activity_initiate_payment);
        ButterKnife.bind(this);
    }
    @Override
    public void initEvent() {
        paySubmitBtn.setOnClickListener(this);
    }
    @Override
    public void widgetClick(View view) {
        Intent intent=new Intent();
        switch ( view.getId() ){
            case R.id.activity_initiatepayment_paySubmitBtn:
                intent.setClass(this,PayQrcodeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean getIsFastClick() {
        return false;
    }

}
