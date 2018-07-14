package com.gdcp.imooctest.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * 接收请求参数，为我们生成Request对象
 */

public class CommonRequest {
    //返回一个创建好的post request对象
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder formBodyBuild=new FormBody.Builder();
        if (params!=null){
            for (Map.Entry<String,String>entry:params.urlParams.entrySet()){
                //将请求参数遍历添加到我们的请求体构建类中
                formBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }
        //通过请求体构建类的build的方法获取真正的请求体对象
        FormBody formBody=formBodyBuild.build();
        return new Request.Builder().url(url).post(formBody).build();
    }
//返回一个创建好的get request对象
    public static Request createGetRequest(String url,RequestParams params){
         StringBuffer urlBuider=new StringBuffer(url).append("?");
         if (params!=null){
             for (Map.Entry<String,String>entry:params.urlParams.entrySet()){
                 //将请求参数遍历添加到我们的请求体构建类中
                 urlBuider.append(entry.getKey()).append("=")
                 .append(entry.getValue()).append("&");
             }
         }
          return new Request.Builder().url(urlBuider.substring(0,urlBuider.length()-1))
                  .get().build();
    }

}
