package com.tonglu.glossary.glossary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonglu on 2017/2/4.
 */
public class NoteBean implements Parcelable {
    public String creatTime;
    public String content;

    public NoteBean() {

    }

    protected NoteBean(Parcel in) {
        creatTime = in.readString();
        content = in.readString();
    }

    public static final Creator<NoteBean> CREATOR = new Creator<NoteBean>() {
        @Override
        public NoteBean createFromParcel(Parcel in) {
            return new NoteBean(in);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatTime);
        dest.writeString(content);
    }
}
