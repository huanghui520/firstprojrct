package com.fajuary.xiyishop_android.module.activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fajuary.xiyishop_android.R;
import com.fajuary.xiyishop_android.module.bean.ValidationRecordBean;
import com.fajuary.xiyishop_android.module.view.BaseActivity;
import com.fajuary.xiyishop_android.tools.DateUtils;

import java.util.ArrayList;

import cz.msebera.android.httpclient.util.TextUtils;
/**
 * @author 黄辉
 * @time 2016/10/30 11:32
 * 验证记录
 */
public class ValidationRecordActivity extends BaseActivity implements View.OnClickListener {
private TextView tv_tishi;
    private String excisetime;//时间
    private LinearLayout iv_back;
    private ListView lv_list_yanz;
    private ArrayList<ValidationRecordBean> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_record);
        excisetime = getIntent().getStringExtra("excisetime");
////        String[] aa ="aaa*bbb*ccc".split("*");
////        　　//String[] aa = "aaa|bbb|ccc".split("\\*"); 这样才能得到正确的结果
////        　　for(int i =0; i <aa.length ; i++){
////            　　System.out.println("--"+aa);
//        String[] split = excisetime.split(",");
//        List list=new ArrayList();
        Log.e("TAG", excisetime + "====================================");
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        tv_tishi = (TextView) findViewById(R.id.tv_tishi);
        lv_list_yanz = (ListView) findViewById(R.id.lv_list_yanz);
        iv_back.setOnClickListener(this);
        datas = new ArrayList<>();
        String[] split = excisetime.split(",");
        Log.e("TAG", split.length + "--------------------------------------");
//        Log.e("TAG", split[0] + "--------------------------------------");
//        Log.e("TAG", split[1] + "-------------------+++++++-------------------");
//        Log.e("TAG", split[2] + "--------------------------------------+++++++");
        for (int i = 0; i < split.length;i++) {
            String s = split[i];
            if (!TextUtils.isEmpty(s)) {
                if(s.length()<15) {
                    datas.add(new ValidationRecordBean(i+"",s));
                }
            }
        }
        int size = datas.size();
        if(size<1) {
            tv_tishi.setVisibility(View.VISIBLE);
            lv_list_yanz.setVisibility(View.GONE);
        }
        lv_list_yanz.setAdapter(new ValidationRecordAdapter());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private class ValidationRecordAdapter extends BaseAdapter {
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
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(ValidationRecordActivity.this, R.layout.list_item, null);//加载布局
                viewHolder.tv_name_yanz = (TextView) convertView.findViewById(R.id.tv_name_yanz);//绑定id
                convertView.setTag(viewHolder);//设置tag
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ValidationRecordBean validationRecordBean = datas.get(i);
            viewHolder.tv_name_yanz.setText("第"+validationRecordBean.getNumber()+"次"+"验证："+ DateUtils.times(validationRecordBean.getTime()));
            return convertView;
        }
    }
    static class ViewHolder {
        TextView tv_name_yanz;

    }
}
