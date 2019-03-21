package com.example.broadlinkdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhangdachun on 2017/12/22.
 */

public class OrderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg2 = intent.getStringExtra("msg2");
        if (action=="com.example.broadlinkdemo.broadcast2"){
            Toast.makeText(context,msg2,Toast.LENGTH_SHORT).show();
        }
    }
}
