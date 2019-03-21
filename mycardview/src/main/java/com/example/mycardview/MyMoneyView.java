package com.example.mycardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zhangdachun on 2017/11/29.
 */

public class MyMoneyView extends LinearLayout {
    private Paint mPaint;
    //圆的半径
    private int radius = 8;
    //圆间的距离
    private int gap = 8;
    private Paint paint;

    public MyMoneyView(Context context) {
        super(context);
        init();
    }

    public MyMoneyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyMoneyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i <=getWidth()/(radius*2+gap*2); i++) {
            canvas.drawCircle((gap+radius)*(2*i-1),radius,radius,paint);

        }
    }
}
