package com.tonglu.glossary.glossary.view.viewInterface;

import com.tonglu.glossary.glossary.model.CodeSearchBean;

/**
 * Created by tonglu on 2017/2/5.
 * 分词爆炸
 */
public interface SearchCodeInterface extends BaseViewInterface {

    void setAdapterData(CodeSearchBean result);
}
