package com.fajuary.xiyishop_android.mymodule.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerAdapter;
import com.fajuary.xiyishop_android.BaseFragment;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.activity.OrderDetailsActivity;
import com.fajuary.xiyishop_android.mymodule.adapter.OrderRecordAdapter;
import com.fajuary.xiyishop_android.mymodule.bean.LifeTopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderRecordFragment extends BaseFragment
              implements SwipeRefreshLayout.OnRefreshListener,
              BaseRecyclerAdapter.OnItemClickListener  {


    @Bind(R.id.fragment_home_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.fragment_home_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager verlayoutManager;

    private OrderRecordAdapter orderRecordAdapter;
    private List<LifeTopBean> allordersLst;

    @Override
    protected void createView(View view, Bundle bundle) {
        ButterKnife.bind(this,view);
        // 设置进度动画的颜色,可以使用多种颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 刷新监听事件，必须有
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // 刷新监听事件，必须有
        verlayoutManager = new LinearLayoutManager(mActivity);
        verlayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(verlayoutManager);
        orderRecordAdapter=new OrderRecordAdapter(mActivity);
        recyclerView.setAdapter(orderRecordAdapter);

    }
    @Override
    public void initEvent() {
        initData();
        orderRecordAdapter.setOnItemClickListener(this);
    }

    private void initData(){
        mSwipeRefreshLayout.setRefreshing(false);
        allordersLst=new ArrayList<>();
        for ( int i=0;i<9;i++ ){
            LifeTopBean lifeTopBean=new LifeTopBean();
            lifeTopBean.setStates("0");
            lifeTopBean.setName("第"+i+"项目");
            allordersLst.add(lifeTopBean);
        }
        orderRecordAdapter.setData(allordersLst);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *               The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
    public static MyOrderRecordFragment newInstance(String param1) {
        MyOrderRecordFragment fragment = new MyOrderRecordFragment();
        Bundle args = new Bundle();
        args.putString("", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order_record;
    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        Intent intent=new Intent();
        intent.setClass(mActivity, OrderDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        initData();

    }
}
