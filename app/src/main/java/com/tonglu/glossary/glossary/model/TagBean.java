package com.tonglu.glossary.glossary.model;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TagBean {

    public String tagName;
    public boolean isChecked = false;

    public TagBean(String tagName) {
        this.tagName = tagName;
    }

    public TagBean(String tagName, boolean isChecked) {
        this.tagName = tagName;
        this.isChecked = isChecked;
    }

    public TagBean() {

    }

}
