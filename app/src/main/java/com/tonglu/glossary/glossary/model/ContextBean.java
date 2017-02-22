package com.tonglu.glossary.glossary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonglu on 2017/2/6.
 */
public class ContextBean implements Parcelable {
    public String id;
    public String contextName;

    public ContextBean() {
    }

    protected ContextBean(Parcel in) {
        id = in.readString();
        contextName = in.readString();
    }

    public static final Creator<ContextBean> CREATOR = new Creator<ContextBean>() {
        @Override
        public ContextBean createFromParcel(Parcel in) {
            return new ContextBean(in);
        }

        @Override
        public ContextBean[] newArray(int size) {
            return new ContextBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(contextName);
    }
}
