package com.gdcp.imooctest.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by asus- on 2018/6/15.
 */

public class App extends Application{
    private static App app=null;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        /*Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);*/
    }

    public static App getInstance() {
        return app;
    }
}
