package com.fajuary.xiyishop_android.module.fragment;/**
 * 作者：huanghui on 2016/10/12 11:10
 */
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.activity.CommoditySeeAvtivity;
import com.fajuary.xiyishop_android.module.activity.DetailsActivtiy;
import com.fajuary.xiyishop_android.module.activity.LogInActivity;
import com.fajuary.xiyishop_android.module.activity.PayActivity;
import com.fajuary.xiyishop_android.module.activity.SetUpActivity;
import com.fajuary.xiyishop_android.module.activity.TiXianActivity;
import com.fajuary.xiyishop_android.module.activity.ToOrderManagerActivty;
import com.fajuary.xiyishop_android.module.activity.VerifyActivity;
import com.fajuary.xiyishop_android.module.bean.AccountBean;
import com.fajuary.xiyishop_android.module.bean.DingDanBean;
import com.fajuary.xiyishop_android.module.bean.OrderStatusBean;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.DateUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * created by huanghui at 2016/10/12
 * 首页
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.list_myorder)//listview
    private ListView list_myorder;
    @ViewInject(R.id.refresh)
    private com.cjj.MaterialRefreshLayout refresh;
    @ViewInject(R.id.iv_camera)
    private ImageView iv_camera;
    @ViewInject(R.id.tv_shop_name)
    private TextView tv_shop_name;
    private TextView tv_balance;           //账户余额
    private TextView tv_qiankuan;           //账户余额
    private TextView tv_totalSales;        //总销售额
    private TextView tv_amountToday;       //今日销售额
    private TextView tv_orderNum;          //今日销售额
    private TextView tv_state_dingdan;     //今日销售额
    private LinearLayout mRl_my_orderimg;  //订单管理
    private LinearLayout mLl_paymentLin;   //提现
    private LinearLayout mLl_withdraw;     //验证订单
    private LinearLayout mLl_seestoreLin;  //发起支付
    private LinearLayout mLl_verifyorderLin;//商品查看
    private LinearLayout mLl_setLin;        //设置
    private String token;                   //商户的token
    private String shopId;                  //商户的id
    private TextView tv_tishi;
    /**
     * 订单状态数据
     */
    private List<OrderStatusBean.DatasBean> datas;
    private View view;
    private View headview;//头部布局
    private AccountBean bean;
    private String pageNo;//页码
    private String shopName;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_my, null);
        x.view().inject(this, view);
        return view;
    }
    /**
     * 获取数据
     */
    @Override
    public void initData() {
        //头部
        headview = View.inflate(context, R.layout.fragment_my_head, null);
        //绑定id
        tv_balance = (TextView) headview.findViewById(R.id.tv_balance_home);
        tv_qiankuan = (TextView) headview.findViewById(R.id.tv_qiankuan);
        tv_totalSales = (TextView) headview.findViewById(R.id.tv_totalSales_home);
        tv_amountToday = (TextView) headview.findViewById(R.id.tv_amountToday_home);
        tv_orderNum = (TextView) headview.findViewById(R.id.tv_orderNum_home);
        mRl_my_orderimg = (LinearLayout) headview.findViewById(R.id.rl_my_orderimg);
        tv_state_dingdan = (TextView) headview.findViewById(R.id.tv_state_dingdan);
        mLl_paymentLin = (LinearLayout) headview.findViewById(R.id.ll_paymentLin);
        mLl_withdraw = (LinearLayout) headview.findViewById(R.id.ll_withdraw);
        mLl_seestoreLin = (LinearLayout) headview.findViewById(R.id.ll_seestoreLin);
        mLl_verifyorderLin = (LinearLayout) headview.findViewById(R.id.ll_verifyorderLin);
        mLl_setLin = (LinearLayout) headview.findViewById(R.id.ll_setLin);
        tv_tishi= (TextView) headview.findViewById(R.id.tv_tishi);
        //添加头部
        list_myorder.addHeaderView(headview);
        //设置点击事件
        mRl_my_orderimg.setOnClickListener(this);
        mLl_paymentLin.setOnClickListener(this);
        mLl_withdraw.setOnClickListener(this);
        mLl_seestoreLin.setOnClickListener(this);
        mLl_verifyorderLin.setOnClickListener(this);
        mLl_setLin.setOnClickListener(this);
        iv_camera.setOnClickListener(this);
        //获取数据
        getData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }
            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refresh.finishRefreshLoadMore();
            }
        });
    }
    private void getData() {
        token = CacheUtils.getstr(context, "token");
        shopId = CacheUtils.getstr(context, "shopId");
        shopName = CacheUtils.getstr(context, "shopName");
        /**
         * 账户信息
         */
        XYApi.home(token, shopId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "首页账户信息1" + token + "llllll" + shopId);
                    //解析json
                    explainJson(new String(responseBody));
                    refresh.finishRefresh();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::首页账户信息2" + statusCode + error.toString());
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                refresh.finishRefresh();
            }
        });
        /**
         * 首页状态
         */
        XYApi.home_state(token, shopId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "首页状态1" + token + "llllll" + shopId);
                    //解析json状态
                    explainJsonstate(new String(responseBody));
                    refresh.finishRefresh();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::首页状态2" + statusCode + error.toString());
