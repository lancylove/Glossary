package com.tonglu.glossary.glossary.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonglu.glossary.glossary.utils.Constant;
import com.tonglu.glossary.glossary.view.dialog.MyProgressDialog;

/**
 * Created by tonglu on 2017/2/3.
 */
public class BaseActivity extends AppCompatActivity {
    public Context context;
    protected ProgressDialog dialog;
    public TextView titleView;
    public ImageView addView;
    public ImageView backView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        context = this;
    }

    public void initView() {

    }


    public void showDialog(int id, boolean... cancle) {

        showDialog(getString(id), cancle);
    }

    public void showDialog(String str, boolean... cancle) {
        boolean iscancle = true;
        if (cancle != null && cancle.length > 0) {
            iscancle = cancle[0];
        }
        try {

            if (dialog != null && dialog.isShowing()) {
                return;
            }
            if (dialog == null)
                dialog = MyProgressDialog.createProgressDialog(this, Constant.Timeout
                        , new MyProgressDialog.OnTimeOutListener() {

                            @Override
                            public void onTimeOut(ProgressDialog dialog) {
                                try {
                                    dialog.dismiss();
                                } catch (Exception e) {

                                }
                            }

                        });


            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(iscancle);
            dialog.setMessage(str);

            dialog.show();
        } catch (Exception e) {
        }
    }


    public void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
    }
}
