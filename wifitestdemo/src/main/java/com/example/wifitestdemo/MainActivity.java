package com.example.wifitestdemo;

import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        StringBuffer sb = new StringBuffer();
        WifiAdmin wifiAdmin = new WifiAdmin(this);
        wifiAdmin.StartScan();
        String wifiInfo = wifiAdmin.GetWifiInfo();
        List<ScanResult> scanResults = wifiAdmin.GetWifiList();
        for (int i = 0; i < scanResults.size(); i++) {

            ScanResult scanResult = scanResults.get(i);
            String s = scanResult.toString();
            sb.append(s + "\n");
            tv_text.setText(sb.toString());
        }
    }
}
