package com.example.brokenview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private BrokenView brokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        brokenView = BrokenView.add2Window(this);
        BrokenTouchListener build = new BrokenTouchListener.Builder(brokenView).build();
        iv.setOnTouchListener(build);
    }
}
