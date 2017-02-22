package com.tonglu.glossary.glossary.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.NoteBean;
import com.tonglu.glossary.glossary.view.viewInterface.NoteContentInterface;

/**
 * 笔记详情页面
 */
public class NoteContentActivity extends BaseActivity implements NoteContentInterface {

    private static final String TAG = "NoteContentActivity";

    NoteBean noteBean;
    TextView noteContextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        context = this;
        noteBean = getIntent().getParcelableExtra("note");
        if (noteBean == null) finish();

        initView();


    }

    @Override
    public void initView() {
        super.initView();
        titleView = (TextView) findViewById(R.id.tv_title);
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);

        titleView.setVisibility(View.GONE);
        noteContextView = (TextView) findViewById(R.id.tv_content);
        noteContextView.setText(noteBean.content);


        setClick();

    }

    private void setClick() {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        noteContextView.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.big_bang_menu, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean ret = false;
                if (item.getItemId() == R.id.menu_big_bang) {
                    int start = noteContextView.getSelectionStart();
                    int end = noteContextView.getSelectionEnd();
                    String selectContent = noteBean.content.substring(start, end);
                    Log.i(TAG, "start:" + start + "   end：" + end + "   选中的内容：" + selectContent);

                    mode.finish();


                    Intent intent = new Intent(context, BigBangActivity.class);
                    intent.putExtra("BangContent", selectContent);
                    startActivity(intent);


                    ret = true;
                }
                return ret;

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

    }
}
