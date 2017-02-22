package com.tonglu.glossary.glossary.utils;


import com.google.gson.JsonArray;
import com.tonglu.glossary.glossary.model.BaseBean;
import com.tonglu.glossary.glossary.model.CodeSearchBean;
import com.tonglu.glossary.glossary.model.FanyiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tonglu on 2016/8/30.
 */
public interface CommonApi {

    @GET("/341-3")
    Call<BaseBean> getJokeGif(@Query("showapi_appid") String appId, @Query("showapi_sign") String secret, @Query("page") int page, @Query("maxResult") int maxResult);

    // http://api.ltp-cloud.com/analysis/?api_key=Lrbhuv&text=sgfsdg&pattern=all&format=json


//    @GET("/get.php")
//    Call<ResponseBody> bigBangApi(@Query("source") String source, @Query("param1") int param1, @Query("param2") int param2);

    @GET("/analysis")
    Call<JsonArray> bigBangApi(@Query("text") String text, @Query("api_key") String api_key, @Query("pattern") String pattern, @Query("format") String format);


    // https://searchcode.com/api/codesearch_I/?q=soup&p=1&per_page=100
    @GET("/api/codesearch_I/")
    Call<CodeSearchBean> searchCodeApi(@Query("q") String q, @Query("p") int p, @Query("per_page") int per_page);

//http://fanyi.youdao.com/openapi.do?keyfrom=DDDML-Glossary&key=1475428248&type=data&doctype=json&version=1.1&q=%E7%BF%BB%E8%AF%91     //有道翻译
    @GET("/openapi.do")
Call<FanyiBean> fanyiApi(@Query("q") String q, @Query("keyfrom") String keyfrom, @Query("key") String key, @Query("type") String type, @Query("doctype") String doctype, @Query("version") String version);
}
