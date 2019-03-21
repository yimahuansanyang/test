package com.example.horseracelamp;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MarqueeTextView tv_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_page = (MarqueeTextView) findViewById(R.id.tv_page);
        String page = getString(R.string.page,5,3);
//        tv_page.setText("真它大爷的无聊");
        int start = page.indexOf("第", 2);
        int end=page.length();
        SpannableString span = new SpannableString(page);
        span.setSpan(new AbsoluteSizeSpan(35), 0, start, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(25), start, end, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(MainActivity.this, EmptyActivity.class));
            }
        }, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_page.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
//        tv_page.setTypeface(Typeface.DEFAULT_BOLD);

        tv_page.setText(span);
    }
}
