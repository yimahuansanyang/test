package com.example.recyclerviewdeno;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivityTest extends AppCompatActivity implements OnItemClickListener {


    private RecyclerView ry_test;
    private ArrayList<Message> list;
    private RecyclerViewTestAdapter recyclerViewTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        initData();
        ry_test = (RecyclerView) findViewById(R.id.ry_test);
//        ry_test.setLayoutManager(new GridLayoutManager(this, 2));
//        ry_test.setLayoutManager(new LinearLayoutManager(this));
        ry_test.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        ry_test.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int pos = parent.getChildAdapterPosition(view);
                outRect.left = 3;
                outRect.bottom = 3;
                outRect.top = 3;
                outRect.right = 3;

            }
        });
        recyclerViewTestAdapter = new RecyclerViewTestAdapter(list);
        recyclerViewTestAdapter.setOnItemClickListener(this);
        ry_test.setAdapter(recyclerViewTestAdapter);
        setTouchListener();


    }

    private void setTouchListener() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//拖拽
                int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                recyclerViewTestAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                list.remove(viewHolder.getAdapterPosition());
                recyclerViewTestAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;

            }
        });
        itemTouchHelper.attachToRecyclerView(ry_test);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Message("Hensen", "下午1:22", "老板：哈哈哈", R.drawable.ic_history));
        list.add(new Message("流年不利", "早上10:31", "美女：呵呵哒", R.drawable.num23));
        list.add(new Message("1402", "下午1:55", "嘻嘻哈哈", R.drawable.food));
        list.add(new Message("Unstoppable", "下午4:32", "美美哒", R.drawable.ic_history));
        list.add(new Message("流年不利", "晚上7:22", "萌萌哒", R.drawable.fullstar));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.drawable.companylogo));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.drawable.num82));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.drawable.food));
    }


    @Override
    public void OnItemClick(int position) {
        Message message = list.get(position);
        Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
