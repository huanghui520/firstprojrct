package com.jindouy.station_android.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.HomeQueryBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static anetwork.channel.http.NetworkSdkSetting.context;

/**
 *
 *@author 黄辉
 *@time 2016/11/17 16:33
 * 收件
 *
*/
public class AddResseeActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_add_ressee;
    private EditText mEdit_content;
    private ImageView mIv_scan;
    private TextView mTv_demand;
    private String token;                                            //token
    private LoadingAlertDialog loadingAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ressee);
        bindViews();
    }
    private void bindViews() {
        token = CacheUtils.getstr(this, "token");
        mActivity_add_ressee = (LinearLayout) findViewById(R.id.activity_add_ressee);
        mEdit_content = (EditText) findViewById(R.id.edit_content);
        mIv_scan = (ImageView) findViewById(R.id.iv_scan);
        mTv_demand = (TextView) findViewById(R.id.tv_demand);
        mTv_demand.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.tv_demand: //收件查询
                CacheUtils.putstr(this,"code","2");
                String ordernum = mEdit_content.getText().toString().trim();
                if (!TextUtils.isEmpty(ordernum)) {// 订单编号不为空
                    loadingAlertDialog = new LoadingAlertDialog(this);
                    loadingAlertDialog.show("正在查询");
                    JDY_JZApi.searchorder(token, ordernum, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG", new String(responseBody) + "收件查询" + token);
                                //解析json
                                explainJson(new String(responseBody));
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", ":::收件查询" + statusCode + error.toString());
                            loadingAlertDialog.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(context, "请输入订单编号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 解析
     * @param Json
     */
    private void explainJson(String Json) {
        HomeQueryBean homeQueryBean = JSON.parseObject(Json, HomeQueryBean.class);
        HomeQueryBean.InfoBean info = homeQueryBean.getInfo();
        String str = JSON.toJSONString(info);
        int status = homeQueryBean.getStatus();
        if (status == 1) {
            loadingAlertDialog.dismiss();
            int category = homeQueryBean.getInfo().getCategory();
            if(category==1) { // 订单
                Intent intent = new Intent(this, KuaiJianDetailsActivity.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }else if(category==2) { // 包裹
                Intent intent = new Intent(this, BaoDetailsActivity.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }
        } else {
            Toast.makeText(context, homeQueryBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
            loadingAlertDialog.dismiss();
        }
    }
}
