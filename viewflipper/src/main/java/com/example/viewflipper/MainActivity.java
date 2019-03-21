package com.example.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);
        vf.addView(View.inflate(this,R.layout.view_ad1,null));
        vf.addView(View.inflate(this,R.layout.view_ad2,null));
        vf.addView(View.inflate(this,R.layout.view_ad3,null));
        vf.addView(View.inflate(this,R.layout.view_ad4,null));
        vf.addView(View.inflate(this,R.layout.view_ad5,null));
    }
}
