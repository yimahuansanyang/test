package com.example.recyclerviewdeno;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangdachun on 2017/12/21.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<Music> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    /**
     * 点击事件
     */
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter() {
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RecyclerAdapter(Context context, List<Music> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder = null;
        if (viewType == Music.TYPE.TYPE_GRID_THREE) {
            view = mInflater.inflate(R.layout.item_grid_three, null);
            mViewHolder = new GridThreeViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_GRID_TWO) {
            view = mInflater.inflate(R.layout.item_grid_two, null);
            mViewHolder = new GridTwoViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_LIST) {
            view = mInflater.inflate(R.layout.item_list, null);
            mViewHolder = new ListViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_TITLE) {
            view = mInflater.inflate(R.layout.item_title, null);
            mViewHolder = new TitleViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Music.TYPE.TYPE_GRID_THREE:
                GridThreeViewHolder gridThreeViewHolder = (GridThreeViewHolder) holder;
                gridThreeViewHolder.iv_icon.setImageResource(mList.get(position).imageId);
                gridThreeViewHolder.tv_content.setText(mList.get(position).title);
                gridThreeViewHolder.itemView.setOnClickListener(this);
                gridThreeViewHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gridTwoViewHolder = (GridTwoViewHolder) holder;
                gridTwoViewHolder.iv_icon.setImageResource(mList.get(position).imageId);
                gridTwoViewHolder.tv_content.setText(mList.get(position).title);
                gridTwoViewHolder.itemView.setOnClickListener(this);
                gridTwoViewHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_LIST:
                ListViewHolder listViewHolder = (ListViewHolder) holder;
                listViewHolder.iv_icon.setImageResource(mList.get(position).imageId);
                listViewHolder.tv_content.setText(mList.get(position).title);
                listViewHolder.itemView.setOnClickListener(this);
                listViewHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_TITLE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                titleViewHolder.tv_content.setText(mList.get(position).title);
                titleViewHolder.itemView.setOnClickListener(this);
                titleViewHolder.itemView.setTag(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            int position = (int) view.getTag();
            mOnItemClickListener.OnItemClick(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }
}
