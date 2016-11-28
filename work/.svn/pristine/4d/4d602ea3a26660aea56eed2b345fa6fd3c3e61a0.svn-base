package com.fajuary.xiyishop_android.mymodule.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fajuary.myapp.adapter.recycleAdapter.BaseRecyclerAdapter;
import com.fajuary.xiyishop_android.BaseActivity;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.adapter.GoodsViewAdapter;
import com.fajuary.xiyishop_android.mymodule.adapter.OrderRecordAdapter;
import com.fajuary.xiyishop_android.mymodule.bean.LifeTopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商品查看
 */
public class GoodsViewActivity extends BaseActivity
              implements SwipeRefreshLayout.OnRefreshListener,BaseRecyclerAdapter.OnItemClickListener{


    @Bind(R.id.fragment_home_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.fragment_home_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private GridLayoutManager gridLayoutManager;

    private List<LifeTopBean> allordersLst;

    private GoodsViewAdapter goodsViewAdapter;
    @Override
    public void createLayout() {
        setContentView(R.layout.activity_goods_view);
        ButterKnife.bind(this);
        // 设置进度动画的颜色,可以使用多种颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 刷新监听事件，必须有
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // 刷新监听事件，必须有
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        goodsViewAdapter=new GoodsViewAdapter(this);
        recyclerView.setAdapter(goodsViewAdapter);
    }

    @Override
    public void initEvent() {
        initData();
        goodsViewAdapter.setOnItemClickListener(this);
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
        goodsViewAdapter.setData(allordersLst);

    }
    @Override
    public void widgetClick(View view) {

    }

    @Override
    public boolean getIsFastClick() {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        Intent intent=new Intent();
        intent.setClass(this, OrderDetailsActivity.class);
        startActivity(intent);
    }
}
