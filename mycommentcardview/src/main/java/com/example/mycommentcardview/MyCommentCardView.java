package com.example.mycommentcardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zhangdachun on 2017/12/1.
 */

public class MyCommentCardView extends LinearLayout {
    private int radius = 8;
    private int gap = 8;
    private int triAngleWidth = 40;

    private Paint mPaint;
    //返回字体的高度
    private float textViewHeight = dp2px(55, getContext());

    public MyCommentCardView(Context context) {
        super(context);
        init();
    }

    public MyCommentCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCommentCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画顶部的圆
        int count = getWidth() / (radius * 2 + gap * 2);
        for (int i = 0; i <= count; i++) {
            canvas.drawCircle((radius + gap) * (2 * i - 1), 0, radius, mPaint);
            canvas.drawCircle((radius + gap) * (2 * i - 1), getHeight(), radius, mPaint);

        }
        //画中间的圆
        int smallCircle = (getWidth() - (2 * triAngleWidth)) / (radius * 2 + gap * 2);
        for (int i = 0; i < smallCircle; i++) {
            canvas.drawCircle((gap + radius) * (2 * i - 1) + triAngleWidth, getHeight() - textViewHeight, radius, mPaint);
        }

        //画三角形形
        Path path = new Path();
        path.moveTo(0, getHeight() - textViewHeight - triAngleWidth / 2);
        path.lineTo(0, getHeight() - textViewHeight + triAngleWidth / 2);
        path.lineTo(triAngleWidth, getHeight() - textViewHeight);
        path.close();
        canvas.drawPath(path, mPaint);
        //第二个三角形
        path.reset();
        path.moveTo(getWidth(), getHeight() - textViewHeight - triAngleWidth / 2);
        path.lineTo(getWidth(), getHeight() - textViewHeight + triAngleWidth / 2);
        path.lineTo(getWidth() - triAngleWidth, getHeight() - textViewHeight);
        path.close();
        canvas.drawPath(path, mPaint);

    }

    public static int dp2px(float dp, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        // 4.1->4, 4.9->4
        int px = (int) (dp * density + 0.5f);// 加0.5可以四舍五入
        return px;
    }
}
