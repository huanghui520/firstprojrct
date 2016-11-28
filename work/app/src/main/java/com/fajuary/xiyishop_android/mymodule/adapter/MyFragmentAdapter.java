package com.fajuary.xiyishop_android.mymodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.fajuary.xiyishop_android.BaseFragment;

import java.util.ArrayList;



public class MyFragmentAdapter extends FragmentStatePagerAdapter {


    private ArrayList<BaseFragment> framents;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> framents) {
        this(fm);
        mFragmentManager = fm;
        this.framents = framents;
    }

    public Fragment getItem(int arg0) {
        return framents.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return framents.size();
    }


}
