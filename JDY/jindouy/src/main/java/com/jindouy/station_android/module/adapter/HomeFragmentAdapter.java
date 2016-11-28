package com.jindouy.station_android.module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.bean.HomeFragmentBean;

import java.util.ArrayList;

/**
 * created by huanghui at 2016/11/16
 */
public class HomeFragmentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HomeFragmentBean> homeFragmentBeen;
    private Animation animation;
    public HomeFragmentAdapter(Context context, ArrayList<HomeFragmentBean> homeFragmentBeen) {
        this.context = context;
        this.homeFragmentBeen = homeFragmentBeen;
        animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
    }
    @Override
    public int getCount() {
        return homeFragmentBeen.size();
    }
    @Override
    public Object getItem(int i) {
        return homeFragmentBeen.get(i);
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
            convertView = View.inflate(context, R.layout.home_fragment_list, null);//加载布局
            viewholder.mIv_home_icon = (ImageView) convertView.findViewById(R.id.iv_home_icon);//绑定id
            viewholder.mTv_home_order_number = (TextView) convertView.findViewById(R.id.tv_home_order_number);
            viewholder.mTv_home_ststus = (TextView) convertView.findViewById(R.id.tv_home_ststus);
            viewholder.mTv_home_time = (TextView) convertView.findViewById(R.id.tv_home_time);
            convertView.setTag(viewholder);//设置tag
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(viewholder);//设置tag
        convertView.startAnimation(animation);
        viewholder.mTv_home_order_number.setText(homeFragmentBeen.get(i).getOrder_number());
        viewholder.mTv_home_ststus.setText(homeFragmentBeen.get(i).getStstus());
        viewholder.mTv_home_time.setText(homeFragmentBeen.get(i).getTime());
        return convertView;
    }
    static class ViewHolder {
        ImageView mIv_home_icon;
        TextView mTv_home_order_number;
        TextView mTv_home_ststus;
        TextView mTv_home_time;
    }
}
