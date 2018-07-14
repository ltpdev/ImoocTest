package com.gdcp.imooctest.manager;

import android.util.Log;
import android.view.ViewGroup;

import com.gdcp.imooctest.model.AdInstance;
import com.gdcp.imooctest.okhttp.RequestCenter;
import com.gdcp.imooctest.okhttp.https.HttpConstants;
import com.gdcp.imooctest.okhttp.listener.DisposeDataListener;

/**
 * Created by asus- on 2018/6/19.
 */

public class DisplayAdSlot implements DisposeDataListener,ImageAdRenderer.ImageRendererListener{
    private ViewGroup container;
    private DisplayConextListener mContextListener;
    private AdInstance mInstance = null; //服务器返回广告实例
    private BaseRenderer baseRenderer;
    public DisplayAdSlot(ViewGroup parentView) {
        container = parentView;
        load();
    }

    private void load() {
        RequestCenter.sendImageAdRequest(HttpConstants.DISPLAY_AD_URL, this);
    }

    public void setContextListener(DisplayConextListener listener) {
        mContextListener = listener;
    }

    @Override
    public void onSuccess(Object responseObj) {
        mInstance = (AdInstance) responseObj;
        Log.i("DisplayAdSlot", "onSuccess: "+responseObj.toString());
        switch (mInstance.type){
            case "image":
                baseRenderer=new ImageAdRenderer(container);
                ((ImageAdRenderer) baseRenderer).setImageRendererListener(this);
                 baseRenderer.onShow(mInstance.values.get(0).resource);
                break;
            //返回的是Html类型的广告，创建Html渲染器
            case "html":
                break;
        }
    }

    @Override
    public void onFailure(Object reasonObj) {
        if (mContextListener != null) {
            mContextListener.onLoadingFailed();
        }
        //这应该去加载本地广告
    }

    @Override
    public void onLoadingComplete() {
        if (mContextListener != null) {
            mContextListener.onLoadingComplete();
        }
    }

    @Override
    public void onLoadingFailed() {
        if (mContextListener != null) {
            mContextListener.onLoadingFailed();
        }
    }

    public void onDispose() {
        if (baseRenderer != null) {
            baseRenderer.onDispose();
        }
    }

    public interface DisplayConextListener {

        void onLoadingComplete();

        void onLoadingFailed();
    }
}
