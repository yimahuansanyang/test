package com.example.interceptinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by zhangdachun on 2017/12/22.
 * 拦截信息的广播接受者
 * Intent intent = new Intent(context,MainActivity.class);
 * <p>
 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 * <p>
 * context.startActivity(intent);
 */

public class InterceptInfoBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            Set<String> keys = bundle.keySet();

            //查看广播中包含的数据项

            for (String key : keys) {

                Log.i("广播中的数据项:", key);

            }

            //短信数据的获取都用通过一个叫做pdus的key获取

            Object[] arrayObjects = (Object[]) bundle.get("pdus");

            //短信的数据内容都是封装在SmsMessage对象中

            SmsMessage[] msg = new SmsMessage[arrayObjects.length];

            //循环处理收到的所有短信

            for (int i = 0; i < arrayObjects.length; i++) {

                //将每条短信数据赋值给SmsMessage对象

                msg[i] = SmsMessage.createFromPdu((byte[]) arrayObjects[i]);

                //获得发送短信的电话号码和短信内容

                String s = "手机号：" + msg[i].getOriginatingAddress() + "\n";

                s += "短信内容：" + msg[i].getDisplayMessageBody();

                //通过土司的方式把电话号码和内容显示出来
                Intent it = new Intent(context, MainActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it.putExtra("msg",s);
                context.startActivity(it);
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();

            }

            abortBroadcast();

        }


    }
}
