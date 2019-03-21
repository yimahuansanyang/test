package com.example.ahrsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.billhsu.ahrsview.AHRSView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AHRSView ahrsView = (AHRSView) findViewById(R.id.AHRSView);
        ahrsView.setRoll(0);
        ahrsView.setPitch(0);
        ahrsView.setYaw(0);
    }
}
