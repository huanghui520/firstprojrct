package com.jindouy.station_android.tools;


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
     * 我的信息
     *
     * @param token   账户
     */
    public static final String INFO = "&c=User&a=getInfo";
    public static void info(String token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(INFO, params, handler);
    }
    /**
     * 我的-我的账户
     * @param token  登陆返回的token
     */
    public static final String MY_ACCOUNT = "&c=User&a=myAccount";
    public static void my_account(String token,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(MY_ACCOUNT, params, handler);
    }
    /**
     * 我的-添加银行卡
     * @param token  登陆返回的token
     * @param bank_name  银行名称
     * @param card_num  银行卡号
     * @param own_name  持卡人
     */
    public static final String ADDBANKCARD = "&c=User&a=addBankCard";
    public static void addBankCard(String token,String bank_name,String card_num,String own_name,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("bank_name", bank_name);
        params.put("card_num", card_num);
        params.put("own_name", own_name);
        HttpHelper.post(ADDBANKCARD, params, handler);
    }
    /**
     * 我的-提现记录
     * @param token  登陆返回的token
     * @param page  页码
     */
    public static final String TIXIAN_RECORD = "&c=User&a=aplyWithList";
    public static void tixian_record(String token,String page,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("page", page);
        HttpHelper.get(TIXIAN_RECORD, params, handler);
    }
    /**
     * 我的-银行卡列表
     * @param token  登陆返回的token
     * @param page  页码
     */
    public static final String BANKCARDLIST = "&c=User&a=bankCardList";
    public static void bankcardlist(String token,int page,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("page", page);
        HttpHelper.get(BANKCARDLIST, params, handler);
    }
    /**
     * 我的-银行卡列表
     * @param token  登陆返回的token
     * @param card_id  银行卡id
     */
    public static final String DELBANKCARD = "&c=User&a=delBankCard";
    public static void delbankcard(String token,String  card_id,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("card_id", card_id);
        HttpHelper.post(DELBANKCARD, params, handler);
    }
    /**
     * 我的-提现申请
     * @param token  登陆返回的token
     * @param card_id  银行卡id
     * @param money  提现金额
     */
    public static final String APLYWITH = "&c=User&a=aplyWith";
    public static void aplywith(String token,String  card_id,String money,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("card_id", card_id);
        params.put("money", money);
        HttpHelper.post(APLYWITH, params, handler);
    }
    /**
     * 我的-提现记录
     * @param token  登陆返回的token
     * @param token  页码
     */
    public static final String APLYWITHLIST = "&c=User&a=aplyWithList";
    public static void aplywithlist(String token,int page,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("page", page);
        HttpHelper.get(APLYWITHLIST, params, handler);
    }
    /**
     * 我的-交易记录
     * @param token  登陆返回的token
     * @param page  页码
     */
    public static final String TRADELIST = "&c=User&a=tradeList";
    public static void tradelist(String token,int page,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("page", page);
        HttpHelper.get(TRADELIST, params, handler);
    }
    /**
     * 我的-发件记录
     * @param token  登陆返回的token
     * @param page  页码
     */
    public static final String SENDORDERLIST = "&c=Index&a=sendOrderList";
    public static void sendOrderList(String token,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(SENDORDERLIST, params, handler);
    }
    /**
     * 我的-关于我们
     * @param token  登陆返回的token
     */
    public static final String ABOUTUS = "&c=User&a=aboutUs";
    public static void aboutus(String token,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(ABOUTUS, params, handler);
    }
    /**
     * 我的-反馈提交
     * @param token  登陆返回的token
     * @param id  反馈类型的id
     * @param content  类容
     */
    public static final String FEEDBACK = "&c=User&a=feedback";
    public static void feedback(String token,int id,String content,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("id", id);
        params.put("content", content);
        HttpHelper.post(FEEDBACK, params, handler);
    }

    /**
     * 我的-反馈类型
     * @param token  登陆返回的token
     * @param id  反馈类型的id
     * @param content  类容
     */
    public static final String FEEDBACKTYPE = "&c=User&a=feedbackType";
    public static void feedbacktype(String token,AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.get(FEEDBACKTYPE, params, handler);
    }
    /**
     * 首页—查询订单
     * @param token  登陆返回的token
     * @param ordernum  编号    &c=Index&a=searchOrder
     * @param category 类型（1订单，2包）
     */
    public static final String SEARCHORDER = "&c=Index&a=searchOrder";
    public static void searchorder(String token,String ordernum, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("ordernum", ordernum);
        HttpHelper.post(SEARCHORDER, params, handler);
    }
    /**
     * 快件入库
     * @param token  登陆返回的token
     * @param ordernum  订单号
     */
    public static final String RECEIVEORDER = "&c=Index&a=receiveOrder";
    public static void receiveorder(String token,String ordernum, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("ordernum", ordernum);
        HttpHelper.post(RECEIVEORDER, params, handler);
    }
    /**
     * 仓库查询-列表
     * @param token  登陆返回的token
     * @param category  =1为订单，=2为包
     * @param o_status  =1为在本站，=2为已发出
     * @param ordertype  =1为同城，=2为跨城
     * @param start_time  开始时间（2016-11-15 00：00：00）
     * @param end_time  结束时间（2016-11-15 00：00：00）
     * @param page  页数默认为1
     */
    public static final String SEARCHWAREHOUSE = "&c=Index&a=searchWarehouse";
    public static void warehouse(String token,String category,String o_status,String ordertype,String start_time, String end_time,String page, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("category", category);
        params.put("o_status", o_status);
        params.put("ordertype", ordertype);
        params.put("start_time", start_time);
        params.put("end_time", end_time);
        params.put("page", page);
        HttpHelper.post(SEARCHWAREHOUSE, params, handler);
    }
    /**
     * 快件 -列表 全部
     * @param token  登陆返回的token
     */
    public static final String SEARCHMYORDER = "&c=Index&a=searchMyOrder";
    public static void searchmyorder(String token, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        HttpHelper.post(SEARCHMYORDER, params, handler);
    }
    /**
     * 快件 -列表 同城
     * @param token  登陆返回的token
     */
    public static final String SEARCHMYORDERA = "&c=Index&a=searchMyOrder";
    public static void searchmyordera(String token,String ordertype, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("ordertype", ordertype);
        HttpHelper.post(SEARCHMYORDERA, params, handler);
    }
    /**
     * 快件 -列表 跨城
     * @param token  登陆返回的token
     */
    public static final String SEARCHMYORDERB = "&c=Index&a=searchMyOrder";
    public static void searchmyorderb(String token,String ordertype, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("ordertype", ordertype);
        HttpHelper.post(SEARCHMYORDERB, params, handler);
    }

}
