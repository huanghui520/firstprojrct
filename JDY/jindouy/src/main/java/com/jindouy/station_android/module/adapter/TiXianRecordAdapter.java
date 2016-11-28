package com.jindouy.station_android.module.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.TiXianRecordBean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 */

public class TiXianRecordAdapter extends BaseAdapter {
    private Context context;
    private List<TiXianRecordBean.InfoBean.ListBean> datas;
    private Animation animation;
    public TiXianRecordAdapter(Context context, List<TiXianRecordBean.InfoBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
        animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
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
            convertView = View.inflate(context, R.layout.ti_xian_record_list, null);
            holder.tv_tixian_money = (TextView) convertView.findViewById(R.id.tv_tixian_money);
            holder.tv_tixian_time = (TextView) convertView.findViewById(R.id.tv_tixian_time);
            holder.tv_tixian_status = (TextView) convertView.findViewById(R.id.tv_tixian_status);
            convertView.setTag(holder);//为view设置标签
        } else {//取出holder
            holder = (ViewHolder) convertView.getTag();//the Object stored in this view as a tag
        }
        convertView.startAnimation(animation);
        holder.tv_tixian_money.setText("提现金额："+datas.get(i).getMoney()+"元");
        holder.tv_tixian_time.setText(datas.get(i).getTime());
        String pay_status = datas.get(i).getPay_status();
        if(pay_status.equals("0")) {//审核中
            holder.tv_tixian_status.setText(datas.get(i).getPay_status_name());
            holder.tv_tixian_status.setTextColor(Color.parseColor("#666666"));
        }else if(pay_status.equals("1")) {//提现成功
            holder.tv_tixian_status.setText(datas.get(i).getPay_status_name());
            holder.tv_tixian_status.setTextColor(Color.parseColor("#07AA5B"));
        }else if(pay_status.equals("2")) {//提现失败
            holder.tv_tixian_status.setText(datas.get(i).getPay_status_name());
            holder.tv_tixian_status.setTextColor(Color.parseColor("#C70202"));
        }
        return convertView;
    }
    class ViewHolder {
        TextView tv_tixian_money;
        TextView tv_tixian_time;
        TextView tv_tixian_status;
    }
}
