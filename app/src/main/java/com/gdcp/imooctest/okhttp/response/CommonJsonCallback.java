package com.gdcp.imooctest.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.gdcp.imooctest.okhttp.adutil.ResponseEntityToModule;
import com.gdcp.imooctest.okhttp.exception.OkHttpException;
import com.gdcp.imooctest.okhttp.listener.DisposeDataHandle;
import com.gdcp.imooctest.okhttp.listener.DisposeDataListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus- on 2018/6/16.
 */

public class CommonJsonCallback implements Callback {
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle disposeDataHandle){
         this.mListener=disposeDataHandle.disposeDataListener;
         this.mClass=disposeDataHandle.mClass;
         this.mDeliveryHandler=new Handler(Looper.getMainLooper());

    }
    @Override
    public void onFailure(Call call, final IOException e) {
          mDeliveryHandler.post(new Runnable() {
              @Override
              public void run() {
                  mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
              }
          });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result=response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleRespone(result);
            }
        });
    }
//处理服务器返回响应的数据
    private void handleRespone(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result=new JSONObject(responseObj.toString());
            if (result.has(RESULT_CODE)){
                if (result.getInt(RESULT_CODE)==RESULT_CODE_VALUE){
                    if (mClass==null){
                        mListener.onSuccess(result);
                    }else {
                       //Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        Gson gson=new Gson();
                        Object obj = gson.fromJson(responseObj.toString(), mClass);
                        if (obj!=null){
                            mListener.onSuccess(obj);
                        }else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }else {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
                }

            }
        }catch (Exception e){
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
