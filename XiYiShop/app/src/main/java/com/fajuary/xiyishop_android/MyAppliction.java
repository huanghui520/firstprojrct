package com.fajuary.xiyishop_android;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.x;

/**
 * created by huanghui at 2016/10/12
 */

public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        new Thread() {
//            public void run() {
//                final String str = PayPluginApi.getInstance().initTrade(MyAppliction.this);
//                Log.e("TAG",str+"==============woccccccsjdsjhjdsnkldsm=============================");
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        btnDealTrade.setEnabled(true);
////                        btnReleaseTrade.setEnabled(true);
//////                        resTv.setText(str);
////                    }
////                });
//            }
//        }.start();

        x.Ext.init(this);
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
//        BugtagsOptions options = new BugtagsOptions.Builder().
//                trackingLocation(true).//是否获取位置
//                trackingCrashLog(true).//是否收集crash
//                trackingConsoleLog(true).//是否收集console log
//                trackingUserSteps(true).//是否收集用户操作步骤
//                versionName("1.0.1").//自定义版本名称
//                versionCode(10).//自定义版本号
//                build();
        //在这里初始化
        Bugtags.start("63f036c74ed9c1462f23ffd4683637ee", this, Bugtags.BTGInvocationEventBubble);
    }
}
