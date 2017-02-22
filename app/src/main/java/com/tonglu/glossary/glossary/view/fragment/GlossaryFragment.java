package com.tonglu.glossary.glossary.view.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.tonglu.glossary.glossary.model.WordBean;
import com.tonglu.glossary.glossary.presenter.GlossaryFragmentPresenter;
import com.tonglu.glossary.glossary.utils.Constant;
import com.tonglu.glossary.glossary.view.GlossaryApplication;
import com.tonglu.glossary.glossary.view.activity.WordsAddActivity;
import com.tonglu.glossary.glossary.view.activity.WordsDetailActivity;
import com.tonglu.glossary.glossary.view.activity.WordsEditActivity;
import com.tonglu.glossary.glossary.view.adapter.WordListAdapter;
import com.tonglu.glossary.glossary.view.viewInterface.GlossaryFragmentInterface;
import com.tonglu.glossary.glossary.view.viewInterface.OnItemDeleteClick;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public class GlossaryFragment extends Fragment implements GlossaryFragmentInterface {


    private Context context;
    private Activity activity;
    private SlideListView slideListView;
    private TextView titleView;
    private ImageView addView;


    LinearLayout none;
    protected TextView noneText;
    protected ImageView noneImg;
    protected ProgressBar nonePb;


    private GlossaryFragmentPresenter presenter;
    WordListAdapter wordListAdapter;
    ContextBean currentContextBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.reLoad(currentContextBean);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);

        context = getContext();
        activity = getActivity();

        currentContextBean = new ContextBean();
        currentContextBean.contextName = GlossaryApplication.getInstance().getSpUtil().getcurrentContext();
        currentContextBean.id = GlossaryApplication.getInstance().getSpUtil().getcurrentContextId();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_CONTEXT_CHANGED);
        activity.registerReceiver(contextChangedReceiver, filter);

        presenter = new GlossaryFragmentPresenter(context, this);
        wordListAdapter = new WordListAdapter(context);
        wordListAdapter.setDeleteClick(new OnItemDeleteClick() {
            @Override
            public void itemDeleteClick(int position) {
                presenter.itemDelete(position);
            }
        });

        slideListView = (SlideListView) view.findViewById(R.id.slidelistview_note);
        titleView = (TextView) view.findViewById(R.id.tv_title);
        addView = (ImageView) view.findViewById(R.id.iv_right_btn);
        view.findViewById(R.id.iv_back).setVisibility(View.GONE);

        titleView.setText(currentContextBean.contextName);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WordsAddActivity.class);
                startActivity(intent);

            }
        });

        slideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.itemClick(position);

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
                presenter.reLoad(currentContextBean);
            }
        });
        ((ViewGroup) slideListView.getParent()).addView(none);
        slideListView.setEmptyView(none);
        slideListView.setAdapter(wordListAdapter);

        return view;
    }

    public void initView() {

    }

    @Override
    public void setAdapterData(ArrayList<WordBean> wordBeens) {
        wordListAdapter.setmData(wordBeens);
        nonePb.setVisibility(View.GONE);
        noneImg.setVisibility(View.VISIBLE);
        noneText.setText(R.string.null_word);
    }

    @Override
    public void showDetail(WordBean wordBean) {
        Intent intent = new Intent(context, WordsDetailActivity.class);
        intent.putExtra("word", wordBean);
        startActivity(intent);

    }

    private BroadcastReceiver contextChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(Constant.ACTION_CONTEXT_CHANGED)) {
                currentContextBean.contextName = GlossaryApplication.getInstance().getSpUtil().getcurrentContext();
                currentContextBean.id = GlossaryApplication.getInstance().getSpUtil().getcurrentContextId();
                titleView.setText(currentContextBean.contextName);
                presenter.reLoad(currentContextBean);

            }

        }
    };
}
