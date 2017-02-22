package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.model.TagBean;
import com.tonglu.glossary.glossary.view.viewInterface.BaseViewInterface;
import com.tonglu.glossary.glossary.view.viewInterface.WordsEditInterface;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/21.
 */

public class WordsEditPresenter extends BasePresenter {
    WordsEditInterface view;
    ArrayList<TagBean> tags = new ArrayList<>();

    public WordsEditPresenter(Context context, WordsEditInterface view) {
        super(context, view);
        this.view = view;
    }


    public void initTags() {

        tags.add(new TagBean("Aggregate"));
        tags.add(new TagBean("Entity"));
        tags.add(new TagBean("Value Object"));
        tags.add(new TagBean("Bounded Context"));
        tags.add(new TagBean("Property"));
        tags.add(new TagBean("Method"));
        tags.add(new TagBean("Trigger"));
        tags.add(new TagBean("State"));
        tags.add(new TagBean("Plural Form"));
        tags.add(new TagBean("Past Participle"));
        tags.add(new TagBean("Present Participle"));
        tags.add(new TagBean("Past Tense",true));


    }

    public ArrayList<TagBean> getTags() {
        return tags;
    }
}
