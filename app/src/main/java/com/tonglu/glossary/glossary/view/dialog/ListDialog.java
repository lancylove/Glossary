package com.tonglu.glossary.glossary.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tonglu.glossary.glossary.R;

/**
 * Created by tonglu on 2016/4/19.
 */
public class ListDialog {

    private Context context;

    View dialogView;
    ListView listView;
    int MAXHeight = 480;
    AlertDialog alertDialog;

    public ListDialog(Context context) {
        this.context = context;
        dialogView = LayoutInflater.from(context).inflate(
                R.layout.dialog_linearlayout, null);
        listView = (ListView) dialogView.findViewById(R.id.common_list);

    }

    public void dismissDialog() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }




    public void showDialog(final AdapterView.OnItemClickListener onItemClickListener, BaseAdapter adapter) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setContentView(dialogView);
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.CENTER);


        setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

    }


    private void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);

        getTotalHeightofListView(listView);

        ViewGroup.LayoutParams lp = listView.getLayoutParams();
        if (lp.height > MAXHeight) {
            lp.height = MAXHeight;
        }
        listView.setLayoutParams(lp);
    }

    public ListView getListView() {
        return listView;
    }


    public void getTotalHeightofListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight();

        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int h = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        if (h > MAXHeight) {
            params.height = MAXHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }


    }


}
