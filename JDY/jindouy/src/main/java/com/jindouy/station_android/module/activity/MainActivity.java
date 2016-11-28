package com.jindouy.station_android.module.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.jindouy.station_android.R;
import com.jindouy.station_android.module.fragment.BaseFragment;
import com.jindouy.station_android.module.fragment.HomeFragment;
import com.jindouy.station_android.module.fragment.KuaiJianFragment;
import com.jindouy.station_android.module.fragment.MyFragment;
import com.jindouy.station_android.module.fragment.StorehouseFragment;

import java.io.File;
import java.util.ArrayList;

/**
 *
 *@author 黄辉
 *@time 2016/11/3 15:36
 *
 * 首页
 *
*/
public class MainActivity extends FragmentActivity {
    private FrameLayout fl_main;
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> list;
    private int mPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_main = (FrameLayout)findViewById(R.id.fl_main);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new KuaiJianFragment());
        list.add(new StorehouseFragment());
        list.add(new MyFragment());
        //RadioGroup 赋值
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_move:
                        mPosition = 0;
                        break;
                    case R.id.rg_cinema:
                        mPosition = 1;
                        break;
                    case R.id.rb_find:
                        mPosition = 2;
                        break;
                    case R.id.rb_my:
                        mPosition = 3;
                        break;
                }
                initFragment();//在点击的时候就调用
            }
        });
        rg_main.check(R.id.rb_move);//默认为首页
    }
    private void initFragment() {
        //得到fragment管理
        FragmentManager fm =  getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main,list.get(mPosition));
        transaction.commit();

    }
    /** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
