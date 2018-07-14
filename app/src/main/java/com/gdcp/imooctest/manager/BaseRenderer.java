package com.gdcp.imooctest.manager;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by asus- on 2018/6/19.
 */

class BaseRenderer {
    protected Context mContext;

    /**
     * UI
     */
    protected ViewGroup mParentView; //广告要加载到的父容器

    protected void initAdView() {
    }

    /**
     * 展示广告
     */
    public void onShow(String uri) {

    }

    /**
     * 销毁广告
     */
    public void onDispose() {
    }
}
