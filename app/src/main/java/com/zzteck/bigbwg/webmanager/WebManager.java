/**
 * 项目名称：PublcNumbe
 * r
 * 文件名：WebManager.java
 * 2015-1-12-下午12:00:47
 * 2015 万家恒通公司-版权所有
 *
 * @version 1.0.0
 */
package com.zzteck.bigbwg.webmanager;

import android.content.Context;
import android.text.TextUtils;

import com.zzteck.bigbwg.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : liujw
 * modify : 2015-1-12 下午12:00:47
 */
public class WebManager {

    private static WebManager mInstance;

    private Context mContext;

    public OkHttpClient okHttpClient ;

    private WebManager(final Context context) {
        super();
        okHttpClient  = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = (String) SharedPreferencesUtils.getParam(context,"token","");
                if(!TextUtils.isEmpty(token)){
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Dljapp-User-Token",token).build() ;
                    return chain.proceed(request);
                }else{
                    Request request = chain.request()
                            .newBuilder()
                            .build() ;
                    return chain.proceed(request);
                }
            }
        })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
                ;


    }

    public synchronized static WebManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WebManager(context);
        }
        return mInstance;
    }


  /*  private static WebManager mInstance;

    private Context mContext;

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setTimeout(30000);
    }

    private WebManager() {
        super();
    }

    public synchronized static WebManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WebManager();
        }
        return mInstance;
    }

    public void cancelAllRequest() {
        client.cancelAllRequests(true);
    }

    public AsyncHttpClient getClient() {
        return client;
    }

    public void post(Context context, String urlString, StringEntity params, AsyncHttpResponseHandler res) {
        client.post(context,urlString,params,"application/json", res);
        String token = (String) SharedPreferencesUtils.getParam(context,"token","");
        if(!TextUtils.isEmpty(token)){
            client.addHeader("Dljapp-User-Token",token);
        }
    }

    public void post(String urlString, RequestParams params, AsyncHttpResponseHandler res) {
        client.post(urlString, params, res);
    }

    public void get(String urlString, FileAsyncHttpResponseHandler res) {
        client.get(urlString, res);
    }

    // 用一个完整url获取一个string对象
    public void get(String urlString, AsyncHttpResponseHandler res) {
        client.get(urlString, res);
    }

    // url里面带参数
    public void get(String urlString, RequestParams params, AsyncHttpResponseHandler res) {
        client.get(urlString, params, res);
    }

    // 不带参数，获取json对象或者数组
    public void get(String urlString, JsonHttpResponseHandler res) {
        client.get(urlString, res);
    }

    // 带参数，获取json对象或者数组
    public void get(String urlString, RequestParams params, JsonHttpResponseHandler res) {
        client.get(urlString, params, res);
    }

    // 下载数据使用，会返回byte数据
    public void get(String uString, BinaryHttpResponseHandler bHandler) {
        client.get(uString, bHandler);
    }*/


}
