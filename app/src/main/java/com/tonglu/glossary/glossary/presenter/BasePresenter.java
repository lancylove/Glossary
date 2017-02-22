package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.view.viewInterface.BaseViewInterface;

/**
 * Created by tonglu on 2017/1/12.
 */
public class BasePresenter {
    protected Context context;
    protected BaseViewInterface view;

    public BasePresenter(Context context, BaseViewInterface view) {
        this.view = view;
        this.context = context;
    }

}
