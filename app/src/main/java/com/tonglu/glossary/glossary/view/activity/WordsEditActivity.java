package com.tonglu.glossary.glossary.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.TagBean;
import com.tonglu.glossary.glossary.model.WordBean;
import com.tonglu.glossary.glossary.presenter.WordsEditPresenter;
import com.tonglu.glossary.glossary.view.adapter.DialogAdapter;
import com.tonglu.glossary.glossary.view.dialog.ListDialog;
import com.tonglu.glossary.glossary.view.viewInterface.WordsEditInterface;
import com.tonglu.glossary.glossary.view.widget.DeleteTextView;

import java.util.ArrayList;

//词汇编辑页面
public class WordsEditActivity extends BaseActivity implements WordsEditInterface {

    WordBean wordBean;
    PopupWindow mPopupWindow;
    private TextView expressionView;
    private LinearLayout partOfSpeechLayout;
    private TextView adjView, vView, advView, nView;
    private LinearLayout tagLayout;
    private ImageView addTagView;
    private EditText chineseView, definitionView;

    private WordsEditPresenter presenter;

    private final String[] partOfSpeechArray = {"adj.", "v.", "adv.", "n."};

    private ArrayList<TagBean> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_edit);
        context = this;
        wordBean = getIntent().getParcelableExtra("word");
        presenter = new WordsEditPresenter(context, this);
        if (wordBean == null) finish();

        initView();

        expressionView.setText(wordBean.expression);
        chineseView.setText(wordBean.chineseExpression);
        definitionView.setText(wordBean.definition);

        final String[] partOfSpeechs = wordBean.partOfSpeechs;
        int count = partOfSpeechs.length;
        for (int i = 0; i < count; i++) {
            final int p = i;
            final DeleteTextView tv = new DeleteTextView(context, partOfSpeechs[i]);
            partOfSpeechLayout.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partOfSpeechLayout.removeView(tv);
                    if (partOfSpeechs[p].equals(partOfSpeechArray[0])) {
                        adjView.setEnabled(true);
                    } else if (partOfSpeechs[p].equals(partOfSpeechArray[1])) {
                        vView.setEnabled(true);
                    } else if (partOfSpeechs[p].equals(partOfSpeechArray[2])) {
                        advView.setEnabled(true);
                    } else if (partOfSpeechs[p].equals(partOfSpeechArray[3])) {
                        nView.setEnabled(true);
                    }
                }
            });

            if (partOfSpeechs[i].equals(partOfSpeechArray[0])) {
                adjView.setEnabled(false);
            } else if (partOfSpeechs[i].equals(partOfSpeechArray[1])) {
                vView.setEnabled(false);
            } else if (partOfSpeechs[i].equals(partOfSpeechArray[2])) {
                advView.setEnabled(false);
            } else if (partOfSpeechs[i].equals(partOfSpeechArray[3])) {
                nView.setEnabled(false);
            }

        }


        String[] tags = wordBean.tags;
        int tagscount = tags.length;

        for (int i = 0; i < tagscount; i++) {
            final DeleteTextView tv = new DeleteTextView(context, tags[i]);
            int c = tagLayout.getChildCount();
            tagLayout.addView(tv, c - 1);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagLayout.removeView(tv);
                }
            });

        }


        initClick();
        presenter.initTags();

    }

    private void initClick() {


        adjView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DeleteTextView tv = new DeleteTextView(context, partOfSpeechArray[0]);
                partOfSpeechLayout.addView(tv);
                adjView.setEnabled(false);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        partOfSpeechLayout.removeView(tv);

                        adjView.setEnabled(true);

                    }
                });


            }
        });
        vView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DeleteTextView tv = new DeleteTextView(context, partOfSpeechArray[1]);
                partOfSpeechLayout.addView(tv);
                vView.setEnabled(false);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        partOfSpeechLayout.removeView(tv);

                        vView.setEnabled(true);

                    }
                });
            }
        });
        advView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DeleteTextView tv = new DeleteTextView(context, partOfSpeechArray[2]);
                partOfSpeechLayout.addView(tv);
                advView.setEnabled(false);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        partOfSpeechLayout.removeView(tv);

                        advView.setEnabled(true);

                    }
                });
            }
        });
        nView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DeleteTextView tv = new DeleteTextView(context, partOfSpeechArray[3]);
                partOfSpeechLayout.addView(tv);
                nView.setEnabled(false);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        partOfSpeechLayout.removeView(tv);

                        nView.setEnabled(true);

                    }
                });
            }
        });


        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存数据并退出页面

                finish();
            }
        });


        addTagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallDialog(context, presenter.getTags());

            }
        });


    }


    private void showCallDialog(Context myContext, final ArrayList<TagBean> tags) {

//        View dialogView = LayoutInflater.from(myContext).inflate(
//                R.layout.dialog_linearlayout, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(myContext).create();
//        alertDialog.show();
//        alertDialog.setContentView(dialogView);
//
//        Window window = alertDialog.getWindow();
//        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, 500);
//
//        ListView listView = (ListView) dialogView.findViewById(R.id.ll);
//        listView.setAdapter(new DialogAdapter(context, tags));

        DialogAdapter adapter = new DialogAdapter(context, tags);
        final ListDialog dialog = new ListDialog(context);
        dialog.showDialog(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismissDialog();
                TagBean tag = tags.get(position);
                int c = tagLayout.getChildCount();
                for (int i = 0; i < c-1; i++) {
                    DeleteTextView childView = (DeleteTextView) tagLayout.getChildAt(i);

                    if (tag.tagName.equals(childView.getText().toString())) {
                       return;
                    }

                }
                final DeleteTextView tv = new DeleteTextView(context, tag.tagName);
                tagLayout.addView(tv, c - 1);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tagLayout.removeView(tv);
                    }
                });


            }
        }, adapter);


    }


    @Override
    public void initView() {
        super.initView();

        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);
        findViewById(R.id.tv_title).setVisibility(View.GONE);
        addView.setVisibility(View.GONE);

        expressionView = (TextView) findViewById(R.id.tv_expression);
        partOfSpeechLayout = (LinearLayout) findViewById(R.id.ll_part_of_speech);
        adjView = (TextView) findViewById(R.id.tv_adj);
        vView = (TextView) findViewById(R.id.tv_v);
        advView = (TextView) findViewById(R.id.tv_adv);
        nView = (TextView) findViewById(R.id.tv_n);
        tagLayout = (LinearLayout) findViewById(R.id.ll_tags);
        addTagView = (ImageView) findViewById(R.id.iv_add_tag);
        chineseView = (EditText) findViewById(R.id.edit_chinese);
        definitionView = (EditText) findViewById(R.id.edit_definition);
    }
}
