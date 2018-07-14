package com.gdcp.imooctest.okhttp.listener;

/**
 * Created by asus- on 2018/6/16.
 */

public class DisposeDataHandle {
    public DisposeDataListener disposeDataListener=null;
    public Class<?>mClass=null;
    public String source=null;
    public DisposeDataHandle(DisposeDataListener listener){
        this.disposeDataListener=listener;
    }
    public DisposeDataHandle(DisposeDataListener listener,Class<?>mClass){
        this.disposeDataListener=listener;
        this.mClass=mClass;
    }
    public DisposeDataHandle(DisposeDataListener listener, String source)
    {
        this.disposeDataListener = listener;
        this.source = source;
    }
}
