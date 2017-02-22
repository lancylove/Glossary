package com.tonglu.glossary.glossary.view.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.view.viewInterface.SearchCodeDetailInterface;

//代码搜索详情页面
public class SearchCodeDetailActivity extends BaseActivity implements SearchCodeDetailInterface {

    public String url;
    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_code_detail);
        url = getIntent().getStringExtra("url");
        initView();

        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(url);
    }

    @Override
    public void initView() {
        super.initView();
        addView = (ImageView) findViewById(R.id.iv_right_btn);
        backView = (ImageView) findViewById(R.id.iv_back);
        findViewById(R.id.tv_title).setVisibility(View.GONE);
        addView.setVisibility(View.GONE);

        webView = (WebView) findViewById(R.id.webview_code);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
