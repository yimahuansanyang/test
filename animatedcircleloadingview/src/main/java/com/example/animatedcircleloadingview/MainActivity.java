package com.example.animatedcircleloadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

public class MainActivity extends AppCompatActivity {

    private AnimatedCircleLoadingView aclv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aclv = (AnimatedCircleLoadingView) findViewById(R.id.aclv);
        aclv.startDeterminate();
        aclv.setPercent(50);
        aclv.startIndeterminate();
        aclv.stopFailure();
    }
}
