package com.fajuary.jdy_android;

import android.app.Application;

import com.fajuary.jdy_android.tools.CacheUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * created by huanghui at 2016/11/3
 * 初始化
 */
public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent mPushAgent = PushAgent.getInstance(this);
            //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                //把获取到的token保存到sp
                CacheUtils.putstr(MyAppliction.this,"deviceToken",deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

    }
}
