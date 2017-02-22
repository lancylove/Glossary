package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.model.WordBean;
import com.tonglu.glossary.glossary.view.viewInterface.GlossaryFragmentInterface;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public class GlossaryFragmentPresenter extends BasePresenter {
    GlossaryFragmentInterface view;
    private ArrayList<WordBean> wordBeens = new ArrayList<>();
    ContextBean currentContextBean;


    public GlossaryFragmentPresenter(Context context, GlossaryFragmentInterface view) {
        super(context, view);
        this.view = view;

    }

    public void reLoad(ContextBean currentContextBean) {
        //glossary	n（名词）	BoundedContext	词汇表	词汇表。我们的应用的名称
        wordBeens.clear();
        this.currentContextBean = currentContextBean;
        WordBean wordBean = new WordBean();
        wordBean.expression = "glossary";
        wordBean.chineseExpression = "词汇表";
        wordBean.partOfSpeechs = new String[]{"adj.","n."};
        wordBean.definition = "词汇表。我们的应用的名称";
        wordBean.tags = new String[]{"BoundedContext","context"};

        wordBeens.add(wordBean);

        wordBean = new WordBean();
        wordBean.expression = "word";
        wordBean.chineseExpression = "词汇";
        wordBean.partOfSpeechs = new String[]{"n."};
        wordBean.definition = "词汇、单词";
        wordBean.tags = new String[]{"BoundedContext","context"};

        wordBeens.add(wordBean);

        view.setAdapterData(wordBeens);

    }

    public void itemDelete(int position) {
    }


    public void itemClick(int position) {
        WordBean wordBean = wordBeens.get(position);
        view.showDetail(wordBean);


    }
}
