package com.tonglu.glossary.glossary.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.WordBean;
import com.tonglu.glossary.glossary.presenter.WordsDetalsPresenter;
import com.tonglu.glossary.glossary.view.viewInterface.WordsDetailInterface;

//词汇详情页面
public class WordsDetailActivity extends BaseActivity implements WordsDetailInterface {

    WordBean wordBean;

    private TextView expressionView;
    private TextView chineseExpressionView;
    private TextView partOfSpeechView;
    private TextView tagsView;
    private TextView definitionView;
    private Button searchButton;

    private WordsDetalsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_detail);
        context = this;
        wordBean = getIntent().getParcelableExtra("word");
        if (wordBean == null) finish();
        initView();

        presenter = new WordsDetalsPresenter(context, this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                presenter.searchCode(wordBean);

                Intent intent = new Intent(context, SearchCodeActivity.class);
                intent.putExtra("content", wordBean.expression);
                startActivity(intent);

            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);
        findViewById(R.id.tv_title).setVisibility(View.GONE);

        addView.setImageResource(R.mipmap.ic_added);


        expressionView = (TextView) findViewById(R.id.tv_expression);
        chineseExpressionView = (TextView) findViewById(R.id.tv_chinese_expression);
        partOfSpeechView = (TextView) findViewById(R.id.tv_part_of_speech);
        tagsView = (TextView) findViewById(R.id.tv_tags);
        definitionView = (TextView) findViewById(R.id.tv_definition);
        searchButton = (Button) findViewById(R.id.btn_search_code);
        expressionView.setText(wordBean.expression);
        chineseExpressionView.setText(wordBean.chineseExpression);
        definitionView.setText(wordBean.definition);

        StringBuffer partOfSpeech = new StringBuffer();
        String[] partOfSpeechs = wordBean.partOfSpeechs;
        int count = partOfSpeechs.length;
        for (int i = 0; i < count; i++) {
            partOfSpeech.append(partOfSpeechs[i]);
            if (i < count - 1) {
                partOfSpeech.append("、");
            }
        }

        partOfSpeechView.setText(partOfSpeech);

        StringBuffer st = new StringBuffer();
        String[] tags = wordBean.tags;
        int tagscount = tags.length;
        for (int i = 0; i < tagscount; i++) {
            st.append(tags[i]);
            if (i < tagscount - 1) {
                st.append("、");
            }
        }

        tagsView.setText(st);

        setOnClick();
    }

    private void setOnClick() {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                Intent intent = new Intent(context,WordsEditActivity.class);
                intent.putExtra("word",wordBean);
                startActivity(intent);

            }
        });
    }
}
