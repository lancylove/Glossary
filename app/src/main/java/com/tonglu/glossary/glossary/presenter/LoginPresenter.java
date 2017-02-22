package com.tonglu.glossary.glossary.presenter;

import android.content.Context;

import com.tonglu.glossary.glossary.view.GlossaryApplication;
import com.tonglu.glossary.glossary.view.viewInterface.LoginViewInterface;

/**
 * Created by tonglu on 2017/2/4.
 */
public class LoginPresenter extends BasePresenter {

    private LoginViewInterface view;

    public LoginPresenter(Context context, LoginViewInterface view) {

        super(context, view);
        this.view = view;
    }

    public void requestLogin(String username, String password) {


        GlossaryApplication.getInstance().getSpUtil().setName("测试");

        String currentContext = GlossaryApplication.getInstance().getSpUtil().getcurrentContext();
        if (currentContext == null) {
            view.selectContext();
        } else {
            view.startMain();
        }


    }
}
