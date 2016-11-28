package com.fajuary.xiyishop_android.mymodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class GoodsViewAdapter extends RecruitAdapter {
    public GoodsViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void valueView(BaseRecyclerViewHolder baseRecyclerViewHolder, final int position) {
        if ( baseRecyclerViewHolder instanceof GoodsViewViewHolder ) {
            GoodsViewViewHolder vhoder = (GoodsViewViewHolder) baseRecyclerViewHolder;
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
        View view = mInflater.from(mContext).inflate(R.layout.recycleview_goodsview_item, viewGroup,false);
        BaseRecyclerViewHolder viewHolder = new GoodsViewViewHolder(view);
        return viewHolder;
    }
    static class GoodsViewViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.recycleview_goodsview_layout)
        LinearLayout layout;
        @Bind(R.id.recycleview_goodsview_storeName)
        TextView storeName;
        @Bind(R.id.recycleview_goodsview_storePrice)
        TextView storePrice;
        @Bind(R.id.recycleview_goodsview_storeImg)
        ImageView storeImg;

        GoodsViewViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
