package com.tonglu.glossary.glossary.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.presenter.NoteEditPresenter;
import com.tonglu.glossary.glossary.view.viewInterface.NoteEditInterface;

//新增、编辑笔记页面
public class NoteEditActivity extends BaseActivity implements NoteEditInterface {

    EditText noteEditView;
    NoteEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        context = this;
        presenter = new NoteEditPresenter(context, this);

        initView();

    }

    @Override
    public void initView() {
        super.initView();

        titleView = (TextView) findViewById(R.id.tv_title);
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);

        titleView.setVisibility(View.GONE);
        addView.setVisibility(View.GONE);

        noteEditView = (EditText) findViewById(R.id.edit_note);

        setClick();

    }

    private void setClick() {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存笔记

                String noteContent = noteEditView.getText().toString();
                presenter.saveNote(noteContent);

                finish();

            }
        });

    }


}
