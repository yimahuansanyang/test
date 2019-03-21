package com.example.broadlinkdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhangdachun on 2017/12/22.
 */

public class CommomBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
        if (action=="com.example.broadlinkdemo.broadcast1"){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
