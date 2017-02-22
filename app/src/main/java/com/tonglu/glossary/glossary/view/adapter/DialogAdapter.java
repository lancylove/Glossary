package com.tonglu.glossary.glossary.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.TagBean;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/2/21.
 */

public class DialogAdapter extends BaseAdapter {
    ArrayList<TagBean> mData;
    private Context context;


    public DialogAdapter(Context context, ArrayList<TagBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setmData(ArrayList<TagBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dialog, null);
            holder.tagView = (TextView) convertView.findViewById(R.id.cb_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tagView.setText(mData.get(position).tagName);



        return convertView;
    }

    class ViewHolder {
        TextView tagView;
    }


}
