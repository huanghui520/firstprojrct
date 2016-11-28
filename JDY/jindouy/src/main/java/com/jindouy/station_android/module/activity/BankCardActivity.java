package com.jindouy.station_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jindouy.station_android.R;
import com.jindouy.station_android.module.adapter.BankCardAdapter;
import com.jindouy.station_android.module.bean.BankCardBean;
import com.jindouy.station_android.module.bean.DelBankCardBean;
import com.jindouy.station_android.tools.CacheUtils;
import com.jindouy.station_android.tools.JDY_JZApi;
import com.jindouy.station_android.view.FirstEvent2;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;

/**
 *
 *@author 黄辉
 *@time 2016/11/9 15:20
 *
 * 银行卡列表
 *
*/
public class BankCardActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mActivity_bank_card;
    private LinearLayout mIv_back;
    private TextView mIv_add;
    private com.cjj.MaterialRefreshLayout refresh;
    private ListView mLv_list_card;
    private int page = 1;                                           //分码
    private String token;                                           //
    private AlertDialog.Builder dialog;                             //提示框
    private List<BankCardBean.InfoBean.ListBean> datas;             //数据
    private BankCardAdapter bankCardAdapter;                        //适配器
    private String card_id;                                         //点击item 银行卡的id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        bindViews();

        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        token = CacheUtils.getstr(this, "token");
        JDY_JZApi.bankcardlist(token, page, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG",new String(responseBody)+"银行卡列表获取成功");
                    //解析json
                    explainJson(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG",statusCode+error.toString()+"银行卡列表获取失败");
            }
        });
    }
    /**
     * 解析Json
     * @param Json
     */
    private void explainJson(String Json) {
        BankCardBean bankCardBean = JSON.parseObject(Json, BankCardBean.class);
        String status = bankCardBean.getStatus();
        if(status.equals("0")) {
            
        }else if(status.equals("1")) {//成功
            int count = bankCardBean.getInfo().getCount();
            if(count<1) {
                /**
                 * 弹出提示框
                 */
                dialog = new AlertDialog.Builder(BankCardActivity.this);
                dialog.setMessage("请添加银行卡");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(BankCardActivity.this, AddBankCardActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.create();
                dialog.setCancelable(false);
                dialog.show();
            }
            datas = bankCardBean.getInfo().getList();
            bankCardAdapter = new BankCardAdapter(BankCardActivity.this,datas);
            //设置适配器
            mLv_list_card.setAdapter(bankCardAdapter);
            //适配器刷新
            bankCardAdapter.notifyDataSetChanged();
            //点击
            mLv_list_card.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    EventBus.getDefault().post(new FirstEvent2(datas.get(i).getCard_num() + "|" + datas.get(i).getCard_id() + "|" + datas.get(i).getBank_name()));
                    finish();
                }
            });
            //长按
            mLv_list_card.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    card_id = datas.get(i).getCard_id();
                    dialog = new AlertDialog.Builder(BankCardActivity.this);
                    dialog.setTitle("银行卡信息");
                    dialog.setMessage(datas.get(i).getBank_name() + "-" + datas.get(i).getCard_num());
                    dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            JDY_JZApi.delbankcard(token, card_id, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    if (null != responseBody && responseBody.length > 0) {
                                        Log.e("TAG",new String(responseBody)+"删除银行卡成功");
                                        //解析json
                                        explainJsondel(new String(responseBody));
                                    }
                                }
                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Log.e("TAG",statusCode+error.toString()+"删除银行卡失败");
                                }
                            });
                        }
                    });
                    dialog.setNegativeButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.create();//设置点击 弹框外不会消失
                    dialog.setCancelable(false);
                    dialog.show();
                    return true;//点击不会消失
                }
            });
        }

    }

    /**
     * 删除银行卡
     * @param Json
     */
    private void explainJsondel(String Json) {
        DelBankCardBean delBankCardBean = JSON.parseObject(Json, DelBankCardBean.class);
        Toast.makeText(BankCardActivity.this, delBankCardBean.getInfo().getMsg(), Toast.LENGTH_SHORT).show();
        //删除之后   从新获取下数据
        initData();
    }
    /**
     * 初始化
     */
    private void bindViews() {
        refresh = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mActivity_bank_card = (LinearLayout) findViewById(R.id.activity_bank_card);
        mIv_back = (LinearLayout) findViewById(R.id.iv_back);
        mIv_add = (TextView) findViewById(R.id.tv_add);
        mLv_list_card = (ListView) findViewById(R.id.lv_list_card);
        //点击
        mIv_back.setOnClickListener(this);
        mIv_add.setOnClickListener(this);
//        AnimationSet set = new AnimationSet(false);
//        Animation animation = new AlphaAnimation(0,1);   //AlphaAnimation 控制渐变透明的动画效果
//        animation.setDuration(500);     //动画时间毫秒数
//        set.addAnimation(animation);    //加入动画集合
//
//        animation = new TranslateAnimation(50, 50, 50, 50);  //ScaleAnimation 控制尺寸伸缩的动画效果
//        animation.setDuration(300);
//        set.addAnimation(animation);
//
//        animation = new RotateAnimation(30,10);    //TranslateAnimation  控制画面平移的动画效果
//        animation.setDuration(300);
//        set.addAnimation(animation);
//
//        animation = new ScaleAnimation(5,0,2,0);    //RotateAnimation  控制画面角度变化的动画效果
//        animation.setDuration(300);
//        set.addAnimation(animation);
//
//        LayoutAnimationController controller = new LayoutAnimationController(set, 1);
//        mLv_list_card.setLayoutAnimation(controller);   //ListView 设置动画效果
        /**
         * 刷新监听
         */
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refresh.finishRefresh();
            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refresh.finishRefreshLoadMore();
            }
            @Override
            public void onfinish() {
                super.onfinish();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back ://返回
                finish();
                break;
            case R.id.tv_add://添加银行卡
                Intent intent = new Intent(this, AddBankCardActivity.class);
                startActivity(intent);
                break;
        }
    }
}