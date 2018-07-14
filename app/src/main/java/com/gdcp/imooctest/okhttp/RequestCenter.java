package com.gdcp.imooctest.okhttp;

import com.gdcp.imooctest.model.AdInstance;
import com.gdcp.imooctest.model.BaseRecommandModel;
import com.gdcp.imooctest.okhttp.https.HttpConstants;
import com.gdcp.imooctest.okhttp.listener.DisposeDataHandle;
import com.gdcp.imooctest.okhttp.listener.DisposeDataListener;
import com.gdcp.imooctest.okhttp.request.CommonRequest;
import com.gdcp.imooctest.okhttp.request.RequestParams;
import com.gdcp.imooctest.okhttp.response.CommonJsonCallback;

/**
 * Created by asus- on 2018/6/17.
 */

public class RequestCenter {
    //根据参数发送get所有请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.sendRequest(CommonRequest.
                createGetRequest(url, params), new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }


    //请求首页推荐的数据
    public static void requestRecommandData(DisposeDataListener listener){
        RequestCenter.getRequest(HttpConstants.HOME_RECOMMAND,null,listener,BaseRecommandModel.class);
    }

    public static void sendImageAdRequest(String url, DisposeDataListener listener){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest(url, null),
                new CommonJsonCallback(new DisposeDataHandle(listener, AdInstance.class)));
    }
}
