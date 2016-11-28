package com.fajuary.xiyishop_android.mymodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerViewHolder;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.RecruitAdapter;
import com.fajuary.xiyishop_android.mymodule.bean.LifeTopBean;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created 张朋飞 on 2016/8/29.
 * user 864598192
 */
public class OrderRecordAdapter extends RecruitAdapter {
    public OrderRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public void valueView(BaseRecyclerViewHolder baseRecyclerViewHolder, final int position) {
        if ( baseRecyclerViewHolder instanceof OrderRecordViewHolder ) {
            OrderRecordViewHolder vhoder = (OrderRecordViewHolder) baseRecyclerViewHolder;
            final LifeTopBean lifeTopBean= (LifeTopBean) mDatas.get(position);

            vhoder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( mOnItemClickListener!=null ){
                        mOnItemClickListener.onItemClick(v,position,"");
                    }
                }
            });

        }
    }

    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup viewGroup, int position) {
        View view = mInflater.from(mContext).inflate(R.layout.recycleview_orderrecordlayout_item, null);
        BaseRecyclerViewHolder viewHolder = new OrderRecordViewHolder(view);
        return viewHolder;
    }
    static class OrderRecordViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.recycleview_orderrecord_layout)
        RelativeLayout layout;
        @Bind(R.id.recycleview_orderrecord_orderName)
        TextView orderName;
        @Bind(R.id.recycleview_orderrecord_orderPckg)
        TextView orderPckg;
        @Bind(R.id.recycleview_orderrecord_orderCrtTm)
        TextView orderCrtTm;
        @Bind(R.id.recycleview_orderrecord_orderPrice)
        TextView orderPrice;
        @Bind(R.id.recycleview_orderrecord_orderStates)
        TextView orderStates;
        @Bind(R.id.recycleview_orderrecord_orderImg)
        ImageView orderImg;

        OrderRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
