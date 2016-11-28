package com.fajuary.xiyishop_android.mymodule.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerViewHolder;
import com.fajuary.myapp.adapter.recycleAdapter.MeBaseAdapter;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.bean.PackAgeInfo;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created 张朋飞 on 2016/8/29.
 * user 864598192
 */
public class SelectCommAttrAdapter extends MeBaseAdapter{
    public SelectCommAttrAdapter(Context context) {
        super(context);
    }

    @Override
    public void valueView(BaseRecyclerViewHolder baseRecyclerViewHolder, final int position) {
        if ( baseRecyclerViewHolder instanceof HomeViewHolder ) {
            HomeViewHolder vhoder = (HomeViewHolder) baseRecyclerViewHolder;
//            String time = (String) mDatas.get(position);
            PackAgeInfo packAgeInfo= (PackAgeInfo) mDatas.get(position);
            if ( packAgeInfo!=null ){
                String name=packAgeInfo.getName();
                String states=packAgeInfo.getStates();
                switch ( states ){
                    case "0":
                        isSelect=true;
                        break;
                    case "1":
                        isSelect=false;
                        break;
                }

                vhoder.pckageName.setText(name);
                vhoder.pckageName.setSelected(isSelect);

                if (! TextUtils.isEmpty(selectName) ){
                    if ( selectName.equals(name) ){
                        vhoder.pckageName.setSelected(isSelect);
                    }
                }

                final boolean selected=vhoder.pckageName.isSelected();

                vhoder.pckageName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( mOnItemClickListener != null ) {
                            mOnItemClickListener.onItemClick(v,position,selected);
                        }
                    }
                });
            }

        }
    }
    /**
     * 单条更新选中状态
     */
    private String selectName;
    private boolean isSelect;
    public void setSelect(String selectName,boolean isSelect){
        this.selectName=selectName;
        this.isSelect=isSelect;
        notifyDataSetChanged();
    }
    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup viewGroup, int position) {
        View view = mInflater.from(mContext).inflate(R.layout.recycleview_commattr_item, null);
        BaseRecyclerViewHolder viewHolder = new HomeViewHolder(view);
        return viewHolder;
    }
    static class HomeViewHolder extends BaseRecyclerViewHolder {
        @Bind(R.id.recycleview_commattr_pckageName)
        TextView pckageName;

//        @Bind(R.id.recycleview_homeitem_imgicon)
//        ImageView imgicon;


        HomeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
