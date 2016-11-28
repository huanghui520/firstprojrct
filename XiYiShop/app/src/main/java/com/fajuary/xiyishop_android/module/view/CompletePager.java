package com.fajuary.xiyishop_android.module.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.activity.DetailsActivtiy;
import com.fajuary.xiyishop_android.module.bean.UnfinishedBean;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * created by huanghui at 2016/10/11
 * 完成订单页
 */
public class CompletePager extends BasePager {
    private ListView lv_list;
    private List<UnfinishedBean.DatasBean> datas;//未完成订单
    private com.cjj.MaterialRefreshLayout refresh;
    private TextView tv_tishi;
    private TextView tv_tishi_a;
    private String token;//商户的token
    private String shopId;//商户的id
    private List<UnfinishedBean.DatasBean> data;//加载联网请求的数据
    private DingDanAdapter dingDanAdapter;
    public CompletePager(Context context) {
        super(context);
//        LogUtil.e("未完成");
        initData();
        Log.e("TAG",pageNo+"--------------------------------------------------------------------");

    }
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.listview_order, null);
        lv_list = (ListView) view.findViewById(R.id.lv_list);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        tv_tishi_a = (TextView) view.findViewById(R.id.tv_tishi_a);
        tv_tishi = (TextView) view.findViewById(R.id.tv_tishi);
        pageNo = 1;
        Log.e("TAG",pageNo+"--------------------------vvvvvvvvvvvvv------------------------------------------");
        return view;
    }
    private int pageNo = 1;
    @Override
    public void initData() {
        getData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                pageNo = 1;
                getData();
                refresh.finishRefresh();
            }
            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                pageNo = pageNo + 1;
                XYApi.complete(token, shopId, pageNo + "", new AsyncHttpResponseHandler() {
                    /**
                     * 成功
                     * @param statusCode
                     * @param headers
                     * @param responseBody
                     */
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("TAG", new String(responseBody) + "完成订单页" + token + "llllll" + shopId);
                        if (null != responseBody && responseBody.length > 0) {
                            //解析json
                            explainJsonAdd(new String(responseBody));
                            refresh.finishRefreshLoadMore();
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
//                Log.e("TAG", new String(responseBody) + "完成订单页" + token + "llllll" + shopId);
                        Log.e("TAG", ":::" + statusCode + error.toString());
                        refresh.finishRefreshLoadMore();
                    }
                });
            }
        });
    }

    /**
     * 加载  解析json
     *
     * @param json
     */
    private void explainJsonAdd(String json) {
        UnfinishedBean dingDanBean = JSON.parseObject(json, UnfinishedBean.class);
        data = dingDanBean.getDatas();
        datas.addAll(data);
//        getData();
        dingDanAdapter.notifyDataSetChanged();
    }
    private void getData() {
        pageNo = 1;
        token = CacheUtils.getstr(context, "token");
        shopId = CacheUtils.getstr(context, "shopId");
        XYApi.complete(token, shopId, pageNo + "", new AsyncHttpResponseHandler() {
            /**
             * 成功
             * @param statusCode
             * @param headers
             * @param responseBody
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("TAG", new String(responseBody) + "完成订单页" + token + "llllll" + shopId);
                if (null != responseBody && responseBody.length > 0) {
                    //提示
                    tv_tishi.setVisibility(View.GONE);
                    //解析json
                    explainJson(new String(responseBody));
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
//                Log.e("TAG", new String(responseBody) + "完成订单页" + token + "llllll" + shopId);
                Log.e("TAG", ":::" + statusCode + error.toString());
            }
        });
    }
    /**
     * 解析json
     *
     * @param json
     */
    private void explainJson(String json) {
        UnfinishedBean dingDanBean = JSON.parseObject(json, UnfinishedBean.class);
        datas = dingDanBean.getDatas();
        int size = datas.size();
        if (size < 1) {
            //提示
            tv_tishi.setVisibility(View.VISIBLE);
        }
        Log.e("TAG",pageNo+"----------------------------------hhhhhhhhhhhh----------------------------------");
        dingDanAdapter = new DingDanAdapter();
        lv_list.setAdapter(dingDanAdapter);
        dingDanAdapter.notifyDataSetChanged();
        //刷新
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CacheUtils.putstr(context, "spare", datas.get(i).getSpare());
                CacheUtils.putstr(context, "spare_status", datas.get(i).getSpare_status());
                String orderId = datas.get(i).getOrderId();
                String goodsName = datas.get(i).getGoodsName();
                String refund = datas.get(i).getRefund();
                Intent intent = new Intent(context, DetailsActivtiy.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("goodsName", goodsName);
                intent.putExtra("refund", refund);
                context.startActivity(intent);
            }
        });
    }
    private class DingDanAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();//获取数量
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
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.dingdan_list_itemtwo, null);//加载item布局
                viewHolder = new ViewHolder();
                viewHolder.Rl_xiangqing = (RelativeLayout) convertView.findViewById(R.id.Rl_xiangqing);
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_taocan = (TextView) convertView.findViewById(R.id.tv_taocan);
                viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
                viewHolder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final UnfinishedBean.DatasBean datasBean = datas.get(i);//获取对象
            viewHolder.tv_taocan.setText(datasBean.getGoodsName());
            String standardName = datasBean.getStandardName();
            if (!TextUtils.isEmpty(standardName)) {
                viewHolder.tv_type.setText("套餐分类: " + datasBean.getStandardName());
            }
            viewHolder.tv_money.setText("金额：" + datasBean.getMoney() + "元");
            //加载图片
            Glide.with(context).load(datasBean.getThumbnail()).into(viewHolder.iv_icon);
            return convertView;
        }
    }
    static class ViewHolder {
        RelativeLayout Rl_xiangqing;
        ImageView iv_icon;//图片
        TextView tv_taocan;//套餐名称
        TextView tv_type;//套餐类型
        TextView tv_money;//金额
        TextView tv_state;//金额
    }
}