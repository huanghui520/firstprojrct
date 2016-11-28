// IPosAidlInterface.aidl
package com.esapos.androidpos;

interface IPosAidlInterface {

    //签到
    String signIn(String jsonData);

    //余额查询
    String balanceQuery(String jsonData);

    //消费
    String payCash(String jsonData);

    //消费撤销
    String consumeCancel(String jsonData);

    //读卡片信息
    String readCardMsg(String mode);

    //取消读卡片信息
    void cancelReadCardMsg();
}
