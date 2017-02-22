package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.view.viewInterface.NoteEditInterface;

/**
 * Created by tonglu on 2017/2/7.
 */
public class NoteEditPresenter extends BasePresenter {

    NoteEditInterface view;

    public NoteEditPresenter(Context context, NoteEditInterface view) {
        super(context, view);
        this.view = view;
    }

    public void saveNote(String noteContent) {

    }
}
