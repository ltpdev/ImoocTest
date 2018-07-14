package com.gdcp.imooctest.manager;

import android.content.Context;
import android.view.ViewGroup;

/**
 * 广告管理者
 */

public class AdSDKManager {
    private static Context mContext;
    public static void init(Context context){
        mContext=context;
        // 初始化数据库管理者
        RealmManager.init(mContext);
    }
    public static Context getContext(){
        return mContext;
    }
    //创建开机广告
    public static void createDisplayAd(ViewGroup container,DisplayAdContext.DisplayAdAppListener appListener){
        DisplayAdContext adContext=new DisplayAdContext(container);
        adContext.setAdAppListener(appListener);
    }
}
