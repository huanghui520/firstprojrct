package com.jindouy.station_android.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 *@author 黄辉
 *@time 2016/10/19 16:13
 *
 * 作用：sp缓存工具类
*/
public class CacheUtils {
    /**
     * 保持软件的参数
     * @param context
     * @param key
     * @param values
     */
    public static void putBoolen(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences("frbode",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,values).commit();
    }

    /**
     * 得到软件保存的参数
     * @param context
     * @param key
     * @return
     */
    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("frbode",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    /**
     * 保存
     * @param context
     * @param key
     * @param values
     */
    public static void putstr(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences("jdy_jz",Context.MODE_PRIVATE);
        sp.edit().putString(key,values).commit();
    }


    /**
     * 获取
     * @param context
     * @param key
     * @return
     */
    public static String getstr(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("jdy_jz",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void putString(Context context, String key, String values) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否存在

            //http://lbsyun.baidu.com/static/img/imgeditor/logo.gif--MD5--lsklkslkllklkskllkslkkls
            //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
            try {
                String fileName = MD5Encoder.encode(key);
                ////mnt/sdcard/
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dongwei";

                // //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
                File file = new File(path, fileName);

                File parentfile = file.getParentFile();//获得文件
                if (!parentfile.exists()) {//不存在
                    parentfile.mkdirs();//创建
                }

                if (!file.exists()) {//不存在
                    file.createNewFile();//床建
                }

                FileOutputStream fos = new FileOutputStream(file);

                fos.write(values.getBytes());//写入到sd卡。
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            SharedPreferences sp = context.getSharedPreferences("xiyi",Context.MODE_PRIVATE);
            sp.edit().putString(key,values).commit();
        }

    }

    public static String getString(Context context, String key) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//是否存在
            //http://lbsyun.baidu.com/static/img/imgeditor/logo.gif--MD5--lsklkslkllklkskllkslkkls
            //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
            String s = "";
            try {
                String fileName = MD5Encoder.encode(key);
                ////mnt/sdcard/
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dongwei";

                // //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
                File file = new File(path, fileName);

                if(file.exists()) {
                 //读取文件
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int length = -1;
                    byte[] bytes = new byte[1024];
                    while ((length = fis.read(bytes))!=-1){
                        bos.write(bytes,0,length);

                    }
                    s = bos.toString();

                    return s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            SharedPreferences sp = context.getSharedPreferences("xiyi",Context.MODE_PRIVATE);
            return sp.getString(key,"");
        }
        return "";
    }
////    1、创建SharedPreferences对象：
//
//    String spName = "SharedPreferences";
//
//    SharedPreferences dataBase;
//    dataBase = getSharedPreferences(spName, 0);
//
//
////    2、创建保存方法：
//
//    public void saveData() {
//        getSharedPreferences(spName, 0).edit().putString("NAME", userName.getText().toString())
//                .putString("PASS", password.getText().toString())
//                .commit();
//    }

//    3、创建删除方法：
    public static void clearData(Context context) {
//        context.getSharedPreferences("xiyi",Context.MODE_PRIVATE).edit().clear().commit();
        SharedPreferences sp = context.getSharedPreferences("xiyi",Context.MODE_PRIVATE);
        sp.edit().remove("token").commit();
    }
//    public static void putString(Context context, String key, String values) {
//            SharedPreferences sp = context.getSharedPreferences("xiyi",Context.MODE_PRIVATE);
//            sp.edit().putString(key,values).commit();
//
//
//    }
//
//    public static String getString(Context context, String key) {
//            SharedPreferences sp = context.getSharedPreferences("xiyi",Context.MODE_PRIVATE);
//            return sp.getString(key,"");
//
//    }

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

