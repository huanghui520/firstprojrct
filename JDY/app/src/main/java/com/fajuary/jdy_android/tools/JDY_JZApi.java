package com.fajuary.jdy_android.tools;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 *
 *@author 黄辉
 *@time 2016/10/19 16:11
 *
 * 斤斗云接口
*/
public class JDY_JZApi {
    /**
     * 登录接口
     *
     * @param username   账户
     * @param password 密码
     * @param deviceToken  手机token
     */
    public static final String LOGIN = "&c=User&a=login";
    public static void login(String username, String password, String device_token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        params.put("device_token",device_token );
        HttpHelper.post(LOGIN, params, handler);
    }
    /**
     * 我的-我的账户
     *
     * @param token  登陆返回的token
     */
    public static final String MY_ACCOUNT = "&c=User&a=myAccount";
    public static void my_account(String token,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(MY_ACCOUNT, params, handler);
    }

    /**
     * 首页账户订单状态
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String HOME_STATE = "shop/orderdynamics.do";
    public static void home_state(String token, String shopId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        HttpHelper.get(HOME_STATE, params, handler);
    }
    /**
     * 未完成订单
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  UNFINISHED = "shop/noorder.do";
    public static void unfinished(String token, String shopId,String pageNo, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("pageNo",pageNo);
        HttpHelper.get(UNFINISHED, params, handler);
    }
    /**
     * 完成订单
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  COMPLETE = "shop/isorder.do";
    public static void complete(String token, String shopId,String pageNo, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("pageNo", pageNo);
        HttpHelper.get(COMPLETE, params, handler);
    }
    /**
     * 订单详情
     *
     * @param token  登陆返回的token
     * @param orderId 订单id
     */
    public static final String  DETAILS = "shop/orderdetails.do";
    public static void details(String token, String orderId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("orderId", orderId);
        HttpHelper.get(DETAILS, params, handler);
    }
    /**
     * 收尾款记录
     *
     * @param token  登陆返回的token
     * @param orderId 订单id
     */
    public static final String  DETAILS_LIST = "shop/shoppaylist.do";
    public static void details_list(String token, String orderId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("orderId", orderId);
        HttpHelper.get(DETAILS_LIST, params, handler);
    }
    /**
     * 提现
     *
     * @param token  登陆返回的token
     * @param bankId 银行卡id
     * @param amount 提现金额
     */
    public static final String  WITHDRAWAL = "shop/cash.do";
    public static void withdrawal(String token, String bankId, String amount,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("bankId", bankId);
        params.put("amount", amount);
        HttpHelper.get(WITHDRAWAL, params, handler);
    }
    /**
     * 提现记录
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  WITHDRAWAL_JILU = "shop/withdrawals.do";
    public static void withdrawal_jilu(String token, String shopId,String pageNo, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("pageNo", pageNo);
        HttpHelper.get(WITHDRAWAL_JILU, params, handler);
    }
    /**
     * 银行卡列表
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  BANKCARD = "shop/banklist.do";
    public static void bankcard(String token, String shopId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        HttpHelper.get(BANKCARD, params, handler);
    }
    /**
     * 删除银行卡
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  REMOVE_BANKCARD = "shop/delbank.do";
    public static void remove_bankcard(String token, String shopId,String bankId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("bankId", bankId);
        HttpHelper.get(REMOVE_BANKCARD, params, handler);
    }
    /**
     * 添加银行卡
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  ADDBANKCARD = "shop/addbank.do";
    public static void addbankcard(String token, String shopId,String card_num,String bank_account,String cardholdername, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("card_num", card_num);
        params.put("bank_account", bank_account);
        params.put("cardholdername", cardholdername);
        HttpHelper.get(ADDBANKCARD, params, handler);
    }
    /**
     * 商品查看
     *
     * @param token  登陆返回的token
     * @param shopId 商户id
     */
    public static final String  COMMODITYSEE = "shop/goodslist.do";
    public static void CommoditySee(String token, String shopId,String pageNo, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("pageNo", pageNo);
        HttpHelper.get(COMMODITYSEE, params, handler);
    }
    /**
     * 商品详情
     *
     * @param token  登陆返回的token
     * @param goodsId 商品id
     */
    public static final String  SHANGPINGXIANGQING = "shop/goodsdetails.do";
    public static void shangpingxiangqing(String token, String goodsId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("goodsId", goodsId);
        HttpHelper.get(SHANGPINGXIANGQING, params, handler);
    }
    /**
     * 获取验证码
     *
     * @param phone  手机号码
     */
    public static final String  PHONE = "SDKSendTemplateSMS.do";
    public static void myphone(String phone,  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        HttpHelper.get(PHONE, params, handler);
    }
    /**
     * 找回密码
     *
     * @param phone  手机号码
     * @param code  验证码
     * @param ip  手机登录ip
     * @param pass  密码
     */
    public static final String  RETRIEVE = "shop/shopcode.do";
    public static void retrieve(String butdphone ,String code,String ip,String pass ,String deviceToken,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("buttphone", butdphone);
        params.put("code", code);
        params.put("ip", ip);
        params.put("pass", pass);
        params.put("token", deviceToken);
        HttpHelper.get(RETRIEVE, params, handler);
    }
    /**
     * 修改密码
     *
     * @param  token
     * @param shopId  商户id
     * @param oldpass  老密码
     * @param newpass 新密码
     */
    public static final String  MODIFYPASS = "shop/modifypass.do";
    public static void modifypass(String token ,String shopId,String oldpass,String newpass ,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("oldpass", oldpass);
        params.put("newpass", newpass);
        HttpHelper.post(MODIFYPASS, params, handler);
    }
    /**
     * 版本更新
     *
     */
    public static final String  VERSION = "shop/version.do";
    public static void version(AsyncHttpResponseHandler handler) {
        HttpHelper.get(VERSION,handler);
    }
    /**
     * 验证订单
     *
     * @param token
     * @param shopId 商户id
     * @param userCode  使用码
     */
    public static final String  VERIFICATION = "shop/verificationorder.do";
    public static void verification(String token,String shopId,String usercode,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("usecode", usercode);
        HttpHelper.post(VERIFICATION, params, handler);
    }
    /**
     * 验证此单
     *
     * @param token
     * @param shopId 商户id
     * @param userCode  使用码
     */
    public static final String  SENDORDER = "shop/sendorder.do";
    public static void sendorder(String token,String shopId,String usercode,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("usecode", usercode);
        HttpHelper.get(SENDORDER, params, handler);
    }
    /**
     * 发起支付记录
     *
     * @param token
     * @param shopId 商户id
     * @param pageNo  页码
     */
    public static final String  PAY_RECORD = "shop/paynote.do";
    public static void pay_record(String token,String shopId,String pageNo,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("shopId", shopId);
        params.put("pageNo", pageNo);
        HttpHelper.get(PAY_RECORD, params, handler);
    }



}
