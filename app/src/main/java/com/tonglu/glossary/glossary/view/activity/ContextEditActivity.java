package com.tonglu.glossary.glossary.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.view.viewInterface.ContextEditInterface;

//新增、编辑上下文页面
public class ContextEditActivity extends BaseActivity implements ContextEditInterface {

    private EditText contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_context);
        context = this;
        initView();
        initClick();
    }

    private void initClick() {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存上下文，然后退出
                

                finish();


            }
        });
    }

    @Override
    public void initView() {
        super.initView();

        titleView = (TextView) findViewById(R.id.tv_title);
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);

        titleView.setVisibility(View.GONE);
        addView.setVisibility(View.GONE);

        contextView = (EditText) findViewById(R.id.edit_context);

    }
}
