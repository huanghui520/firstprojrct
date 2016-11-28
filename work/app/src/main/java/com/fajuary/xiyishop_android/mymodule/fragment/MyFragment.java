package com.fajuary.xiyishop_android.mymodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.fajuary.xiyishop_android.BaseFragment;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.mymodule.activity.GoodsViewActivity;
import com.fajuary.xiyishop_android.mymodule.activity.InitiatePaymentActivity;
import com.fajuary.xiyishop_android.mymodule.activity.OrderManagerActivity;
import com.fajuary.xiyishop_android.mymodule.activity.SetUptheActivity;
import com.fajuary.xiyishop_android.mymodule.activity.VerifyOrderActivity;
import com.fajuary.xiyishop_android.mymodule.activity.WithdrawDepositActivity;
import com.fajuary.xiyishop_android.mymodule.adapter.OrderStatesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaseFragment {



    @Bind(R.id.fragment_head_leftlayout)
    LinearLayout leftlayout;


    @Bind(R.id.fragment_myorder_recycleview)
    RecyclerView recyclerView;

    @Bind(R.id.fragment_myorder_orderManagerLin)
    LinearLayout orderManagerLin;

    @Bind(R.id.fragment_myorder_withdrawalLin)
    LinearLayout withdrawalLin;

    @Bind(R.id.fragment_myorder_verifyorderLin)
    LinearLayout verifyorderLin;

    @Bind(R.id.fragment_myorder_paymentLin)
    LinearLayout paymentLin;

    @Bind(R.id.fragment_myorder_seestoreLin)
    LinearLayout seestoreLin;
    
    @Bind(R.id.fragment_myorder_setLin)
    LinearLayout setLin;




    private LinearLayoutManager verlinelayoutManager;
    private OrderStatesAdapter statesAdapter;

    private List<String> allOrders;

    @Override
    public void initEvent() {
        leftlayout.setVisibility(View.INVISIBLE);

        orderManagerLin.setOnClickListener(this);
        withdrawalLin.setOnClickListener(this);
        paymentLin.setOnClickListener(this);
        verifyorderLin.setOnClickListener(this);
        seestoreLin.setOnClickListener(this);
        setLin.setOnClickListener(this);
    }

    @Override
    protected void createView(View view, Bundle bundle) {
        ButterKnife.bind(this,view);
        recyclerView.setFocusable(false);//无法获取焦点

        //recyclerview管理者
        verlinelayoutManager=new LinearLayoutManager(mActivity);
        verlinelayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(verlinelayoutManager);
        statesAdapter=new OrderStatesAdapter(mActivity);
        recyclerView.setAdapter(statesAdapter);

        initDatas();




    }

    private void initDatas(){
        allOrders=new ArrayList<>();
        for ( int i=0;i<15;i++ ){
            allOrders.add("第"+i+"项");
        }
        statesAdapter.setData(allOrders);

    }


    /**
     * 注入到fragment
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *               The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch ( v.getId() ){
            case R.id.fragment_myorder_orderManagerLin://订单管理
                intent.setClass(mActivity, OrderManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_myorder_withdrawalLin://提现
                intent.setClass(mActivity, WithdrawDepositActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_myorder_verifyorderLin://验证订单
                intent.setClass(mActivity, VerifyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_myorder_paymentLin://发起支付
                intent.setClass(mActivity, InitiatePaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_myorder_seestoreLin://查看商品
                intent.setClass(mActivity, GoodsViewActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_myorder_setLin://设置
                intent.setClass(mActivity, SetUptheActivity.class);
                startActivity(intent);
                break;
        }
    }


    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("my", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null ) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    /**
     *
     *@author 黄辉
     *@time 2016/10/10 18:14
     * 
     * 
    */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
