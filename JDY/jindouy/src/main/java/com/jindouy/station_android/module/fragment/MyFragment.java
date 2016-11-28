package com.jindouy.station_android.module.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.FajianRecordActivity;
import com.jindouy.station_android.module.activity.GuanyuWeActivity;
import com.jindouy.station_android.module.activity.MyAccountActivity;
import com.jindouy.station_android.module.activity.SystemSettingActivity;
import com.jindouy.station_android.module.bean.MyFragmentBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cz.msebera.android.httpclient.Header;

/**
 * created by huanghui at 2016/11/3
 * 我的页
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.iv_icon)
    private com.jindouy.station_android.view.CircleImageView mIv_icon;
    @ViewInject(R.id.ll_my_account)
    private LinearLayout mLl_my_account;
    @ViewInject(R.id.ll_fajian_record)
    private LinearLayout mLl_fajian_record;
    @ViewInject(R.id.ll_guanyu_we)
    private LinearLayout mLl_guanyu_we;
    @ViewInject(R.id.ll_system_setting)
    private LinearLayout mLl_system_setting;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    private LoadingAlertDialog loadingAlertDialog;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.my_mian, null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        loadingAlertDialog = new LoadingAlertDialog(context);
        loadingAlertDialog.show("正在加载...");
        String token = CacheUtils.getstr(context, "token");
        JDY_JZApi.info(token, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "我的信息");
                    //解析json
                    explainJson(new String(responseBody));
                    loadingAlertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::我的信息" + statusCode + error.toString());
                loadingAlertDialog.dismiss();
            }
        });
        mLl_my_account.setOnClickListener(this);
        mLl_fajian_record.setOnClickListener(this);
        mLl_guanyu_we.setOnClickListener(this);
        mLl_system_setting.setOnClickListener(this);

    }
    /**
     * 我的信息  解析
     * @param Json
     */
    private void explainJson(String Json) {
        MyFragmentBean myFragmentBean = JSON.parseObject(Json, MyFragmentBean.class);
        Glide.with(context).load(myFragmentBean.getInfo().getPic()).into(mIv_icon);
        tv_name.setText(myFragmentBean.getInfo().getAddress()+myFragmentBean.getInfo().getSendname());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_account ://我的账户
                Intent intent = new Intent(context, MyAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_fajian_record://发件记录
                Intent intent3 = new Intent(context, FajianRecordActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_guanyu_we://关于我们
                Intent intent1 = new Intent(context, GuanyuWeActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_system_setting://系统设置
                Intent intent2 = new Intent(context, SystemSettingActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
