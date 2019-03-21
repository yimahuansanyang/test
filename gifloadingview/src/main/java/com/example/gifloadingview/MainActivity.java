package com.example.gifloadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.roger.gifloadinglibrary.GifLoadingView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int[] IDS = {
            R.drawable.num0, R.drawable.num1,
            R.drawable.num7, R.drawable.num10, R.drawable.num11,
            R.drawable.num30, R.drawable.num31, R.drawable.num32, R.drawable.num33,
            R.drawable.num42, R.drawable.num43, R.drawable.num47, R.drawable.num49,
            R.drawable.num65, R.drawable.num66, R.drawable.num67, R.drawable.num68, R.drawable.num69,
            R.drawable.num70, R.drawable.num71, R.drawable.num72, R.drawable.num73, R.drawable.num74,
            R.drawable.num75


    };
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private GifLoadingView mGifLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(new HomeAdapter());
        mGifLoadingView = new GifLoadingView();
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < IDS.length; i++) {
            mDatas.add("GifLoadingView : " + i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(
                    LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.ImageButton.setText(mDatas.get(position));
            holder.ImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGifLoadingView.setImageResource(IDS[position]);
                    mGifLoadingView.show(getFragmentManager(), "");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            Button ImageButton;

            public MyViewHolder(View view) {
                super(view);
                ImageButton = (Button) view.findViewById(R.id.mButton);
            }
        }
    }
}
