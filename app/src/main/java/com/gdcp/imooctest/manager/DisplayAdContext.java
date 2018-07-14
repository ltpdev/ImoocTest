package com.gdcp.imooctest.manager;

import android.view.ViewGroup;

/**
 * Created by asus- on 2018/6/19.
 */

public class DisplayAdContext implements
        DisplayAdSlot.DisplayConextListener{

    private DisplayAdSlot mDisplayAdSlot; //图片类型广告
    private DisplayAdAppListener mAdAppListener;

    public void setAdAppListener(DisplayAdAppListener listener) {
        mAdAppListener = listener;
    }
    public DisplayAdContext(ViewGroup parentView) {
        mDisplayAdSlot = new DisplayAdSlot(parentView);
        mDisplayAdSlot.setContextListener(this);
    }


    @Override
    public void onLoadingComplete() {
         if (mAdAppListener!=null){
             mAdAppListener.onLoadingComplete();
         }
    }

    @Override
    public void onLoadingFailed() {
        if (mAdAppListener!=null){
            mAdAppListener.onLoadingFailed();
        }
    }

    public void onDispose() {
        if (mDisplayAdSlot != null) {
            mDisplayAdSlot.onDispose();
        }
    }

    public interface DisplayAdAppListener {

        void onLoadingComplete();

        void onLoadingFailed();
    }
}
