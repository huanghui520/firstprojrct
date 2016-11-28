package com.jindouy.station_android.module.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.activity.AddResseeActivity;
import com.jindouy.station_android.module.activity.BaoDetailsActivity;
import com.jindouy.station_android.module.activity.KuaiJianDetailsActivity;
import com.jindouy.station_android.module.adapter.HomeFragmentAdapter;
import com.jindouy.station_android.module.bean.HomeFragmentBean;
import com.jindouy.station_android.module.bean.HomeQueryBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.LoadingAlertDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;


/**
 * created by huanghui at 2016/11/3
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.iv_back)
    private LinearLayout mIv_back;
    @ViewInject(R.id.tv_build)
    private TextView mTv_build;
    @ViewInject(R.id.list_myorder)//listview
    private ListView list_myorder;
    @ViewInject(R.id.refresh)
    private com.cjj.MaterialRefreshLayout refresh;
    private String type;                                                        //类型 1 代收点 2 基站 中转站
    private View headview;                                                     //头
    private EditText mEdit_content;
    private ImageView mIv_scan;
    private TextView mTv_find;
    private RelativeLayout mRl_addressee;
    private RelativeLayout mRl_getpiece;
    private TextView mTv_status;
    private String token;                                                         //token
    private ArrayList<HomeFragmentBean> homeFragmentBeen;
    private HomeFragmentAdapter homeFragmentAdapter;
    private LoadingAlertDialog loadingAlertDialog;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_mian, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        token = CacheUtils.getstr(context, "token");
        type = CacheUtils.getstr(context, "type");
        //头部
        headview = View.inflate(context, R.layout.home_fragment_my_head, null);
        mEdit_content = (EditText) headview.findViewById(R.id.edit_content);
        mIv_scan = (ImageView) headview.findViewById(R.id.iv_scan);
        mTv_find = (TextView) headview.findViewById(R.id.tv_find);
        mRl_addressee = (RelativeLayout) headview.findViewById(R.id.rl_addressee);
        mRl_getpiece = (RelativeLayout) headview.findViewById(R.id.rl_getpiece);
        mTv_status = (TextView) headview.findViewById(R.id.tv_status);
        //添加头
        list_myorder.addHeaderView(headview);
        //点击事件
        mRl_addressee.setOnClickListener(this);
        mRl_getpiece.setOnClickListener(this);
        mTv_build.setOnClickListener(this);
        mTv_find.setOnClickListener(this);

        //获取数据
        getData();
        //下拉刷新
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getData();
            }

            //加载数据
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refresh.finishRefreshLoadMore();
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        refresh.finishRefresh();
//        JDY_JZApi.
        homeFragmentBeen = new ArrayList<>();
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛1已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛2已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛3已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛4已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛5已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛6已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛7已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛8已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛9已接单"));
        homeFragmentBeen.add(new HomeFragmentBean("订单号：0010105032015151", "2016.08.08 14:00", "小葛葛11已接单"));
        //适配器
        homeFragmentAdapter = new HomeFragmentAdapter(context, homeFragmentBeen);
        list_myorder.setAdapter(homeFragmentAdapter);
        list_myorder.setDivider(null);
        homeFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_addressee://首页-收件
                Intent intent = new Intent(context, AddResseeActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_getpiece://首页-取件

                break;
            case R.id.tv_build://首页-建包

                break;
            case R.id.tv_find://首页—查询
                CacheUtils.putstr(context,"code","1");
                String ordernum = mEdit_content.getText().toString().trim();
                if (!TextUtils.isEmpty(ordernum)) {// 订单编号不为空
                    loadingAlertDialog = new LoadingAlertDialog(context);
                    loadingAlertDialog.show("正在查询");
                    JDY_JZApi.searchorder(token, ordernum, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (null != responseBody && responseBody.length > 0) {
                                Log.e("TAG", new String(responseBody) + "首页查询" + token);
                                //解析json
                                explainJson(new String(responseBody));
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("TAG", ":::首页查询" + statusCode + error.toString());
                        }
                    });
                } else {
                    Toast.makeText(context, "请输入订单编号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 查询  解析  订单查询
     *
     * @param Json
     */
    private void explainJson(String Json) {
        HomeQueryBean homeQueryBean = JSON.parseObject(Json, HomeQueryBean.class);
        HomeQueryBean.InfoBean info = homeQueryBean.getInfo();
        String str = JSON.toJSONString(info);
        int status = homeQueryBean.getStatus();
        if (status == 1) {
            loadingAlertDialog.dismiss();
            int category = homeQueryBean.getInfo().getCategory();
            if(category==1) { // 订单
                Intent intent = new Intent(context, KuaiJianDetailsActivity.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }else if(category==2) { // 包裹
                Intent intent = new Intent(context, BaoDetailsActivity.class);
                intent.putExtra("str",str);
                startActivity(intent);
            }
        } else {
            Toast.makeText(context, homeQueryBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
            loadingAlertDialog.dismiss();
        }
    }
}
