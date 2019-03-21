package com.example.broadlinkdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_y;
    private Button btn_p;
    private Button btn_yb;
    private String Action = "com.example.broadlinkdemo.broadcast1";
    private String Action2 = "com.example.broadlinkdemo.broadcast2";
    private String Action3 = "com.example.broadlinkdemo.broadcast3";
    private CommomBroadcast commomBroadcast = new CommomBroadcast();
    private OrderBroadcast orderBroadcast = new OrderBroadcast();
    private AnysBroadcast anysBroadcast = new AnysBroadcast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_y = (Button) findViewById(R.id.btn_y);
        btn_p = (Button) findViewById(R.id.btn_p);
        btn_yb = (Button) findViewById(R.id.btn_yb);
        btn_y.setOnClickListener(this);
        btn_p.setOnClickListener(this);
        btn_yb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_y:
                intent.setAction(Action);
                intent.putExtra("msg", "这是普通广播");
                sendBroadcast(intent);
                break;
            case R.id.btn_p:
                intent.setAction(Action2);
                intent.putExtra("msg2", "有序广播");
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.btn_yb:
                intent.setAction(Action3);
                intent.putExtra("msg3","这是异步广播");
                sendStickyBroadcast(intent);
                IntentFilter filter3 = new IntentFilter(Action3);
                registerReceiver(anysBroadcast, filter3);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Action);
        registerReceiver(commomBroadcast, filter);
        IntentFilter filter2 = new IntentFilter(Action2);
        registerReceiver(orderBroadcast, filter2);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(commomBroadcast);
        unregisterReceiver(orderBroadcast);
        unregisterReceiver(anysBroadcast);
    }
}
