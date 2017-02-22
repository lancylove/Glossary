package com.tonglu.glossary.glossary.presenter;

import android.content.Context;
import android.content.Intent;

import com.tonglu.glossary.glossary.model.WordBean;
import com.tonglu.glossary.glossary.view.viewInterface.WordsDetailInterface;

/**
 * Created by tonglu on 2017/2/14.
 */
public class WordsDetalsPresenter extends BasePresenter {

    WordsDetailInterface view;

    public WordsDetalsPresenter(Context context, WordsDetailInterface view) {

        super(context, view);
        this.view = view;

    }

    public void searchCode(WordBean wordBean) {



    }
}
