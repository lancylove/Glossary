package com.tonglu.glossary.glossary.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.CodeSearchBean;
import com.tonglu.glossary.glossary.presenter.SearchCodePresenter;
import com.tonglu.glossary.glossary.view.adapter.SearchCodeAdapter;
import com.tonglu.glossary.glossary.view.viewInterface.SearchCodeInterface;

//代码搜索页面
public class SearchCodeActivity extends BaseActivity implements SearchCodeInterface {

    private String searchCodeContent;
    private ListView searchCodeView;
    private SearchCodePresenter presenter;
    private SearchCodeAdapter searchCodeAdapter;

    LinearLayout none;
    protected TextView noneText;
    protected ImageView noneImg;
    protected ProgressBar nonePb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_code);
        context = this;
        searchCodeContent = getIntent().getStringExtra("content");
        presenter = new SearchCodePresenter(context, this);
        initView();

        presenter.searchCode(searchCodeContent);
    }

    @Override
    public void initView() {
        super.initView();
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);
        findViewById(R.id.tv_title).setVisibility(View.GONE);
        addView.setVisibility(View.GONE);

        searchCodeView = (ListView) findViewById(R.id.lv_search_code);
        searchCodeAdapter = new SearchCodeAdapter(context);

        none = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_empty, null);

        noneText = (TextView) none.findViewById(R.id.empty_text);
        noneText.setText(R.string.loading);
        noneImg = (ImageView) none.findViewById(R.id.empty_img);
        noneImg.setVisibility(View.GONE);
        nonePb = (ProgressBar) none.findViewById(R.id.empty_progress);
        nonePb.setVisibility(View.VISIBLE);
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noneText.setText(R.string.loading);
                noneImg.setVisibility(View.GONE);
                nonePb.setVisibility(View.VISIBLE);
                presenter.searchCode(searchCodeContent);
            }
        });
        ((ViewGroup) searchCodeView.getParent()).addView(none);
        searchCodeView.setEmptyView(none);
        searchCodeView.setAdapter(searchCodeAdapter);

        setOnClick();
    }

    private void setOnClick() {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchCodeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClik(position);
            }
        });

    }

    @Override
    public void setAdapterData(CodeSearchBean result) {

        searchCodeAdapter.setMatchterm(result.matchterm);
        searchCodeAdapter.setmData(result.results);
        nonePb.setVisibility(View.GONE);
        noneImg.setVisibility(View.VISIBLE);
        noneText.setText(R.string.null_code);
    }
}
