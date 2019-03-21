package com.example.percentview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangdachun on 2017/12/14.
 */

public class PercentView extends View {

    private Paint mPaint;
    private RectF oval;
    private TextPaint textPaint;

    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        textPaint = myTextPaint();
        oval = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mode1 = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        int width = getWidth();
        int height = getHeight();
        float radius = width / 4;
        canvas.drawCircle(width / 2, width / 2, radius, mPaint);

        mPaint.setColor(Color.BLUE);
        oval.set(width / 2 - radius, width / 2 - radius, width / 2
                + radius, width / 2 + radius);//用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval, 270, 120, true, mPaint);  //根据进度画圆弧
        mPaint.setColor(Color.RED);
        canvas.drawArc(oval, 30, 90, true, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(oval, 120, 90, true, mPaint);
        String tt="红色";
        String t1="黄色";
        String t2="蓝色";

        canvas.drawText(tt,width/2,height/2+30,textPaint);
        canvas.drawText(t1,width/2-150,height/2-30,textPaint);
        canvas.drawText(t2,width/2+130,height/2-130,textPaint);
    }

    public TextPaint myTextPaint() {

        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
        int TEXT_SIZE = Math.round(25);
        textPaint.setTextSize(TEXT_SIZE);// 字体大小
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
        textPaint.setColor(Color.BLACK);// 采用的颜色
        return textPaint;

    }
}
