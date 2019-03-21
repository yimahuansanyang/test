package com.example.circlepointbadge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by zhangdachun on 2017/12/4.
 */

public class ListAdapter extends BaseAdapter {
    private final List<String> data;
    private final Context context;
    private final LayoutInflater ml;

    public ListAdapter(Context context, List<String> list) {
        this.data = list;
        this.context = context;

        this.ml = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ml.inflate(R.layout.list_layout, null);
        }
        TextView tv = view.findViewById(R.id.tv);
        ViscosityView viewById = view.findViewById(R.id.vv);
        tv.setText(data.get(i));
        return view;
    }
}
