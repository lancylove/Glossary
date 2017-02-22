package com.tonglu.glossary.glossary.view;

import android.app.Application;

import com.tonglu.glossary.glossary.utils.SharedPreferenceUtil;

/**
 * Created by tonglu on 2017/2/4.
 */
public class GlossaryApplication extends Application {

    private SharedPreferenceUtil mSpUtil;
    private static final String SP_FILE_NAME = "glossary";
    public static GlossaryApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public synchronized static GlossaryApplication getInstance() {
        return instance;
    }

    public synchronized SharedPreferenceUtil getSpUtil() {
        if (mSpUtil == null) {
            mSpUtil = new SharedPreferenceUtil(this, SP_FILE_NAME);
        }
        return mSpUtil;
    }

}
