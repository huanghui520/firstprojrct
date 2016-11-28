package com.fajuary.xiyishop_android.tools;

import android.os.SystemClock;
import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * okhttp的简单封装
 */
public class HttpUtils {

    public HttpUtils(String url) {
        long mStartTime = SystemClock.currentThreadTimeMillis();
        if (!TextUtils.isEmpty(url)) {
                    OkHttpUtils
                    .post()
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            if (onHttpRequestListener != null) {
                                onHttpRequestListener.onFailed(e.getMessage());
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (onHttpRequestListener != null) {
                                onHttpRequestListener.onSuccess(response);
                            }
                        }
                    });
        }
    }

    public HttpUtils() {

    }

    /**
     * 访问网络
     *
     * @param url
     * @param commonCallback
     */
   /* public static void request(String url, Callback.CommonCallback<String> commonCallback) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, commonCallback);
    }*/

    public OnHttpRequestListener onHttpRequestListener;

    public void setOnHttpRequestListener(OnHttpRequestListener onHttpRequestListener) {
        this.onHttpRequestListener = onHttpRequestListener;
    }

    public interface OnHttpRequestListener {
        void onSuccess(String json);

        void onFailed(String error);
    }
}
