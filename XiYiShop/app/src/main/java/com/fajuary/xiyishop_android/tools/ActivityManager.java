package com.fajuary.xiyishop_android.tools;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 *
 *@author 黄辉
 *@time 2016/10/23 23:32
 *
 * activity管理类
 *
*/
public class ActivityManager {
    /**
     * 保存所有Activity
     */
    private volatile Stack<Activity> activityStack = new Stack<Activity>();

    private static volatile ActivityManager instance;

    private ActivityManager()
    {
    }

    /**
     * 创建单例类，提供静态方法调用
     *
     * @return ActivityManager
     */
    public static ActivityManager getInstance()
    {
        if (instance == null)
        {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 退出Activity
     *
     * @param activity Activity
     */
    public void popActivity(Activity activity)
    {
        if (activity != null)
        {
            Log.e("TAG", "popActivity: " + activity.getLocalClassName());
            activityStack.remove(activity);
        }
    }

    /**
     * 获得当前栈顶的Activity
     *
     * @return Activity Activity
     */
    public Activity currentActivity()
    {
        Activity activity = null;
        if (!activityStack.empty())
        {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity Activity
     */
    public void pushActivity(Activity activity)
    {
        Log.e("TAG", "pushActivity: " + activity.getLocalClassName());
        activityStack.add(activity);
    }

    /**
     * 退出栈中其他所有Activity
     *
     * @param cls Class 类名
     */
    @SuppressWarnings("rawtypes")
    public void popOtherActivity(Class cls)
    {
        if(null == cls)
        {
            Log.e("TAG", "cls is null");
            return;
        }

        for(Activity activity : activityStack)
        {
            if (null == activity || activity.getClass().equals(cls))
            {
                continue;
            }

            activity.finish();
        }
        Log.e("TAG", "activity num is : " + activityStack.size());
    }

    /**
     * 退出栈中所有Activity
     *
     */
    public void popAllActivity()
    {
        while (true)
        {
            Activity activity = currentActivity();
            if (activity == null)
            {
                break;
            }
            activity.finish();
            popActivity(activity);
    }
        Log.e("TAG", "activity num is : " + activityStack.size());
    }

    /**
     * 关闭当前Activity栈中.第二个activtiy
     */
    public void popSecond(){
        Log.e("TAG","::"+activityStack.size());
        Activity activity = activityStack.get(activityStack.size() - 2);
        if (activity != null){
            activity.finish();
            popActivity(activity);
        }
    }
   /* public void startNextActivity(Class<?> activity)
    {
        Activity curActivity = currentActivity();
        Intent intent = new Intent(curActivity, activity);
        curActivity.startActivity(intent);
        curActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }*/
}
