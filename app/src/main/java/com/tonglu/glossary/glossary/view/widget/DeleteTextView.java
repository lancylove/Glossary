package com.tonglu.glossary.glossary.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonglu.glossary.glossary.R;

/**
 * Created by Administrator on 2017/2/17.
 */

public class DeleteTextView extends TextView {
    public DeleteTextView(Context context, String text) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.item_edit));

        setLayoutParams(params);
        setText(text);
        setTextColor(getResources().getColor(R.color.textcolor_gray_3));
        setTextSize(14);
        setGravity(Gravity.BOTTOM);
        setPadding(15, 5, 15, 5);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_delete);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        setCompoundDrawables(null, null, drawable, null);
    }
}
