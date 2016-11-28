package com.jindouy.station_android.module.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.BankCardActivity;
import com.jindouy.station_android.module.bean.BankCardBean;

import java.util.List;

/**
 * created by huanghui at 2016/11/14
 * 银行卡列表的适配器
 */
public class BankCardAdapter extends BaseAdapter {
    private int temp = -1;
    private BankCardActivity context;
    private  List<BankCardBean.InfoBean.ListBean> datas;
    private  Animation animation;
    public BankCardAdapter(BankCardActivity context, List<BankCardBean.InfoBean.ListBean> datas) {
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.bank_card_list_adapter, null);
            holder.tv_name_card = (TextView) convertView.findViewById(R.id.tv_name_card);
            holder.tv_number_card = (TextView) convertView.findViewById(R.id.tv_number_card);
            holder.item_cb_section = (CheckBox) convertView.findViewById(R.id.item_cb_section);
            convertView.setTag(holder);//为view设置标签

        } else {//取出holder
            holder = (ViewHolder) convertView.getTag();//the Object stored in this view as a tag
        }
        convertView.startAnimation(animation);
        holder.tv_name_card.setText(datas.get(i).getBank_name());//银行名称
        holder.tv_number_card.setText(datas.get(i).getCard_num());//银行卡号
        if (i == temp) {
            //比对position和当前的temp是否一致
            holder.item_cb_section.setChecked(true);
        } else {
            holder.item_cb_section.setChecked(false);
        }
        // 根据isSelected来设置checkbox的选中状况
        holder.item_cb_section.setId(i);//对checkbox的id进行重新设置为当前的position
        holder.item_cb_section.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //把上次被选中的checkbox设为false
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//实现checkbox的单选功能
//                        Toast.makeText(BankCardActivity.this, datas.get(buttonView.getId()).getCard_num()+"|"+datas.get(buttonView.getId()).getId(), Toast.LENGTH_SHORT).show();
                    //发消息
//                        EventBus.getDefault().post(new FirstEvent2(datas.get(buttonView.getId()).getCard_num() + "|" + datas.get(buttonView.getId()).getId()+"|"+datas.get(buttonView.getId()).getCardholdername()));
                    if (temp != -1) {
                        //找到上次点击的checkbox,并把它设置为false,对重新选择时可以将以前的关掉
                        CheckBox tempCheckBox = (CheckBox)context.findViewById(temp);
                        if (tempCheckBox != null && temp == i) {
                            tempCheckBox.setChecked(true);
                        } else {
                            tempCheckBox.setChecked(false);
                        }
                    }
                    temp = buttonView.getId();//保存当前选中的checkbox的id值
                }
            }
        });
        if (i == temp) {
            //比对position和当前的temp是否一致
            holder.item_cb_section.setChecked(true);
        }
//        else if(i==0) {
//            holder.item_cb_section.setChecked(true);
//        }
        else {
            holder.item_cb_section.setChecked(false);
        }
        return convertView;
    }
    class ViewHolder {
        TextView tv_name_card;
        TextView tv_number_card;
        CheckBox item_cb_section;
    }
}


