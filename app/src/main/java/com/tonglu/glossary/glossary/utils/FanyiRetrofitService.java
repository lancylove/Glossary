package com.tonglu.glossary.glossary.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonglu on 2016/8/30.
 */
public class FanyiRetrofitService {
    //https://searchcode.com/api/codesearch_I/?q=soup&p=1&per_page=100  //查询词汇使用情况
    //http://api.pullword.com/get.php?source=清华大学&param1=0&param2=1   //词汇大爆炸
    //http://fanyi.youdao.com/openapi.do?keyfrom=DDDML-Glossary&key=1475428248&type=data&doctype=json&version=1.1&q=%E7%BF%BB%E8%AF%91     //有道翻译


    public static final String URL = "http://fanyi.youdao.com";//有道翻译


    protected FanyiRetrofitService() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Constant.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        //设置timeout
        builder.connectTimeout(Constant.Timeout, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    private volatile static FanyiRetrofitService instance = null;

    private Retrofit retrofit;

    public static FanyiRetrofitService getInstance() {
        if (instance == null) {
            synchronized (FanyiRetrofitService.class) {
                if (instance == null) {
                    instance = new FanyiRetrofitService();
                }
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }


}