//                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 订单动态
         */
        pageNo = "1";
        XYApi.unfinished(token, shopId, pageNo, new AsyncHttpResponseHandler() {
            /**
             * 成功
             * @param statusCode
             * @param headers
             * @param responseBody
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("TAG", new String(responseBody) + "未完成订单页" + token + "llllll" + shopId);
                if (null != responseBody && responseBody.length > 0) {
                    //解析json
                    explainJsonState(new String(responseBody));
//                    refresh.finishRefresh();
                }
            }
            /**
             * 失败
             * @param statusCode
             * @param headers
             * @param responseBody
             * @param error
             */
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", ":::" + statusCode + error.toString() + "未完成订单页");
            }
        });
    }
    /**
     * 未完成订单数
     * @param json
     */
    private void explainJsonState(String json) {
        DingDanBean dingDanBean = JSON.parseObject(json, DingDanBean.class);
        List<DingDanBean.DatasBean> datas = dingDanBean.getDatas();
        int size = datas.size();
        if(size>0&&size<10) {
            tv_state_dingdan.setText(size+"");
        }else if(size<=0) {
            tv_state_dingdan.setVisibility(View.GONE);
        }else if(size>=10) {
            tv_state_dingdan.setText("···");
        }
    }
    /**
     * 账户信息
     * @param json
     */
    private void explainJson(String json) {
        bean = JSON.parseObject(json, AccountBean.class);
        if (bean != null&&bean.getCode().equals("1")) {
            String balance = bean.getDatas().getBalance();
             double balance1 = Double.parseDouble(balance);
            if(balance1<0) {
                tv_qiankuan.setText("欠款(元)");
                tv_balance.setText(balance1-balance1-balance1+"");
            }else {
                tv_balance.setText(balance1+"");
            }
            tv_totalSales.setText("￥" + bean.getDatas().getTotalSales());
            tv_amountToday.setText("￥" + bean.getDatas().getAmountToday() + "");
            tv_orderNum.setText(bean.getDatas().getOrderNum() + "");
            tv_shop_name.setText(shopName);
        }
    }
    /**
     * 解析订单状态json
     *
     * @param json
     */
    private void explainJsonstate(String json) {
//        new ArrayList<>()
        OrderStatusBean orderstatusbean = JSON.parseObject(json, OrderStatusBean.class);
        if(orderstatusbean.getCode().equals("1")) {
            datas = orderstatusbean.getDatas();
            int size = datas.size();
           if(size>0) {
           }else if(size==0) {
               tv_tishi.setVisibility(View.VISIBLE);
           }
            //适配器 显示listview所显示的数据
            OrderStatusAdapter adapter = new OrderStatusAdapter();
            list_myorder.setDivider(null);
            list_myorder.setAdapter(adapter);
            //刷新
            adapter.notifyDataSetChanged();
            list_myorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i!=0) {
                        String orderId = datas.get(i-1).getOrderId();
                        String goodsName = datas.get(i-1).getGoodsName();
                        String refund = datas.get(i-1).getRefund()+"";
                        Intent intent = new Intent(context, DetailsActivtiy.class);
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("goodsName", goodsName);
                        intent.putExtra("refund", refund);
                        startActivity(intent);
                    }
                }
            });
        }else {
            Toast.makeText(context, "登录失败请重新登录", Toast.LENGTH_SHORT).show();
            CacheUtils.putstr(context, "token", null);
            Intent intent = new Intent(context, LogInActivity.class);
            startActivity(intent);
        }
    }
    //处理点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_orderimg://订单管理
                Intent intent = new Intent(context, ToOrderManagerActivty.class);
                startActivity(intent);
                break;
            case R.id.ll_withdraw://提现
                Intent intent1 = new Intent(context, TiXianActivity.class);
                intent1.putExtra("balance", bean.getDatas().getBalance());
                startActivity(intent1);
                break;
            case R.id.ll_verifyorderLin://验证订单
                Intent intent2 = new Intent(context, VerifyActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_paymentLin://发起支付
                Intent intent3 = new Intent(context, PayActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_seestoreLin://商品查看
                Intent intent5 = new Intent(context, CommoditySeeAvtivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_setLin://设置
                Intent intent4 = new Intent(context, SetUpActivity.class);
                startActivity(intent4);
                break;
            case R.id.iv_camera:
                Intent intent6 = new Intent(context, VerifyActivity.class);
                startActivity(intent6);
                break;
        }
    }
    //适配器Adapter
    private class OrderStatusAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();//数量
        }
        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewholder;
            if (convertView == null) {
                viewholder = new ViewHolder();
                convertView = View.inflate(context, R.layout.listview_orderstates_itemlayout, null);//加载布局
                viewholder.myTv_name = (TextView) convertView.findViewById(R.id.tv_name);//绑定id
                viewholder.myTv_cg = (TextView) convertView.findViewById(R.id.tv_cg);
                viewholder.mytv_yifw = (TextView) convertView.findViewById(R.id.tv_yifw);
                viewholder.tv_tuikuan = (TextView) convertView.findViewById(R.id.tv_tuikuan);
                 viewholder.layout= (LinearLayout) convertView.findViewById(R.id.recycleview_orderstatesitem_layout);
                convertView.setTag(viewholder);//设置tag
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            OrderStatusBean.DatasBean datasBean = datas.get(i);//获取数据
            String userName = datasBean.getUserName();
            String spare_status = datasBean.getSpare_status();
            if(!TextUtils.isEmpty(userName)) {
                    viewholder.myTv_name.setText(userName + "  于" + DateUtils.timedate(datasBean.getBuytime()) + "  购买" + datasBean.getGoodsName());//用户名称
            }else {
                    viewholder.myTv_name.setText(userName + "于" + DateUtils.timedate(datasBean.getBuytime()) + "  购买" + datasBean.getGoodsName());//用户名称
            }
            String type = datasBean.getType();
            String refund = datasBean.getRefund();
            if(refund.equals("0")) {
                if (type.equals("1")) {
                    viewholder.mytv_yifw.setVisibility(View.VISIBLE);
                    viewholder.myTv_cg.setVisibility(View.GONE);
                } else if (type.equals("2")) {
                    viewholder.myTv_cg.setVisibility(View.VISIBLE);
                    viewholder.mytv_yifw.setVisibility(View.GONE);
                }
            }else if(refund.equals("1")) {
                viewholder.tv_tuikuan.setText("退款中");
            }else if(refund.equals("2")) {
                viewholder.tv_tuikuan.setText("商家已确认");
            }else  if(refund.equals("3")) {
                viewholder.tv_tuikuan.setText("退款失败");
            }else if(refund.equals("4")) {
                viewholder.tv_tuikuan.setText("退款成功");
            }
            return convertView;
        }
    }
    static class ViewHolder {
        TextView myTv_name;//用户名称
        TextView myTv_cg;//状态成功
        TextView mytv_yifw;//状态以服务
        TextView tv_tuikuan;//退款状态
        LinearLayout layout;
    }
}
