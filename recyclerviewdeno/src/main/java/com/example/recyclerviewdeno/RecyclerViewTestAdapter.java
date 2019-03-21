package com.example.recyclerviewdeno;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangdachun on 2017/12/21.
 */

public class RecyclerViewTestAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final List<Message> data;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewTestAdapter(List<Message> list) {

        this.data = list;
    }
    //构造方法

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //构造一个视图view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.iv_icon.setImageResource(data.get(position).getImg_id());
        myViewHolder.tv_message.setText(data.get(position).getMessage());
        myViewHolder.tv_time.setText(data.get(position).getTime());
        myViewHolder.tv_username.setText(data.get(position).getUsername());
        myViewHolder.itemView.setOnClickListener(this);
        myViewHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            int tag = (int) view.getTag();
            onItemClickListener.OnItemClick(tag);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_username, tv_time, tv_message;
        private ImageView iv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
