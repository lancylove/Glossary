package com.tonglu.glossary.glossary.view.viewInterface;

import com.tonglu.glossary.glossary.model.WordBean;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public interface GlossaryFragmentInterface extends BaseViewInterface {

    void setAdapterData(ArrayList<WordBean> wordBeens);

    void showDetail(WordBean wordBean);
}
