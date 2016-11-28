package com.jindouy.station_android.module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.TradeRecordBean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 * 交易记录 适配器
 */

public class TradeRecordAdapter extends BaseAdapter {
    private Context context;
    private  List<TradeRecordBean.InfoBean.ListBean> datas;
    public TradeRecordAdapter(Context context, List<TradeRecordBean.InfoBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
      ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.trade_record_list, null);
            holder.tv_tixian_money = (TextView) convertView.findViewById(R.id.tv_tixian_money);
            holder.tv_tixian_time = (TextView) convertView.findViewById(R.id.tv_tixian_time);
            holder.tv_tixian_status = (TextView) convertView.findViewById(R.id.tv_tixian_status);
            convertView.setTag(holder);//为view设置标签
        } else {//取出holder
            holder = (ViewHolder) convertView.getTag();//the Object stored in this view as a tag
        }
        holder.tv_tixian_money.setText(datas.get(i).getMoney_name());
        holder.tv_tixian_time.setText(datas.get(i).getTime());
        String pay_status = datas.get(i).getRel_type_name();
        return convertView;
    }
    class ViewHolder {
        TextView tv_tixian_money;
        TextView tv_tixian_time;
        TextView tv_tixian_status;
    }

}
