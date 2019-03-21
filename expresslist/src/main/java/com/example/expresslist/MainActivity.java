package com.example.expresslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<KuiDi> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        initData();
        KuaiDiAdapter kuaiDiAdapter = new KuaiDiAdapter(this, list);
        lv.setAdapter(kuaiDiAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new KuiDi("2016-09-18 08:33:50", "您的订单开始处理"));
        list.add(new KuiDi("2016-09-18 08:40:23", "您的订单待配货"));
        list.add(new KuiDi("2016-09-18 08:51:33", "您的包裹已出库"));
        list.add(new KuiDi("2016-09-18 21:12:53", "【深圳市龙华函件中心】已收寄"));
        list.add(new KuiDi("2016-09-18 17:44:20", "到达【深圳】"));
        list.add(new KuiDi("2016-09-18 21:26:51", "离开【深圳市龙华函件中心】，下一站【深圳市】"));
        list.add(new KuiDi("2016-09-18 23:18:21", "到达【深圳市处理中心】"));
        list.add(new KuiDi("2016-09-19 01:14:30", "离开【深圳市处理中心】，下一站【广州市】"));
        list.add(new KuiDi("2016-09-19 04:42:11", "到达【广东省广州邮件处理中心】"));
    }
}
