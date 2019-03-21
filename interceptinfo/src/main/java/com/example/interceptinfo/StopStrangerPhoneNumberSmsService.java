package com.example.interceptinfo;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Filter;

/**
 * Created by zhangdachun on 2017/12/22.
 */

public class StopStrangerPhoneNumberSmsService extends Service {

    private InterceptInfoBroadCast interceptInfoBroadCast;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        interceptInfoBroadCast = new InterceptInfoBroadCast();
        IntentFilter filter=new IntentFilter();
        filter.setPriority(Integer.MAX_VALUE);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(interceptInfoBroadCast, filter);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(interceptInfoBroadCast);
        interceptInfoBroadCast=null;
        super.onDestroy();
    }
}
