package com.tonglu.glossary.glossary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地存储小部分数据
 * Created by xinqing on 2015/12/26.
 */
public class SharedPreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    public SharedPreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void exit() {
        clearUserId();
        setName("");
        setcurrentContext("");

    }

    public void clearUserId() {
        editor.remove("UserId");
        editor.commit();
    }




    public void setUserId(String userId) {
        editor.putString("UserId", userId);
        editor.commit();
    }



    public String getUserId() {
        return sp.getString("UserId", "");
    }


    public void setName(String name) {
        editor.putString("Name", name);
        editor.commit();
    }

    public String getName() {
        return sp.getString("Name", "");
    }

    public void setcurrentContext(String currentContext) {
        editor.putString("currentContext", currentContext);
        editor.commit();
    }

    public String getcurrentContext() {
        return sp.getString("currentContext", null);
    }


    public void setcurrentContextId(String currentContextId) {
        editor.putString("currentContextId", currentContextId);
        editor.commit();
    }

    public String getcurrentContextId() {
        return sp.getString("currentContextId", null);
    }



    public void setLastCheckVersionTime(long data) {
        editor.putLong("last_check_version_time", data);
        editor.commit();
    }

    public long getLastCheckVersionTime() {
        return sp.getLong("last_check_version_time", 0);
    }


    public Boolean hasNewVersion() {
        return sp.getBoolean("has_new_version", false);

    }

    public void setHasNewVersion(boolean hasNewVersion) {
        editor.putBoolean("has_new_version", hasNewVersion);
        editor.commit();
    }




    public void setIsFirst(boolean b) {
        editor.putBoolean("isFirst", b);
        editor.commit();
    }

    public boolean isFirst() {

        return sp.getBoolean("isFirst", true);
    }
}
