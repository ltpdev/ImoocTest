package com.gdcp.imooctest.okhttp;

import com.gdcp.imooctest.okhttp.https.HttpsUtils;
import com.gdcp.imooctest.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */

public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;
    static {
        OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder();
        //为构建者配置时间
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        okhttpBuilder.followRedirects(true);
        //增加https支持
        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okhttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okhttpBuilder.build();
    }
//发送具体的http/https请求
    public static Call sendRequest(Request request, CommonJsonCallback callback){
             Call call=mOkHttpClient.newCall(request);
             call.enqueue(callback);
             return call;
    }


}
