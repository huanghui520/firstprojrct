package com.fajuary.xiyishop_android.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.fajuary.myapp.widget.alertview.AlertView;
import com.fajuary.myapp.widget.alertview.OnItemClickListener;
import com.fajuary.xiyishop_android.BeapakeShopApp;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by aa on 2016/4/8.
 * 一些小算法和小功能集合
 */
public class SmallFeatureUtils {

    public static void Toast(String str) {
        WindowManager wm = (WindowManager) BeapakeShopApp.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int SCREEN_HEIGHT = dm.heightPixels;
        final Toast toast = Toast.makeText(BeapakeShopApp.getInstance().getApplicationContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(80, 0, SCREEN_HEIGHT / 4);
//        toast.setDuration(1000);
//        toast.show();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, 2000);
    }

    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                          .getSystemService(Context.CONNECTIVITY_SERVICE);
            if ( connectivity != null ) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if ( info != null && info.isConnected() ) {
                    // 判断当前网络是否已经连接
                    if ( info.getState() == NetworkInfo.State.CONNECTED ) {
                        return true;
                    }
                }
            }
        } catch ( Exception e ) {
            Logger.e(e.toString());
        }
        return false;
    }

    /**
     * 传入时间戳
     *
     * @param longTim
     *
     * @return
     */
    public static String getYMD(String longTim) {
        long timestam = 0;
        try {
            timestam = Long.parseLong(longTim);
        } catch ( NumberFormatException e ) {
            timestam = 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new java.util.Date(timestam * 1000));

        return date;
    }

    public static String getTimeYMDHMStr(String timeStr) {
        long timestam = 0;
        try {
            timestam = Long.parseLong(timeStr);
        } catch ( NumberFormatException e ) {
            timestam = 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = simpleDateFormat.format(new java.util.Date(timestam * 1000));

        return date;
    }

    /**
     * 获取圆角位图的方法
     *
     * @param bitmap
     *               需要转化成圆角的位图
     * @param pixels
     *               圆角的度数，数值越大，圆角越大
     *
     * @return 处理后的圆角位图
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                      bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static int getWindowWith() {
        WindowManager wm = (WindowManager) BeapakeShopApp.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
                  Manifest.permission.READ_EXTERNAL_STORAGE,
                  Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission

        int permission = ActivityCompat.checkSelfPermission(activity,
                      Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if ( permission != PackageManager.PERMISSION_GRANTED ) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                          REQUEST_EXTERNAL_STORAGE);
        } else {
            // 这里是取消权限要处理的事情
//                    MessageUtil.toastPrintShort(MyPersonnalInFoActivity.this, "要打开权限才可以使用");
            SmallFeatureUtils.Toast("要打开权限才可以使用");

        }
    }

    /**
     * 获取版本名字
     *
     * @param context
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String version = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch ( Exception e ) {
            version = null;
        }
        return version;
    }


    /**
     * 外部存储
     *
     * @param activity
     *
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                      Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            activity.requestPermissions(new String[]{
                          Manifest.permission.READ_EXTERNAL_STORAGE,
                          Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
            return false;
        }

        return true;
    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if ( mNetworkInfo != null && mNetworkInfo.isAvailable() ) {   //判断网络连接是否打开
            return mNetworkInfo.isConnected();
        }
        return false;
    }

    public static void getNetDialog(final Activity activity) {
        AlertView alertView = new AlertView("没网了,是否打开???", null, "取消", null, new String[]{"确定"},
                      activity, AlertView.Style.Alert, new OnItemClickListener() {
            public void onItemClick(Object o, int position) {
                switch ( position ) {
                    case 0://确定
//                        Intent intent=null;
                        //判断手机系统的版本  即API大于10 就是3.0或以上版本
//                                if(android.os.Build.VERSION.SDK_INT>10){
//                                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//                                }else{
//                                    intent = new Intent();
//                                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
//                                    intent.setComponent(component);
//                                    intent.setAction("android.intent.action.VIEW");
//                                }
                        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        activity.startActivity(intent);
                        break;

                }
            }
        });

        alertView.show();

    }

    /**
     * 关闭输入法
     */
    public static void closeEditer(Activity context) {
        View view = context.getWindow().peekDecorView();
        if ( view != null ) {
            InputMethodManager inputmanger = (InputMethodManager) context
                          .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 图片变灰色
     *
     * @param bitmap
     *
     * @return
     */
    public static Bitmap creategrayBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.01f);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    /***/
    /**
     * 图片去色,返回灰度图片
     *
     * @param bmpOriginal
     *               传入的图片
     *
     * @return 去色后的图片
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }


    /**
     * 对图片进行处理
     *
     * @param bitmap
     * @param sx
     *               色相
     * @param bhd
     *               饱和度
     * @param ld
     *               亮度
     *
     * @return
     */
    public static Bitmap getColorImage(Bitmap bitmap, float sx, float bhd, float ld) {
        Bitmap bmp = bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix sxMatrix = new ColorMatrix();// 设置色调
        sxMatrix.setRotate(0, sx);
        sxMatrix.setRotate(1, sx);
        sxMatrix.setRotate(2, sx);
        ColorMatrix bhdMatrix = new ColorMatrix();// 设置饱和度
        bhdMatrix.setSaturation(bhd);
        ColorMatrix ldMatrix = new ColorMatrix();// 设置亮度
        ldMatrix.setScale(ld, ld, ld, 1);
        ColorMatrix mixMatrix = new ColorMatrix();// 设置整体效果
        mixMatrix.postConcat(sxMatrix);
        mixMatrix.postConcat(bhdMatrix);
        mixMatrix.postConcat(ldMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(mixMatrix));// 用颜色过滤器过滤
        canvas.drawBitmap(bmp, 0, 0, paint);// 重新画图
        return bmp;
    }

    /**
     * 添加水印效果
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft,
                                                int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
    }

    public static void callPhone(Context context,String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        context.startActivity(intent);

    }



    public static void setScreenImgSize(ImageView imageView){
        int size =getWindowWith();
        /**
         * 设置图片宽高
         */
        ViewGroup.LayoutParams imgparms = imageView.getLayoutParams();
        imgparms.height = size/2;
        imgparms.width = size;
        imageView.setLayoutParams(imgparms);
    }

   /* *//**
     * 用字符串生成二维码
     * @param str
     * @author zhouzhe@lenovo-cw.com
     * @return
     * @throws WriterException
     *//*
    public static Bitmap Create2DCode(String str) throws WriterException {
//生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
//二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                if ( matrix.get(x, y) ) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
    }*/

    public static float getDensityDpi(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        float m=width/density;

        return density;
    }
    public static ViewGroup.LayoutParams getParms(ImageView imageView){
        ViewGroup.LayoutParams imgparms = imageView.getLayoutParams();
        return imgparms;
    }
}
