package com.fajuary.xiyishop_android.module.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.VersionBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.fajuary.xiyishop_android.tools.getVersionNameOrUpdater;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

import static com.fajuary.xiyishop_android.R.id.iv_back;
import static com.fajuary.xiyishop_android.R.id.tv_versions_number;

/**
 * @author 黄辉
 * @time 2016/10/13 9:39
 * <p>
 * 版本更新页面
 */
public class EditionActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_edition;
    private ImageView mIv_back;
    private RelativeLayout mRl_banben;
    private LinearLayout mLl_install;
    private TextView mTv_versions_number;
    private TextView mTv_install;
    private String versionName;
    private String version;
    private VersionBean.DatasBean datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_edition);
        bindViews();
        initData();
    }

    private void initData() {
        XYApi.version(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "版本更新====================================");
                    explainJson(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void explainJson(String json) {
        VersionBean versionBean = JSON.parseObject(json, VersionBean.class);
        datas = versionBean.getDatas();
        version = datas.getVersion();
        if (version.equals(versionName)) {//版本号一样   显示最新版本
            mRl_banben.setVisibility(View.VISIBLE);
            mLl_install.setVisibility(View.GONE);
        } else { //不一样显示 版本号
            mRl_banben.setVisibility(View.GONE);
            mLl_install.setVisibility(View.VISIBLE);
        }
        mTv_versions_number.setText("新版本" + datas.getVersion());
    }

    private void bindViews() {
        versionName = getVersionName(this);
        Log.e("versionName", versionName);
        mActivity_edition = (LinearLayout) findViewById(R.id.activity_edition);
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mRl_banben = (RelativeLayout) findViewById(R.id.rl_banben);
        mLl_install = (LinearLayout) findViewById(R.id.ll_install);
        mTv_versions_number = (TextView) findViewById(tv_versions_number);
        mTv_install = (TextView) findViewById(R.id.tv_install);
        mIv_back.setOnClickListener(this);
        mTv_install.setOnClickListener(this);
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("TAG", "Exception:" + e);
        }
        Log.e("TAG", "versionName:" + versionName);
        return versionName;
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
            case iv_back:
                finish();
                break;
            case R.id.tv_install://下载更新
                showDownloadDialog();
                break;
        }
    }

    private void showDownloadDialog() {
        new AlertDialog.Builder(this)
                .setTitle("下载最新版本")
                //.setMessage(info.getDesc())
                .setPositiveButton("立即下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //启动分线程下载apk
                        downApk();
                    }
                })
                .setCancelable(false)
                .setNegativeButton("暂不下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }



    ProgressDialog pd;
    File apkFile;
    /**
     * 下载最新的APK
     * ProgressDailog
     * apkFile
     */
    private void downApk() {
        Toast.makeText(this, "下载最新的APK", Toast.LENGTH_SHORT).show();

        //准备pd
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setTitle("正在下载最新版本");

        pd.show();
        String sdFile = getExternalFilesDir(null).getAbsolutePath();
//        File sdFile = Environment.getExternalStorageDirectory();
        apkFile = new File(sdFile, "hb.apk");
        //启动分线程下载Apk
        new Thread() {
            public void run() {
                //下载
                try {
                    getVersionNameOrUpdater.downloadAPK(getApplicationContext(), pd, apkFile, datas.getPath());
                    //下载成功
                    installApk();
                } catch (Exception e) {//下载失败
                    e.printStackTrace();
                    Log.e("TAG", "e====" + e);
                  Toast.makeText(EditionActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                } finally {
                    //移除dialog
                    pd.dismiss();
                }
            }
        }.start();

    }
    public void installApk() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        //File file = new File(s);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        startActivity(intent);
    }

}


