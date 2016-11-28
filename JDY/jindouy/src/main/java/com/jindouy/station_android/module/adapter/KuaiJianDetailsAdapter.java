package com.jindouy.station_android.module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.HomeQueryKuaiJianBean;

import java.util.List;

/**
 * created by huanghui at 2016/11/17
 */

public class KuaiJianDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<HomeQueryKuaiJianBean.LogisticsBean> logistics;
    public KuaiJianDetailsAdapter(Context context, List<HomeQueryKuaiJianBean.LogisticsBean> logistics) {
        this.context = context;
        this.logistics = logistics;
    }

    @Override
    public int getCount() {
        return logistics.size();
    }

    @Override
    public Object getItem(int i) {
        return logistics.get(i);
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
            convertView = View.inflate(context, R.layout.bao_details_list, null);//加载布局
            viewholder.mTv_bao_time = (TextView) convertView.findViewById(R.id.tv_bao_time);//绑定id
            viewholder.mTv_bao_date = (TextView) convertView.findViewById(R.id.tv_bao_date);
            viewholder.mTv_bao_content = (TextView) convertView.findViewById(R.id.tv_bao_content);
            convertView.setTag(viewholder);//设置tag
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.mTv_bao_time.setText(logistics.get(i).getTime_num());
        viewholder.mTv_bao_date.setText(logistics.get(i).getDate_num());
        viewholder.mTv_bao_content.setText(logistics.get(i).getInfo());
        return convertView;
    }
    static class ViewHolder {
        TextView mTv_bao_time;
        TextView mTv_bao_date;
        TextView mTv_bao_content;
    }
}
