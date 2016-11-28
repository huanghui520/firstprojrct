package com.jindouy.station_android.module.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.WarehouseActivity;


/**
 * created by huanghui at 2016/11/3
 * 仓库页
 * */

public class StorehouseFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout mIv_back;
    private TextView mEt_house_type;
    private ImageView mIv_icon_a;
    private TextView mEt_house_type_a;
    private ImageView mIv_icon_b;
    private TextView mEt_start_time;
    private ImageView mIv_icon_c;
    private TextView mEt_stop_time;
    private ImageView mIv_icon_d;
    private TextView mTv_demand;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.storehouse_mian, null);
        mIv_back = (LinearLayout) view.findViewById(R.id.iv_back);
        mEt_house_type = (TextView) view.findViewById(R.id.et_house_type);
        mIv_icon_a = (ImageView) view.findViewById(R.id.iv_icon_a);
        mEt_house_type_a = (TextView) view.findViewById(R.id.et_house_type_a);
        mIv_icon_b = (ImageView) view.findViewById(R.id.iv_icon_b);
        mEt_start_time = (TextView) view.findViewById(R.id.et_start_time);
        mIv_icon_c = (ImageView)view. findViewById(R.id.iv_icon_c);
        mEt_stop_time = (TextView)view. findViewById(R.id.et_stop_time);
        mIv_icon_d = (ImageView)view. findViewById(R.id.iv_icon_d);
        mTv_demand = (TextView) view.findViewById(R.id.tv_demand);

        mIv_back.setOnClickListener(this);
        mIv_icon_a.setOnClickListener(this);
        mTv_demand.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back ://返回

                break;
            case R.id.et_house_type://包裹类型

                break;
            case R.id.iv_icon_a://选择包裹类型
                Log.e("TAG","包裹类型");
//                //实例化MyPopepWindow
//                myBaoTypePopuWindow = new MyBaoTypePopuWindow(context, itemsOnClick);
//                //显示窗口
//                myBaoTypePopuWindow.showAtLocation(((Activity) context).findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);//设置布局  在popupwindow 中的显示的位置
                break;
            case R.id.et_house_type_a://站点

                break;
            case R.id.iv_icon_b://选择站点

                break;
            case R.id.et_start_time://起始时间

                break;
            case R.id.iv_icon_c://选择起始时间

                break;
            case R.id.et_stop_time://终止时间

                break;
            case R.id.iv_icon_d://选择终止时间

                break;
            case R.id.tv_demand://查询
                Intent intent = new Intent(context, WarehouseActivity.class);
                startActivity(intent);
                break;
        }
    }
    //为弹出窗口实现监听
    private View.OnClickListener itemsOnClick= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }
    };
}