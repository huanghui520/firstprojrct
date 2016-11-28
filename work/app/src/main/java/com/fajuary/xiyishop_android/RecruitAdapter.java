package com.fajuary.xiyishop_android;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fajuary.myapp.adapter.recycleAdapter.MeBaseAdapter;

/**
 * Created 张朋飞 on 2016/7/27.
 * user 864598192
 */
public abstract class RecruitAdapter extends MeBaseAdapter {
    public RequestManager glideRequest;

    public RecruitAdapter(Context context) {
        super(context);
        glideRequest = Glide.with(context);

    }


}
