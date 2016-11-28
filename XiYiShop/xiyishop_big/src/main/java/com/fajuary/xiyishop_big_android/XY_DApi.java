package com.fajuary.xiyishop_big_android;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 *
 *@author 黄辉
 *@time 2016/10/19 16:11
 *
 * 心驿接口
*/
public class XY_DApi {
    /**
     * 登录接口
     *
     * @param loginname   账户
     * @param pass 密码
     * @param ip  手机ip地址
     * @param deviceToken  手机token
     */
    public static final String LOGIN = "poslogin.do";
    public static void login(String name, String password,String ip, String code, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("password", password);
        params.put("ip", ip);
        params.put("code",code );
        HttpHelper.post(LOGIN, params, handler);
    }
    /**
     * 获取验证码
     *
     * @param loginname   账户
     * @param pass 密码
     * @param ip  手机ip地址
     * @param deviceToken  手机token
     */
    public static final String SENDSMS = "sendsms.do";
    public static void sendsms(String name, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("name", name);
        HttpHelper.post(SENDSMS, params, handler);
    }
    /**
     * 验证订单
     *
     * @param token
     * @param shopId 商户id
     * @param userCode  使用码
     */
    public static final String  POSISORDER = "/posisorder.do";
    public static void posisorder(String token,String usecode,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("usecode", usecode);
        HttpHelper.post(POSISORDER, params, handler);
    }
    /**
     * 收款完成
     *
     * @param token
     * @param shopId 商户id
     * @param userCode  使用码
     */
    public static final String  POSBIGPAY = "/posbigpay.do";
    public static void posbigpay(String token,String ordercode,String out_trade_no,String type,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("orderCode", ordercode);
        params.put("out_trade_no", out_trade_no);
        params.put("type", type);
        HttpHelper.post(POSBIGPAY, params, handler);
    }


}
