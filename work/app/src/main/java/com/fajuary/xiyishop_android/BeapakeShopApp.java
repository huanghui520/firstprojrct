package com.fajuary.xiyishop_android;

import android.app.Activity;
import android.app.Application;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.fajuary.myapp.CrashHandler;
import com.fajuary.myapp.utils.NetUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheEntity;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created 张朋飞 on 2016/9/5.
 * user 864598192
 */
public class BeapakeShopApp extends Application {
    private List<AppCompatActivity> activities;
    private static BeapakeShopApp instance;
    public static final long DEFAULT_MILLISECONDS = 10000L;
    private boolean isNote=false;
    public static int mNetWorkState;
    private AppCompatActivity exitAppAct;


    public static BeapakeShopApp getInstance() {
        return instance;
    }

    private List<AppCompatActivity> acts;

    public void onCreate() {
        super.onCreate();
        //在这里初始化
        BugtagsOptions options = new BugtagsOptions.Builder().
                      trackingLocation(true).//是否获取位置，默认 true
                      trackingCrashLog(true).//是否收集crash，默认 true
                      trackingConsoleLog(true).//是否收集console log，默认 true
                      trackingUserSteps(true).//是否收集用户操作步骤，默认 true
                      trackingNetworkURLFilter("(.*)").//自定义网络请求跟踪的 url 规则，默认 null
                      build();
        Bugtags.start("d54e77165747f59ca58ffc26a82fffac", this, Bugtags.BTGInvocationEventBubble);
//        if (APPTools.checkAPP(this.getApplicationContext(), BBA_PACKAGENAME)) {
//            Intent intent = new Intent();
//            intent.setClassName(BBA_PACKAGENAME, "com.siveco.bluebee.core.activity.GetServerAddressActivity");
//            startActivityForResult(intent, REQUEST_CODE);
//
//        }
        instance = this;
        this.activities = new LinkedList();
        this.acts = new LinkedList();
//        initShareSdk();

        this.initOkhttpUtils();
//        Logger.init("Tag").methodCount(3).hideThreadInfo().methodOffset(1);
        this.initData();
        if ( this.isNote ) {
            CrashHandler.getInstance().init(instance);
        }
        HandlerThread workerThread = new HandlerThread("global_worker_thread");
        workerThread.start();
//        initImageLoader(this);
    }
//    private void initShareSdk() {
//        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
//        PlatformConfig.setWeixin("wx9e4d24e56ff20cdf", "01208ed63bbebaa9746f046bbfd39b6d");
//        //豆瓣RENREN平台目前只能在服务器端配置
//        //新浪微博
//        PlatformConfig.setSinaWeibo("1944394723", "e14df90acc17c5cc96b6ed32bdf7b307");
//
//        PlatformConfig.setQQZone("1105423557", "o4yNWDw5wCxVPq6d");
//
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//
//        PlatformConfig.setAlipay("2015111700822536");
//
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
//
//    }

//    public static void initImageLoader(Context context){
//        if(!ImageLoader.getInstance().isInited()){
//            ImageLoaderConfiguration config = null;
//            if(BuildConfig.DEBUG){
//                config = new ImageLoaderConfiguration.Builder(context)
//						/*.threadPriority(Thread.NORM_PRIORITY - 2)
//						.memoryCacheSize((int) (Runtime.getRuntime().maxMemory() / 4))
//						.diskCacheSize(500 * 1024 * 1024)
//						.writeDebugLogs()
//						.diskCacheFileNameGenerator(new Md5FileNameGenerator())
//						.tasksProcessingOrder(QueueProcessingType.LIFO).build();*/
//
//                              //.memoryCacheExtraOptions(200, 200)
//                              //.diskCacheExtraOptions(200, 200, null).threadPoolSize(3)
//                              .threadPriority(Thread.NORM_PRIORITY - 1)
//                              .tasksProcessingOrder(QueueProcessingType.LIFO)
//                              //.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//						/*.memoryCacheSize(20 * 1024 * 1024)*/
//                              .memoryCacheSizePercentage(13)
//                              .diskCacheSize(500 * 1024 * 1024)
//                              //.imageDownloader(new BaseImageDownloader(A3App.getInstance().getApplicationContext()))
//                              //.imageDecoder(new BaseImageDecoder(true))
//                              //.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                              //.writeDebugLogs()
//                              .build();
//            }else{
//                config = new ImageLoaderConfiguration.Builder(context)
//                              .threadPriority(Thread.NORM_PRIORITY - 2)
//                              .diskCacheSize(500 * 1024 * 1024)
//                              .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                              .tasksProcessingOrder(QueueProcessingType.LIFO).build();
//            }
//            ImageLoader.getInstance().init(config);
//        }
//
//    }
    private void initOkhttpUtils() {
        OkHttpUtils.init(this);

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkHttpUtils.getInstance()

                          //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                          .debug("OkHttpUtils")

                          //如果使用默认的 60秒,以下三行也不需要传
                          .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                          .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                          .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                          //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                          .setCacheMode(CacheMode.NO_CACHE)

                          //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                          .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                          //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                          .setCookieStore(new PersistentCookieStore());          //cookie持久化存储，如果cookie不过期，则一直有效

                          //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
//                    .setCertificates()                                  //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

                          //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                          //这两行同上,不需要就不要传
//                          .addCommonHeaders(headers)                                         //设置全局公共头
//                          .addCommonParams(params);                                          //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getIsnoteDebug() {
        return this.isNote;
    }

    public void initData() {
        mNetWorkState = NetUtil.getNetworkState(this.getApplicationContext());
    }

    public void exitApp() {
        Iterator var1 = this.activities.iterator();

        while (var1.hasNext()) {
            AppCompatActivity activity = (AppCompatActivity) var1.next();
            activity.finish();
        }

        System.exit(0);
    }
    public void exitActs() {
        Iterator var1 = this.acts.iterator();

        while (var1.hasNext()) {
            AppCompatActivity activity = (AppCompatActivity) var1.next();
            activity.finish();
        }

        System.exit(0);
    }

    public void addActivity(AppCompatActivity activity) {
        this.activities.add(activity);
    }

    public void finishActivity(Activity activity) {
        if ( activity != null ) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }

    }

    public List<AppCompatActivity> getActivities() {
        return this.activities;
    }

    public AppCompatActivity getExitAppAct() {
        return this.exitAppAct;
    }

    public void setExitAppAct(AppCompatActivity exitAppAct) {
        this.exitAppAct = exitAppAct;
    }
}
