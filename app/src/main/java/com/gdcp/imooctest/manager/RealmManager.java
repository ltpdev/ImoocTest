package com.gdcp.imooctest.manager;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by asus- on 2018/6/19.
 */

public class RealmManager {

    //SDK数据库名字
    private static final String DB_NAME = "vnandroidsdk.realm";

    private static Realm mRealm;

    public static void init(Context context) {
        //负责初始化整个Relam数据库
            Realm.init(context);
    }

    public static Realm getRealm(){
        mRealm=Realm.getInstance(new RealmConfiguration.Builder()
        .name(DB_NAME).build());
        return mRealm;
    }

public static void closeRealm(){
        if (mRealm!=null&&!mRealm.isClosed()){
            mRealm.close();
        }

}
}
