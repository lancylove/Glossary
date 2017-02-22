package com.tonglu.glossary.glossary.view.viewInterface;

import com.tonglu.glossary.glossary.model.NoteBean;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public interface NoteFragmentInterface extends BaseViewInterface {

    void setAdapterData(ArrayList<NoteBean> noteBeens);

    void showContent(NoteBean noteBean);
}
