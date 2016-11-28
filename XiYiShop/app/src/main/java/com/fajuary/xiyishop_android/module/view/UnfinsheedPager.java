package com.fajuary.xiyishop_android.module.view;/**
 * 作者：huanghui on 2016/10/12 09:38
 */

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
import com.fajuary.xiyishop_android.module.bean.DingDanBean;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * created by huanghui at 2016/10/12
 * 未完成的订单页
 */

public class UnfinsheedPager extends BasePager {
    private ListView lv_list;
    private List<DingDanBean.DatasBean> datas;//未完成订单

    private com.cjj.MaterialRefreshLayout refresh;

    private String token;//商户的token
    private String shopId;//商户的id
    private TextView tv_tishi_a;
    private String spare;
    private String spare_status;

    public UnfinsheedPager(Context context) {
        super(context);
        initData();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.listview_order, null);
        lv_list = (ListView) view.findViewById(R.id.lv_list);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        tv_tishi_a = (TextView) view.findViewById(R.id.tv_tishi);
        return view;
    }
    private String pageNo;

    @Override
    public void initData() {
        getData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
                refresh.finishRefresh();
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
                    explainJson(new String(responseBody));
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
//        lv_list.setDividerHeight(0);//去掉item的分割线
    }
    /**
     * 解析json
     *
     * @param json
     */
    private void explainJson(String json) {
        DingDanBean dingDanBean = JSON.parseObject(json, DingDanBean.class);
        datas = dingDanBean.getDatas();
        //判断 没有数据   显示暂无数据
        int size = datas.size();
        if(size<1) {
            tv_tishi_a.setVisibility(View.VISIBLE);
        }
        lv_list.setAdapter(new DingDanAdapter());
        //刷新
        new DingDanAdapter().notifyDataSetChanged();
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CacheUtils.putstr(context,"spare",datas.get(i).getSpare());
                CacheUtils.putstr(context,"spare_status",datas.get(i).getSpare_status());
                String orderId = datas.get(i).getOrderId();
                String goodsName = datas.get(i).getGoodsName();
                String standardName = datas.get(i).getStandardName();
                String refund = datas.get(i).getRefund();
//                Toast.makeText(context, goodsName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailsActivtiy.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("goodsName", goodsName);
                intent.putExtra("standardName",standardName);
                intent.putExtra("refund",refund);
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
                convertView = View.inflate(context, R.layout.dingdan_list_item, null);//加载item布局
                viewHolder = new ViewHolder();
                viewHolder.Rl_xiangqing = (RelativeLayout) convertView.findViewById(R.id.Rl_xiangqing);
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_taocan = (TextView) convertView.findViewById(R.id.tv_taocan);
                viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
                viewHolder.tv_all = (TextView) convertView.findViewById(R.id.tv_all);
                viewHolder.tv_all_a = (TextView) convertView.findViewById(R.id.tv_all_a);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final DingDanBean.DatasBean datasBean = datas.get(i);//获取对象
            viewHolder.tv_taocan.setText(datasBean.getGoodsName());
            String standardName = datasBean.getStandardName();
            if(!TextUtils.isEmpty(standardName)) {

                viewHolder.tv_type.setText("套餐分类: " + datasBean.getStandardName());
            }
            viewHolder.tv_money.setText("金额：" + datasBean.getMoney()+"元");
           spare = datasBean.getSpare();
          spare_status = datasBean.getSpare_status();
            if (spare.equals("1")) {
                viewHolder.tv_all.setText("已付全款");
            } else if (spare.equals("2")) {
                viewHolder.tv_all.setText("已付预付款");
            }else if(spare.equals("3")&&spare_status.equals("2")) {
                viewHolder.tv_all.setText("未付款");
            }else if(spare.equals("3")&&spare_status.equals("1")){
                viewHolder.tv_all.setText("已付款");
            }
            String refund = datasBean.getRefund();
            if(refund.equals("0")) {
                viewHolder.tv_all_a.setText("");
            }else if(refund.equals("1")) {
                viewHolder.tv_all_a.setText("退款中");
            }else  if(refund.equals("2")) {
                viewHolder.tv_all_a.setText("商家已确认");
            }else  if(refund.equals("3")) {
                viewHolder.tv_all_a.setText("退款失败");
            }else  if(refund.equals("4")) {
                viewHolder.tv_all_a.setText("退款成功");
            }
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
        TextView tv_all;//已付全款
        TextView tv_all_a;//已付全款
        TextView tv_yu;//已预付全款
        TextView tv_weifu;//已预付全款
    }
}
