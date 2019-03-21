package com.example.mycardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by zhangdachun on 2017/11/29.
 */

public class MyCardView extends LinearLayout {

    private Paint mPaint;
    //圆的半径
    private int radius = 8;
    //圆间的距离
    private int gap = 8;

    public MyCardView(Context context) {
        super(context);
        init();
    }

    public MyCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆的个数
        Log.d("ddd","票的宽度=="+getWidth());
        int count = getWidth() / (radius * 2 + gap * 2);
        for (int i = 0; i <= count; i++) {
            canvas.drawCircle((gap+radius)*(2*i-1),0,radius,mPaint);
            canvas.drawCircle((gap+radius)*(2*i-1),getHeight(),radius,mPaint);
        }

    }
}
