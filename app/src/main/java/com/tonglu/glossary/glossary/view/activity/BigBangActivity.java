package com.tonglu.glossary.glossary.view.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.BigBangBean;
import com.tonglu.glossary.glossary.model.FanyiBean;
import com.tonglu.glossary.glossary.model.FanyiWebBean;
import com.tonglu.glossary.glossary.presenter.BigBangPresenter;
import com.tonglu.glossary.glossary.view.viewInterface.BigBangInterface;
import com.tonglu.glossary.glossary.view.widget.AutoExpandLinearLayout;
import com.tonglu.glossary.glossary.view.widget.BangWordView;
import com.tonglu.glossary.glossary.view.widget.TagTextView;

import java.util.ArrayList;

//分词爆炸页面
public class BigBangActivity extends BaseActivity implements BigBangInterface {

    String bangContent;
    AutoExpandLinearLayout autoExpandLinearLayout;
    PopupWindow mPopupWindow;
    BigBangPresenter presenter;
    EditText englshView, chineseView;
    int checkedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bang);
        context = this;
        bangContent = getIntent().getStringExtra("BangContent");
        initView();
        presenter = new BigBangPresenter(context, this);
        presenter.bang(bangContent);
    }

    @Override
    public void initView() {
        super.initView();
        titleView = (TextView) findViewById(R.id.tv_title);
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);
        addView.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        autoExpandLinearLayout = (AutoExpandLinearLayout) findViewById(R.id.auto_layout);
        englshView = (EditText) findViewById(R.id.edit_english);
        chineseView = (EditText) findViewById(R.id.edit_chinese);

    }

    @Override
    public void showBangView(ArrayList<BigBangBean> bigBangBeens) {
        for (BigBangBean bigBangBean : bigBangBeens) {
            BangWordView bangWordView = new BangWordView(getApplication(), bigBangBean.cont);
            autoExpandLinearLayout.addView(bangWordView);

            setBangClick();
        }

    }

    @Override
    public void showPopView(final BangWordView anchorView, FanyiBean result) {
        final View view = LayoutInflater.from(context).inflate(R.layout.pop_fanyi, null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        AutoExpandLinearLayout fanyiLayout = (AutoExpandLinearLayout) view.findViewById(R.id.auto_layout_fanyi);

        ArrayList<String> fanyi = new ArrayList<>();
        if (result.translation != null)
            fanyi.addAll(result.translation);
        if (result.basic != null)
            fanyi.addAll(result.basic.explains);
        ArrayList<FanyiWebBean> web = result.web;
        if (web != null) {
            for (FanyiWebBean webBean : web) {
                if (webBean.key.equals(result.query)) {
                    fanyi.addAll(webBean.value);
                }
            }
        }


        for (String str : fanyi) {
            TagTextView bangWordView = new TagTextView(getApplication(), str);

            fanyiLayout.addView(bangWordView);
        }
        int count = fanyiLayout.getChildCount();

        for (int i = 0; i < count; i++) {
            final TagTextView bangWordView = (TagTextView) fanyiLayout.getChildAt(i);
            bangWordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BangWordView view1 = (BangWordView) autoExpandLinearLayout.getChildAt(checkedId);
                    chineseView.setText(chineseView.getText() + view1.getText().toString());
                    englshView.setText(englshView.getText() + " " + bangWordView.getText().toString());
                }
            });

        }
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
// 自动调整箭头的位置
                autoAdjustArrowPos(mPopupWindow, view, anchorView);
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.8f;
        getWindow().setAttributes(params);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1.0f;//设置为不透明，即恢复原来的界面
                getWindow().setAttributes(params);
            }
        });
        mPopupWindow.showAsDropDown(anchorView, anchorView.getLayoutParams().width / 2, 0);


    }
    private void autoAdjustArrowPos(PopupWindow popupWindow, View contentView, View anchorView) {
        View upArrow = contentView.findViewById(R.id.iv_triangle);

        int pos[] = new int[2];
        contentView.getLocationOnScreen(pos);
        int popLeftPos = pos[0];
        anchorView.getLocationOnScreen(pos);
        int anchorLeftPos = pos[0];
        int arrowLeftMargin = anchorLeftPos - popLeftPos + anchorView.getWidth() / 2 - upArrow.getWidth() / 2;
        RelativeLayout.LayoutParams upArrowParams = (RelativeLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;

    }



    private void setBangClick() {
        int count = autoExpandLinearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            final int position = i;
            final BangWordView view = (BangWordView) autoExpandLinearLayout.getChildAt(i);
            view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (checkedId >= 0) {
                            BangWordView view1 = (BangWordView) autoExpandLinearLayout.getChildAt(checkedId);
                            view1.setChecked(false);
                        }
                        checkedId = position;
                        //显示翻译view
                        presenter.fanyi(view);

                    } else {
                        //隐藏翻译view

                    }
                }
            });

        }
    }


}
