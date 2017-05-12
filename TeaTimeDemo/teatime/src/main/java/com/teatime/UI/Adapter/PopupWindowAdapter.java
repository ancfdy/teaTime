package com.teatime.UI.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teatime.R;

/**
 * Created by HG on 2017/5/12.
 */

public class PopupWindowAdapter extends BaseAdapter {
    private Context context;
    private int[] st;
    private int[] al;

    public PopupWindowAdapter(Context context, int[] st, int[] al) {
        this.st = st;
        this.al = al;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (al != null)
            return Math.max(st.length, al.length);
        else
            return Math.max(st.length, 0);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.popupwindow_item, null);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.iv_popwin);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_popwin);
        if (al != null && position < al.length) {
            image.setBackgroundResource(al[position]);
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.INVISIBLE);
        }

        if (position < st.length) {
            tv.setText(st[position]);
            tv.setTextColor(Color.parseColor("#ff212121"));
        } else {
            tv.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
