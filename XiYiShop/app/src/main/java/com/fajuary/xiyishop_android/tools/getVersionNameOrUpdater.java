package com.fajuary.xiyishop_android.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */

public class getVersionNameOrUpdater {
    /**
     * 当前版本号
     * @return
     */
    public static String getVersion(Context context) {
        String version = "未知版本";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
        }

        return version;
    }

    /**
     * 判断手机是否联网
     * ConnectivityManager
     * @return
     */
    public static boolean isConnected(Context context) {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo!=null) {
            connected = networkInfo.isConnected();
        }

        return connected;
    }


    /**
     * 下载apk文件, 并同步显示进度
     * @param applicationContext
     * @param pd
     * @param apkFile
     * @param apkUrl
     * @throws Exception
     */
    public static void downloadAPK(Context applicationContext,
                                   ProgressDialog pd, File apkFile, String apkUrl) throws Exception {

        InputStream is = null;
        // 1). 得到HttpUrlConnection对象
        URL url = new URL(apkUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 2). 设置
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        // 3). 连接
        conn.connect();
        // 4). 请求并获取服务器端返回的数据流
        int responseCode = conn.getResponseCode();
        if(responseCode==200) {
            //设置最大进度
            pd.setMax(conn.getContentLength());
            is = conn.getInputStream();
            //得到apk文件的输出流
            FileOutputStream fos = new FileOutputStream(apkFile);
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len=is.read(buffer))>0) {
                //写数据
                fos.write(buffer, 0, len);
                //更新进度
                pd.incrementProgressBy(len);
                //模拟网速慢
                //Thread.sleep(20);
            }
            fos.close();
            is.close();
        }
        //5). 关闭流, 连接
        conn.disconnect();
    }
}
