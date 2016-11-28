package com.fajuary.xiyishop_android.tools;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
public class HttpHelper {
    private static Logger logger = Logger.getLogger(HttpHelper.class);
//    private static final String BASE_URL = "http://192.168.0.27:8081/";
    private static final String BASE_URL = "http://60.205.149.58:8083/xinyi_interfice/";
    public static AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient;
    }
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    static {
    }
    public HttpHelper() {
    }
    public static void get(String url, AsyncHttpResponseHandler handler) {
        asyncHttpClient.get(getAbsoluteUrl(url), handler);
        logger.i(new StringBuilder("GET").append(getAbsoluteUrl(url)).toString());
    }
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        asyncHttpClient.get(getAbsoluteUrl(url), params, handler);
        logger.i(new StringBuilder("GET").append(getAbsoluteUrl(url)).append("&").append(params).toString());
    }
    public static void post(String url, AsyncHttpResponseHandler handler) {
        asyncHttpClient.post(getAbsoluteUrl(url), handler);
        logger.i(new StringBuilder("POST").append(getAbsoluteUrl(url)).toString());
    }
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        if (params != null) {
            Log.e("TAG", params.toString());
            logger.i(new StringBuilder("POST").append(getAbsoluteUrl(url)).append("&").append(params).toString());
        }
            asyncHttpClient.post(getAbsoluteUrl(url), params, handler);
    }
    public static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
