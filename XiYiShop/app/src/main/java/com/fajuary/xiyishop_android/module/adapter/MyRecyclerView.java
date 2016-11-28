package com.fajuary.xiyishop_android.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.ShangPinBean;

import java.util.List;

/**
 * created by huanghui at 2016/10/13
 */

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.ViewHolder>  {
    private List<ShangPinBean.DatasBean> datas;
    private Context context;

    public MyRecyclerView(Context context,List<ShangPinBean.DatasBean> datas) {
        this.datas = datas;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextPaint paint = holder.name.getPaint();
        paint.setFakeBoldText(true);//加粗
        holder.name.setText(datas.get(position).getGoodsName());
        holder.price.setText("￥"+datas.get(position).getPrice());
        Glide.with(context).load(datas.get(position).getThumbnail()).into(holder.img);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //    自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView img;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.recycleview_goodsview_storeName);
            price = (TextView) view.findViewById(R.id.recycleview_goodsview_storePrice);
            img = (ImageView) view.findViewById(R.id.recycleview_goodsview_storeImg);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //加一个判断
                    if(mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view,getLayoutPosition(),String.valueOf(datas.get(getLayoutPosition())));//数据转换
                    }
                }
            });

        }
    }
    //点击回调接口
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int postion, String data);
    }

    /**
     * 设置点击某一条item的监听
     * @param mOnItemClickListener
     */
    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener;
}
