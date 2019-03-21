package com.example.circlepointbadge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initData();
//        lv = (ListView) findViewById(R.id.lv);
//        lv.setAdapter(new ListAdapter(this, list));
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }
    }
}
