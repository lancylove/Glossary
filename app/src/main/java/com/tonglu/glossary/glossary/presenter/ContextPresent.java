package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.view.GlossaryApplication;
import com.tonglu.glossary.glossary.view.viewInterface.ContextInterface;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/6.
 */
public class ContextPresent extends BasePresenter {
    ContextInterface view;
    ArrayList<ContextBean> contextBeans = new ArrayList<>();

    public ContextPresent(Context context, ContextInterface view) {
        super(context, view);
        this.view = view;
    }

    public void itemDelete(int position) {

    }

    public void reLoad() {

        ContextBean contextBean = new ContextBean();
        contextBean.contextName = "词汇表";
        contextBean.id = "1";
        contextBeans.add(contextBean);
        contextBean = new ContextBean();
        contextBean.contextName = "田园驿站";
        contextBean.id = "2";
        contextBeans.add(contextBean);

        view.setAdapterData(contextBeans);

    }

    public void saveContext(int position) {
        ContextBean contextBean = contextBeans.get(position);
        GlossaryApplication.getInstance().getSpUtil().setcurrentContext(contextBean.contextName);
        GlossaryApplication.getInstance().getSpUtil().setcurrentContextId(contextBean.id);
        view.startMain(contextBean);

    }
}
