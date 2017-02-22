package com.tonglu.glossary.glossary.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roamer.slidelistview.SlideListView;
import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.presenter.ContextPresent;
import com.tonglu.glossary.glossary.utils.Constant;
import com.tonglu.glossary.glossary.view.adapter.ContextListAdapter;
import com.tonglu.glossary.glossary.view.fragment.NoteFragment;
import com.tonglu.glossary.glossary.view.viewInterface.ContextInterface;
import com.tonglu.glossary.glossary.view.viewInterface.OnItemDeleteClick;

import java.util.ArrayList;

//
public class ContextActivity extends BaseActivity implements ContextInterface {

    private SlideListView slideListView;

    LinearLayout none;
    protected TextView noneText;
    protected ImageView noneImg;
    protected ProgressBar nonePb;


    private ContextPresent presenter;
    ContextListAdapter contextListAdapter;

    private int requestCode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        context = this;

        requestCode = getIntent().getIntExtra("requestCode", -1);

        slideListView = (SlideListView) findViewById(R.id.slidelistview_context);
        presenter = new ContextPresent(context, this);
        contextListAdapter = new ContextListAdapter(context);
        contextListAdapter.setDeleteClick(new OnItemDeleteClick() {
            @Override
            public void itemDeleteClick(int position) {
                presenter.itemDelete(position);
            }
        });


        titleView = (TextView) findViewById(R.id.tv_title);
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);

        titleView.setText("上下文");
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增上下文
                startActivity(new Intent(context, ContextEditActivity.class));

            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        slideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.saveContext(position);
            }
        });

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
                presenter.reLoad();
            }
        });
        ((ViewGroup) slideListView.getParent()).addView(none);
        slideListView.setEmptyView(none);
        slideListView.setAdapter(contextListAdapter);
        presenter.reLoad();
    }


    @Override
    public void setAdapterData(ArrayList<ContextBean> contextBeans) {

        contextListAdapter.setmData(contextBeans);
        nonePb.setVisibility(View.GONE);
        noneImg.setVisibility(View.VISIBLE);
        noneText.setText(R.string.null_context);

    }

    @Override
    public void startMain(ContextBean contextBean) {


        if (requestCode == NoteFragment.REQUEST_CODE_CONTEXT) {
            Intent intent = new Intent();
            intent.setAction(Constant.ACTION_CONTEXT_CHANGED);
            sendBroadcast(intent);
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("context", contextBean);
            startActivity(intent);
        }

        finish();
    }
}
