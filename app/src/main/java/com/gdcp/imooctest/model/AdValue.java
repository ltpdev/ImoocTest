package com.gdcp.imooctest.model;

import java.util.ArrayList;

/**
 * Created by asus- on 2018/6/19.
 */

public class AdValue {
    public String resourceID;
    public String adid;
    public String resource;
    public String thumb;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;
    public String type;
}
