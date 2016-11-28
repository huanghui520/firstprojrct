package com.fajuary.xiyishop_android.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by ${黄辉} on 2016/5/31.
 * 检测工具类:
 *  号码是否正确
 */
public class DetectionTools {
    /**
     * 检测手机号码是否正确
     * @param phone
     * @return
     */
    public static boolean isMobileNum(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        System.out.println(m.matches() + "---");
        return m.matches();

    }

    /**
     * 检测密码是否符合要求
     * @param password
     * @return
     */
    public static boolean isPasswordCorrect(String password) {
        int length = password.length();
        if (length < 6 || length > 12){
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z0-9]+");
        Matcher m = p.matcher(password);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    /**
     * 检测图形验证码是否符合要求
     * @param code
     * @return
     */
    public static boolean isGraphCodeCorrect(String code) {
        int length = code.length();
        if (length != 4){
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z0-9]+");
        Matcher m = p.matcher(code);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersionName(Context context)
    {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("TAG", "Exception:"+ e);
        }
        Log.e("TAG", "versionName:"+ versionName);
        return versionName;
    }

//    /**
//     * 获取sign
//     * @return 当前应用的版本号
//     */
//    public static String getSign(Context context)
//    {
//        UserInfo userInfo = SaveUserInfo.getInstances(context).getUserInfo();
//        String str =  "token=" + userInfo.getToken() + "&userid=" + userInfo.getUserId() + "&iamlink";
//        String sign = Md5Util.getMD5Str(str.toString().toLowerCase()).toLowerCase();
//
//        return sign;
//    }

    /**
     * 将毫秒转化为日期. 固定格式
     * @param pattern       //格式
     * @param dateTime      //毫秒
     * @return
     */
    public static String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    /**
     * 获取当前系统时间的格式. 即12小时制或者是24小时制
     * 24小时制返回true
     * @param context
     * @return
     */
  /*  public static boolean getTimeFormat(Context context){
        ContentResolver cv = context.getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString(cv,android.provider.Settings.System.TIME_12_24);
        if(strTimeFormat.equals("24"))
        {
           return true;
        }else {
            return false;
        }
    }*/

    /**
     * 获取系统当前时间的时
     * @return
     */
    public static int getSystemTimeHour(){
        Calendar c = Calendar.getInstance();
       return c.get(Calendar.HOUR_OF_DAY);
    }
    /**
     * 获取系统当前时间的分
     * @return
     */
    public static int getSystemTimeMinuie(){
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.MINUTE);
    }
// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
// 清除掉所有特殊字符
    /**
     * 过虑特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean StringFilter(String str){

        String regEx="[^~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.matches() + "---");
        return m.matches();
      /*  Matcher m = p.matcher(str);
        return m.replaceAll("").trim();*/
    }

//    /**
//     * 判断是否登录
//     * @param context
//     * @return
//     */
//    public static boolean isLogin(Context context){
//        UserInfo userInfo = SaveUserInfo.getInstances(context).getUserInfo();
//        if (null != userInfo){
//            if (null == userInfo.getUserId() || "".equals(userInfo.getUserId()) || null == userInfo.getToken() || "".equals(userInfo.getToken())){
//                return false;
//            }else {
//                return true;
//            }
//        }else{
//            return false;
//        }
//    }

}
