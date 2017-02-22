package com.tonglu.glossary.glossary.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roamer.slidelistview.SlideBaseAdapter;
import com.tonglu.glossary.glossary.R;
import com.tonglu.glossary.glossary.model.ContextBean;
import com.tonglu.glossary.glossary.view.viewInterface.OnItemDeleteClick;

import java.util.ArrayList;

/**
 * Created by tonglu on 2017/2/3.
 */
public class ContextListAdapter extends SlideBaseAdapter {

    private ArrayList<ContextBean> mData;
    private Context context;

    private OnItemDeleteClick deleteClick;

    public ContextListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void setmData(ArrayList<ContextBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void setDeleteClick(OnItemDeleteClick deleteClick) {
        this.deleteClick = deleteClick;
    }


    @Override
    public int getFrontViewId(int position) {
        return R.layout.item_context;
    }

    @Override
    public int getLeftBackViewId(int position) {
        return 0;
    }

    @Override
    public int getRightBackViewId(int position) {
        return R.layout.silde_right;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = createConvertView(position);
            viewHolder.contextView = (TextView) convertView.findViewById(R.id.tv_context);
            viewHolder.deleteView = (TextView) convertView.findViewById(R.id.tv_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ContextBean item = mData.get(position);
        viewHolder.contextView.setText(item.contextName);


        viewHolder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClick != null) {
                    deleteClick.itemDeleteClick(position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        public TextView contextView;
        public TextView deleteView;
    }

}
