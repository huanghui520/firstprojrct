package com.fajuary.xiyishop_android.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.BankCardDelBean;
import com.fajuary.xiyishop_android.module.bean.BankCardStateBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.module.view.FirstEvent2;
import com.fajuary.xiyishop_android.tools.ActivityManager;
import com.fajuary.xiyishop_android.tools.CacheUtils;
import com.fajuary.xiyishop_android.tools.XYApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;

import static com.fajuary.xiyishop_android.R.id.iv_back;
/**
 * @author 黄辉
 * @time 2016/10/13 11:46
 * <p>
 * 选择银行卡页   添加银行卡  银行卡列表
 */
public class BankCardActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mIv_back;
    private ImageView mIv_add;
    private ListView lv_list_card;
    private String token;
    private String shopId;
    private List<BankCardStateBean.DatasBean> datas;//获取到的数据
    private BankCardAdapter bankCardAdapter;
    private int temp = -1;
    private String bankId;
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        setContentView(R.layout.activity_bank_card);
        bindViews();
        initData();
    }
    private void bindViews() {
        mIv_back = (LinearLayout) findViewById(iv_back);
        mIv_add = (ImageView) findViewById(R.id.iv_add);
        lv_list_card = (ListView) findViewById(R.id.lv_list_card);
        mIv_back.setOnClickListener(this);
        mIv_add.setOnClickListener(this);
    }
    private void initData() {
        token = CacheUtils.getstr(this, "token");
        shopId = CacheUtils.getstr(this, "shopId");
        XYApi.bankcard(token, shopId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null != responseBody && responseBody.length > 0) {
                    Log.e("TAG", new String(responseBody) + "银行卡列表" + token + "llllll" + shopId);
                    //解析json
                    explainJson(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("TAG", "银行卡列表:::" + statusCode + error.toString());
            }
        });
    }
    private void explainJson(String json) {
        BankCardStateBean bankCardStateBean = JSON.parseObject(json, BankCardStateBean.class);
        datas = bankCardStateBean.getDatas();
        int size = datas.size();
        if(size<1) {
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
        bankCardAdapter = new BankCardAdapter();
        lv_list_card.setAdapter(bankCardAdapter);
        //长按 修改或者删除
        lv_list_card.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                bankId = datas.get(i).getId();
               dialog = new AlertDialog.Builder(BankCardActivity.this);
                dialog.setTitle("银行卡信息");
                dialog.setMessage(datas.get(i).getBank_account() + "-" + datas.get(i).getCard_num());
                dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("TAG", "==================================" + token + "111111" + shopId + "2222" + bankId + "33333");
                        XYApi.remove_bankcard(token, shopId, bankId, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                if (statusCode == 200 && new String(responseBody) != null) {
                                    Log.e("TAG", new String(responseBody) + "银行卡列表删除" + token + "llllll" + shopId);
                                    Log.e("TAG", "code:" + statusCode + "  result:" + new String(responseBody));
                                    BankCardDelBean bankCardDelBean = JSON.parseObject(new String(responseBody), BankCardDelBean.class);
                                    String code = bankCardDelBean.getCode() + "";
                                    if (code.equals("1")) {
                                        Toast.makeText(BankCardActivity.this, bankCardDelBean.getDatas(), Toast.LENGTH_SHORT).show();
                                        initData();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Log.e("TAG", "银行卡列表删除:::" + statusCode + error.toString());
                            }
                        });
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create();//设置点击 弹框外不会消失
                dialog.setCancelable(false);
                dialog.show();
                return true;
            }
        });
        bankCardAdapter.notifyDataSetChanged();
        lv_list_card.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EventBus.getDefault().post(new FirstEvent2(datas.get(i).getCard_num() + "|" + datas.get(i).getId() + "|" + datas.get(i).getCardholdername()));
                finish();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this, AddBankCardActivity.class);
                startActivity(intent);
                break;
        }
    }
    //适配器
    private class BankCardAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }
        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(BankCardActivity.this, R.layout.list_adapter, null);
                holder.tv_name_card = (TextView) convertView.findViewById(R.id.tv_name_card);
                holder.tv_number_card = (TextView) convertView.findViewById(R.id.tv_number_card);
                holder.item_cb_section = (CheckBox) convertView.findViewById(R.id.item_cb_section);
                convertView.setTag(holder);//为view设置标签
            } else {//取出holder
                holder = (ViewHolder) convertView.getTag();//the Object stored in this view as a tag
            }
            holder.tv_name_card.setText(datas.get(i).getBank_account());
            holder.tv_number_card.setText(datas.get(i).getCard_num());
            if (i == temp) {
                //比对position和当前的temp是否一致
                holder.item_cb_section.setChecked(true);
            } else {

                holder.item_cb_section.setChecked(false);
            }
            // 根据isSelected来设置checkbox的选中状况
            holder.item_cb_section.setId(i);//对checkbox的id进行重新设置为当前的position
            holder.item_cb_section.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                //把上次被选中的checkbox设为false
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {//实现checkbox的单选功能
//                        Toast.makeText(BankCardActivity.this, datas.get(buttonView.getId()).getCard_num()+"|"+datas.get(buttonView.getId()).getId(), Toast.LENGTH_SHORT).show();
                        //发消息
//                        EventBus.getDefault().post(new FirstEvent2(datas.get(buttonView.getId()).getCard_num() + "|" + datas.get(buttonView.getId()).getId()+"|"+datas.get(buttonView.getId()).getCardholdername()));
                        if (temp != -1) {
                            //找到上次点击的checkbox,并把它设置为false,对重新选择时可以将以前的关掉
                            CheckBox tempCheckBox = (CheckBox) BankCardActivity.this.findViewById(temp);
                            if (tempCheckBox != null && temp == i) {
                                tempCheckBox.setChecked(true);
                            } else {
                                tempCheckBox.setChecked(false);
                            }
                        }
                        temp = buttonView.getId();//保存当前选中的checkbox的id值
                    }
                }
            });
            if (i == temp) {
                //比对position和当前的temp是否一致
                holder.item_cb_section.setChecked(true);
            }
            else {
                holder.item_cb_section.setChecked(false);
            }
            return convertView;
        }
    }
    static class ViewHolder {
        TextView tv_name_card;
        TextView tv_number_card;
        CheckBox item_cb_section;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ActivityManager.getInstance().currentActivity();
    }
}
