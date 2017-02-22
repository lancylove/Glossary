package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.model.NoteBean;
import com.tonglu.glossary.glossary.view.viewInterface.NoteFragmentInterface;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public class NoteFragmentPresenter extends BasePresenter {
    NoteFragmentInterface view;
    ContextBean currentContextBean;
    private ArrayList<NoteBean> noteBeens = new ArrayList<>();

    public NoteFragmentPresenter(Context context, NoteFragmentInterface view) {
        super(context, view);
        this.view = view;

    }


    public void reLoad(ContextBean currentContextBean) {
        noteBeens.clear();
        this.currentContextBean = currentContextBean;
        NoteBean noteBean = new NoteBean();
        noteBean.creatTime = "2017/02/06 09:32";
        noteBean.content = "这是测试";
        noteBeens.add(noteBean);

        noteBean = new NoteBean();
        noteBean.creatTime = "2017/02/07 09:32";
        noteBean.content = "支持通过笔记来快速生成词汇表中的词条。设想的过程大致是：\n" +
                "\n" +
                "支持对笔记进行“分词”（笔记）。分词后，笔记以类似“大爆炸”的方式显示。可以支持点击其中的（单个？）分词结果（中文词语）进行翻译（中 -> 英）；\n" +
                "\n" +
                "分词后对所有分词结果进行去重，然后批量查询翻译结果进行显示？\n" +
                "\n" +
                "所有英文单词都是可以点击的，点击之后，进入上方的词条（Term）生成英文输入栏，相应的中文也进入中文输入栏。中英文输入栏的单词都可以修改。最后可以确认生成词条（词语／术语）。";
        noteBeens.add(noteBean);
        view.setAdapterData(noteBeens);

    }

    public void itemDelete(int position) {
    }


    public void itemClick(int position) {
        NoteBean noteBean = noteBeens.get(position);
        view.showContent(noteBean);

    }
}
