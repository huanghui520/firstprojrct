package com.fajuary.xiyishop_android.mymodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fajuary.myapp.adapter.lstAdapter.LstAdapter;
import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerViewHolder;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.RecruitAdapter;
import com.fajuary.xiyishop_android.utils.SmallFeatureUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created 张朋飞 on 2016/9/19.
 * user 864598192
 */
public class OrderStatesAdapter extends RecruitAdapter{

    public OrderStatesAdapter(Context context) {
        super(context);
    }

    @Override
    public void valueView(BaseRecyclerViewHolder baseRecyclerViewHolder, final int position) {
        if ( baseRecyclerViewHolder instanceof OrderStatesViewHolder ) {
            OrderStatesViewHolder vhoder = (OrderStatesViewHolder) baseRecyclerViewHolder;
//            final LifeTopBean lifeTopBean= (LifeTopBean) mDatas.get(position);


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
        View view = mInflater.from(mContext).inflate(R.layout.recycleview_orderstates_itemlayout, viewGroup,false);
        BaseRecyclerViewHolder viewHolder = new OrderStatesViewHolder(view);
        return viewHolder;
    }
    static class OrderStatesViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.recycleview_orderstatesitem_layout)
        LinearLayout layout;
//
//        @Bind(R.id.recycleview_myreservice_servcitemName)
//        TextView servcitemName;
//        @Bind(R.id.recycleview_myreservice_servcitemImg)
//        ImageView servcitemImg;

        OrderStatesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
