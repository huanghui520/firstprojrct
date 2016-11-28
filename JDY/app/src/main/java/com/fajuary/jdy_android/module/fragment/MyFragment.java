package com.fajuary.jdy_android.module.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.fajuary.jdy_android.R;
import com.fajuary.jdy_android.module.activity.MyAccountActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * created by huanghui at 2016/11/3
 * 我的页
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.iv_icon)
    private com.fajuary.jdy_android.view.CircleImageView mIv_icon;
    @ViewInject(R.id.ll_my_account)
    private LinearLayout mLl_my_account;
    @ViewInject(R.id.ll_fajian_record)
    private LinearLayout mLl_fajian_record;
    @ViewInject(R.id.ll_guanyu_we)
    private LinearLayout mLl_guanyu_we;
    @ViewInject(R.id.ll_system_setting)
    private LinearLayout mLl_system_setting;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.my_mian, null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mLl_my_account.setOnClickListener(this);
        mLl_fajian_record.setOnClickListener(this);
        mLl_guanyu_we.setOnClickListener(this);
        mLl_system_setting.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_account ://我的账户
                Intent intent = new Intent(context, MyAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_fajian_record://发件记录

                break;
            case R.id.ll_guanyu_we://关于我们

                break;
            case R.id.ll_system_setting://系统设置

                break;
        }
    }
}
