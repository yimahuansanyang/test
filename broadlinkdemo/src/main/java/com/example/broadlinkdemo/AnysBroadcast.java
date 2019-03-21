package com.example.broadlinkdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhangdachun on 2017/12/22.
 */

public class AnysBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg3");

            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
