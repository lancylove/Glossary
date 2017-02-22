package com.tonglu.glossary.glossary.view.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
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
import com.tonglu.glossary.glossary.model.NoteBean;
import com.tonglu.glossary.glossary.presenter.NoteFragmentPresenter;
import com.tonglu.glossary.glossary.utils.Constant;
import com.tonglu.glossary.glossary.view.GlossaryApplication;
import com.tonglu.glossary.glossary.view.activity.ContextActivity;
import com.tonglu.glossary.glossary.view.activity.NoteContentActivity;
import com.tonglu.glossary.glossary.view.activity.NoteEditActivity;
import com.tonglu.glossary.glossary.view.adapter.NoteListAdapter;
import com.tonglu.glossary.glossary.view.viewInterface.NoteFragmentInterface;
import com.tonglu.glossary.glossary.view.viewInterface.OnItemDeleteClick;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tonglu on 2017/2/3.
 */
public class NoteFragment extends Fragment implements NoteFragmentInterface {

    public static final int REQUEST_CODE_CONTEXT = 1000;
    public static final int RESULT_CODE_CONTEXT = 1001;


    private Context context;
    private Activity activity;
    private SlideListView slideListView;
    private TextView titleView;
    private ImageView addView;


    LinearLayout none;
    protected TextView noneText;
    protected ImageView noneImg;
    protected ProgressBar nonePb;


    private NoteFragmentPresenter presenter;
    NoteListAdapter noteListAdapter;
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
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);

        context = getContext();
        activity = getActivity();

        currentContextBean = new ContextBean();
        currentContextBean.contextName = GlossaryApplication.getInstance().getSpUtil().getcurrentContext();
        currentContextBean.id = GlossaryApplication.getInstance().getSpUtil().getcurrentContextId();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_CONTEXT_CHANGED);
        activity.registerReceiver(contextChangedReceiver, filter);

        presenter = new NoteFragmentPresenter(context, this);
        noteListAdapter = new NoteListAdapter(context);
        noteListAdapter.setDeleteClick(new OnItemDeleteClick() {
            @Override
            public void itemDeleteClick(int position) {
                presenter.itemDelete(position);
            }
        });

        slideListView = (SlideListView) view.findViewById(R.id.slidelistview_note);
        titleView = (TextView) view.findViewById(R.id.tv_title);
        addView = (ImageView) view.findViewById(R.id.iv_right_btn);
        view.findViewById(R.id.iv_back).setVisibility(View.GONE);

        Drawable drawable = getResources().getDrawable(R.mipmap.ic_added);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        titleView.setCompoundDrawables(null, null, drawable, null);//画在右边
        titleView.setText(currentContextBean.contextName);

        setClick();
        setNoneView();
        slideListView.setAdapter(noteListAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            activity.unregisterReceiver(contextChangedReceiver);
        }catch (Exception e){}

    }

    private void setNoneView() {
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
    }

    private void setClick() {
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteEditActivity.class);
                startActivity(intent);
            }
        });

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContextActivity.class);
                intent.putExtra("requestCode", REQUEST_CODE_CONTEXT);
                startActivity(intent);
            }
        });

        slideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.itemClick(position);

            }
        });
    }


    @Override
    public void setAdapterData(ArrayList<NoteBean> noteBeens) {
        noteListAdapter.setmData(noteBeens);
        nonePb.setVisibility(View.GONE);
        noneImg.setVisibility(View.VISIBLE);
        noneText.setText(R.string.null_note);
    }

    @Override
    public void showContent(NoteBean noteBean) {
        Intent intent = new Intent(context, NoteContentActivity.class);
        intent.putExtra("note", noteBean);
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
