package com.example.whorlview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tt.whorlviewlibrary.WhorlView;

public class MainActivity extends AppCompatActivity {

    private WhorlView whorl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whorl2 = (WhorlView) findViewById(R.id.whorl2);
        whorl2.start();
    }
}
