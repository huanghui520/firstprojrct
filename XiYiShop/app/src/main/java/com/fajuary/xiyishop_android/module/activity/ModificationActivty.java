package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.ModifiPwdBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

import static com.fajuary.xiyishop_android.R.id.iv_back;

/**
 * @author 黄辉
 * @time 2016/10/13 9:39
 * <p>
 * 修改密码页面
 */
public class ModificationActivty extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_modification_activty;
    private LinearLayout mIv_back;
    private EditText mEt_yuanshi_mima;
    private EditText mEt_xin_mima;
    private EditText mEt_queding_xin_mima;
    private TextView mTv_tijiao;
    private String yuanmima;//原来的密码
    private String xinmima;//新密码
    private String qxinmima;//确定新密码
    private String token;
    private String shopId;//商户的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_modification_activty);
        bindViews();
    }
    private void bindViews() {
        mActivity_modification_activty = (LinearLayout) findViewById(R.id.activity_modification_activty);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mEt_yuanshi_mima = (EditText) findViewById(R.id.et_yuanshi_mima);
        mEt_xin_mima = (EditText) findViewById(R.id.et_xin_mima);
        mEt_queding_xin_mima = (EditText) findViewById(R.id.et_queding_xin_mima);
        mTv_tijiao = (TextView) findViewById(R.id.tv_tijiao_xiugai);
        mIv_back.setOnClickListener(this);
        mTv_tijiao.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case iv_back:
                finish();
                break;
            case R.id.tv_tijiao_xiugai:
               yuanmima = mEt_yuanshi_mima.getText().toString().trim();
               xinmima = mEt_xin_mima.getText().toString().trim();
               qxinmima = mEt_queding_xin_mima.getText().toString().trim();
                if(!TextUtils.isEmpty(yuanmima)) {
                    if(!TextUtils.isEmpty(xinmima)) {
                        if(!TextUtils.isEmpty(qxinmima)) {
                            if(xinmima.equals(qxinmima)) {
                                token = CacheUtils.getstr(this, "token");
                                shopId = CacheUtils.getstr(this, "shopId");
                                XYApi.modifypass(token, shopId, yuanmima, xinmima, new AsyncHttpResponseHandler() {
                                    //成功
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        Log.e("TAG", new String(responseBody) + "修改密码" + token + "llllll" + shopId+yuanmima+xinmima);
                                        if (null != responseBody && responseBody.length > 0) {
                                            ModifiPwdBean modifiPwdBean = JSON.parseObject(new String(responseBody), ModifiPwdBean.class);
//                                            Toast.makeText(ModificationActivty.this, modifiPwdBean.getDatas(), Toast.LENGTH_SHORT).show();
                                            String code = modifiPwdBean.getCode();
                                            if(code.equals("1")) {//成功
                                                AlertDialog.Builder dialog = new AlertDialog.Builder(ModificationActivty.this);
                                                dialog.setMessage(modifiPwdBean.getDatas());
                                                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        CacheUtils.putstr(ModificationActivty.this,"token",null);
                                                        String token = CacheUtils.getString(ModificationActivty.this, "tokent");
                                                        if(android.text.TextUtils.isEmpty(token)) {
                                                            Toast.makeText(ModificationActivty.this, "请重新登录", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(ModificationActivty.this, LogInActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                                dialog.create();
                                                dialog.setCancelable(false);
                                                dialog.show();
                                            }else if(code.equals("2")) {//失败
                                                AlertDialog.Builder dialog = new AlertDialog.Builder(ModificationActivty.this);
                                                dialog.setMessage(modifiPwdBean.getDatas());
                                                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                    }
                                                });
                                                dialog.create();
                                                dialog.setCancelable(false);
                                                dialog.show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                        Log.e("TAG", "修改密码:::" + statusCode + error.toString());
                                    }
                                });
                            }else {
                                Toast.makeText(ModificationActivty.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ModificationActivty.this, "确定密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ModificationActivty.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ModificationActivty.this, "原密码不能为空", Toast.LENGTH_SHORT).show();
                }

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
