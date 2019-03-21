package com.example.expresslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangdachun on 2017/12/20.
 */

public class KuaiDiAdapter extends BaseAdapter {
    private final Context context;
    private final List<KuiDi> data;
    private final LayoutInflater mInflater;
    private TextView tv_time;
    private TextView tv_content;

    public KuaiDiAdapter(Context context, List<KuiDi> list) {
        this.context = context;
        this.data = list;
        mInflater = LayoutInflater.from(context);

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

            view = mInflater.inflate(R.layout.time_item, null);
        }
        ViewHolder viewHolder = getViewHolder(view);
        KuiDi kuiDi = data.get(i);
        viewHolder.tv_content.setText(kuiDi.getContent());
        viewHolder.tv_time.setText(kuiDi.getTime());
        return view;
    }

    private class ViewHolder {
        private TextView tv_content, tv_time;

        ViewHolder(View view) {
            tv_content = view.findViewById(R.id.tv_content);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    private ViewHolder getViewHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }
}
