package com.jindouy.station_android.module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.WareHouseBean;

import java.util.List;

/**
 * created by huanghui at 2016/11/23
 */

public class WareHouseAdapter extends BaseAdapter {
    private Context context;
    private List<WareHouseBean.InfoBean.ListBean> list;
    public WareHouseAdapter(Context context, List<WareHouseBean.InfoBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolderb viewholderb;
       ViewHoldera viewholdera;
        int type = getItemViewType(i);
        if (type == 0) {//订单
            if (convertView == null) {
                viewholdera = new ViewHoldera();
                convertView = View.inflate(context, R.layout.all_pack_pager_list_kuaijian_a, null);//加载布局
                viewholdera.mIv_bao_icon = (ImageView) convertView.findViewById(R.id.iv_bao_icon);//绑定id
                viewholdera.mTv_addressera = (TextView) convertView.findViewById(R.id.tv_addressera);
                viewholdera.mTv_recipients = (TextView) convertView.findViewById(R.id.tv_recipients);
                viewholdera.mTv_kuaijian_number = (TextView) convertView.findViewById(R.id.tv_kuaijian_number);
                viewholdera.mTv_kuaijian_weight = (TextView) convertView.findViewById(R.id.tv_kuaijian_weight);
                viewholdera.mTv_kuaijian_time = (TextView) convertView.findViewById(R.id.tv_kuaijian_time);
                viewholdera.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
                viewholdera.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewholdera);//设置tag
            } else {
                viewholdera = (ViewHoldera) convertView.getTag();
            }
            viewholdera.mTv_addressera.setText(list.get(i).getUsername());//发件人
            viewholdera.mTv_recipients.setText(list.get(i).getAppear());//收件人
            viewholdera.mTv_kuaijian_number.setText("快递单号："+list.get(i).getOrdernum());//快递单号
            viewholdera.mTv_kuaijian_weight.setText(list.get(i).getWeight());//快递重量
            viewholdera.mTv_kuaijian_time.setText(list.get(i).getAdd_time());//快递时间
            viewholdera.tv_info.setText(list.get(i).getO_status_name());//快件在哪里
            viewholdera.tv_time.setText(list.get(i).getAdd_time());//物流时间
        } else if (type == 1) {//包裹
            if (convertView == null) {
                viewholderb = new ViewHolderb();
                convertView = View.inflate(context, R.layout.all_pack_pager_list_bao_a, null);//加载布局
                viewholderb.mIv_bao_icon = (ImageView) convertView.findViewById(R.id.iv_bao_icon);//绑定id
                viewholderb.mTv_bao_name = (TextView) convertView.findViewById(R.id.tv_bao_name);
                viewholderb.mTv_bao_number = (TextView) convertView.findViewById(R.id.tv_bao_number);
                viewholderb.mTv_bao_weight = (TextView) convertView.findViewById(R.id.tv_bao_weight);
                viewholderb.mTv_bao_time = (TextView) convertView.findViewById(R.id.tv_bao_number);
                viewholderb.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
                viewholderb.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewholderb);//设置tag
            } else {
                viewholderb = ( ViewHolderb) convertView.getTag();
            }
            //设置
            viewholderb.mTv_bao_name.setText(list.get(i).getPackname());
            viewholderb.mTv_bao_number.setText("快递单号："+list.get(i).getOrdernum());
            viewholderb.mTv_bao_weight.setText(list.get(i).getWeight());
            viewholderb.mTv_bao_time.setText(list.get(i).getAdd_time());
            viewholderb.tv_info.setText(list.get(i).getO_status_name());//快件在哪里
            viewholderb.tv_time.setText(list.get(i).getAdd_time());//物流时间
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;

    }
    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getCategory().equals("1")) {
            return 0;
        } else if (list.get(position).getCategory().equals("2")) {
            return 1;
        }
        return 1;
    }
    static class ViewHoldera {
        ImageView mIv_bao_icon;
        TextView mTv_addressera;
        TextView mTv_recipients;
        TextView mTv_kuaijian_number;
        TextView mTv_kuaijian_weight;
        TextView mTv_kuaijian_time;
        TextView tv_info;
        TextView tv_time;

    }
    static class ViewHolderb {
        ImageView mIv_bao_icon;
        TextView mTv_bao_name;
        TextView mTv_bao_number;
        TextView mTv_bao_weight;
        TextView mTv_bao_time;
        TextView tv_info;
        TextView tv_time;
    }
}
