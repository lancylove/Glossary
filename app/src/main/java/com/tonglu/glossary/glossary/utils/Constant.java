package com.tonglu.glossary.glossary.utils;

import android.os.Environment;

/**
 * Created by tonglu on 2016/8/30.
 */
public class Constant {
    public static final String TAG = "glossary";

    public static final String ACTION_CONTEXT_CHANGED = "com.tonglu.glossary.action.context";

    public static final String appkey = "1475428248";//有道翻译
    public static final String keyfrom = "DDDML-Glossary";//有道翻译

    public static final String XunfeiAppkey = "L9L7n8t3GDWaqLjz6P7viElx7SGwyt3UVfWrbhuv";//讯飞分词appkey
    public static final long Timeout = 30 * 1000l;


    public static final int maxResult = 10;
    public static final boolean DEBUG = true;

    public final static String baseRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + "glossary";

}
