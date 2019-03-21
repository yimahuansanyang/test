package com.example.interceptinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it = getIntent();
        String msg = it.getStringExtra("msg");
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText(msg);
        intent = new Intent(MainActivity.this, StopStrangerPhoneNumberSmsService.class);
        startService(this.intent);
    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }
}
