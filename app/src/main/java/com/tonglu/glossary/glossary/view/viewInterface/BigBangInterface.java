package com.tonglu.glossary.glossary.view.viewInterface;

import com.tonglu.glossary.glossary.model.BigBangBean;
import com.tonglu.glossary.glossary.model.FanyiBean;
import com.tonglu.glossary.glossary.view.widget.BangWordView;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/5.
 * 分词爆炸
 */
public interface BigBangInterface extends BaseViewInterface {

    void showBangView(ArrayList<BigBangBean> bigBangBeens);

    void showPopView(BangWordView v, FanyiBean result);
}
