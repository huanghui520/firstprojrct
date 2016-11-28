package com.jindouy.station_android.module.activity;/**
 * 作者：huanghui on 2016/11/8 09:51
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.umeng.message.PushAgent;

/**
 * created by huanghui at 2016/11/8
 * 基类
 */

public class BaseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        PushAgent.getInstance(this).onAppStart();
    }
}
