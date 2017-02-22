package com.tonglu.glossary.glossary.view.viewInterface;

import com.tonglu.glossary.glossary.model.ContextBean;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/5.
 * 分词爆炸
 */
public interface ContextInterface extends BaseViewInterface {

    void setAdapterData(ArrayList<ContextBean> contextBeans);

    void startMain(ContextBean contextBean);
}
