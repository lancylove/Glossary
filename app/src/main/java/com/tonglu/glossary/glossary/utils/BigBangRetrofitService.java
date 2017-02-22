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
public class BigBangRetrofitService {
    //https://searchcode.com/api/codesearch_I/?q=soup&p=1&per_page=100  //查询词汇使用情况
    //http://api.pullword.com/get.php?source=清华大学&param1=0&param2=1   //词汇大爆炸
    //http://fanyi.youdao.com/openapi.do?keyfrom=DDDML-Glossary&key=1475428248&type=data&doctype=json&version=1.1&q=%E7%BF%BB%E8%AF%91     //有道翻译
    //http://api.ltp-cloud.com/analysis/?api_key=L9L7n8t3GDWaqLjz6P7viElx7SGwyt3UVfWrbhuv&text=%E6%88%91%E6%98%AF%E4%B8%AD%E5%9B%BD%E4%BA%BA%E3%80%82&pattern=all&format=json
    public static final String URL = "http://api.ltp-cloud.com";//词汇大爆炸

    protected BigBangRetrofitService() {

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
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    private volatile static BigBangRetrofitService instance = null;

    private Retrofit retrofit;

    public static BigBangRetrofitService getInstance() {
        if (instance == null) {
            synchronized (BigBangRetrofitService.class) {
                if (instance == null) {
                    instance = new BigBangRetrofitService();
                }
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }


}
