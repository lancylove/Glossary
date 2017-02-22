package com.tonglu.glossary.glossary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonglu on 2017/1/13.
 */
public class WordBean implements Parcelable {
    //Expression	Part of speech	Tags	Chinese expression	Definition
    public String expression;//词汇主体
    public String[] partOfSpeechs;//词性
    public String chineseExpression;//中文翻译
    public String definition;//定义
    public String[] tags;//标签

    public WordBean() {
    }

    protected WordBean(Parcel in) {
        expression = in.readString();
        partOfSpeechs = in.createStringArray();
        chineseExpression = in.readString();
        definition = in.readString();
        tags = in.createStringArray();
    }

    public static final Creator<WordBean> CREATOR = new Creator<WordBean>() {
        @Override
        public WordBean createFromParcel(Parcel in) {
            return new WordBean(in);
        }

        @Override
        public WordBean[] newArray(int size) {
            return new WordBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expression);
        dest.writeStringArray(partOfSpeechs);
        dest.writeString(chineseExpression);
        dest.writeString(definition);
        dest.writeStringArray(tags);
    }
}
