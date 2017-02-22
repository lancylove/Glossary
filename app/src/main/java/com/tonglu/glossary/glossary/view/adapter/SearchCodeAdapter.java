package com.tonglu.glossary.glossary.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.CodeSearchItemBean;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/2/17.
 */

public class SearchCodeAdapter extends BaseAdapter {

    private String matchterm;
    private ArrayList<CodeSearchItemBean> mData;
    private Context context;

    public void setMatchterm(String matchterm) {
        this.matchterm = matchterm;
    }

    public void setmData(ArrayList<CodeSearchItemBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public SearchCodeAdapter(Context context) {
        this.context = context;
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

        ViewHolder holder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_code, null);
            holder = new ViewHolder();

            holder.contentView = (TextView) convertView.findViewById(R.id.tv_content);
            holder.countView = (TextView) convertView.findViewById(R.id.tv_count);
            holder.languageView = (TextView) convertView.findViewById(R.id.tv_language);
            holder.searchView = (TextView) convertView.findViewById(R.id.tv_search);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CodeSearchItemBean itemBean = mData.get(position);

        holder.contentView.setText(matchterm);
        holder.countView.setText("Codes "+itemBean.linescount);
        holder.languageView.setText(itemBean.language);
        holder.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    class ViewHolder {
        public TextView contentView;
        public TextView countView;
        public TextView languageView;
        public TextView searchView;
    }
}
