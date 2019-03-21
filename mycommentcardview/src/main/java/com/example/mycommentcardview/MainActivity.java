package com.example.mycommentcardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_c = (TextView) findViewById(R.id.text_c);
        text_c.setText("独立寒秋，湘江北去，橘子洲头。\n" +
                "看万山红遍，层林尽染；漫江碧透，百舸争流。\n" +
                "鹰击长空，鱼翔浅底，万类霜天竞自由。\n" +
                "怅寥廓，问苍茫大地，谁主沉浮？\n" +
                "携来百侣曾游，忆往昔峥嵘岁月稠。\n" +
                "恰同学少年，风华正茂；书生意气，挥斥方遒。\n" +
                "指点江山，激扬文字，粪土当年万户侯。 \n" +
                "曾记否，到中流击水，浪遏飞舟？");
    }
}
