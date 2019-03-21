package com.example.elasticdownloadview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import is.arontibo.library.ElasticDownloadView;

public class MainActivity extends AppCompatActivity {
    private int i = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ElasticDownloadView edl = (ElasticDownloadView) findViewById(R.id.edl);
        edl.startIntro();
        if (i == 100) {
            edl.setProgress(i);
            edl.success();
        } else {
            edl.setProgress(i);
            edl.fail();
        }


    }
}
