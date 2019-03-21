package com.example.drawtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangdachun on 2017/12/14.
 */

public class DrawTextView extends View {
    private Paint textPaint;
    private Paint pathPaint;
    private Path mPath;
    private String[] pathEffectName = {
            "默认", "CornerPathEffect", "DashPathEffect", "PathDashPathEffect",
            "SumPathEffect", "DiscretePathEffect", "ComposePathEffect"
    };
    private PathEffect[] mPathEffect;


    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//文字画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);
        //路径画笔
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.parseColor("#FF4081"));
        pathPaint.setStrokeWidth(5F);
        pathPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        //设置起点
        mPath.moveTo(0, 0);
        //路径连接的点
        for (int i = 1; i < 37; i++) {
            mPath.lineTo(i * 30, (float) (Math.random() * 100));
        }
        //初始化PathEffect
        mPathEffect = new PathEffect[7];
        mPathEffect[0] = new PathEffect();//默认
        mPathEffect[1] = new CornerPathEffect(10f);
        mPathEffect[2] = new DashPathEffect(new float[]{10f, 5f, 20f, 15f}, 10);
        mPathEffect[3] = new PathDashPathEffect(new Path(), 10, 10f, PathDashPathEffect.Style.ROTATE);
        mPathEffect[4] = new SumPathEffect(mPathEffect[1], mPathEffect[2]);
        mPathEffect[5] = new DiscretePathEffect(5f, 10f);
        mPathEffect[6] = new ComposePathEffect(mPathEffect[3], mPathEffect[5]);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mPathEffect.length; i++) {
            pathPaint.setPathEffect(mPathEffect[i]);
            canvas.drawPath(mPath, pathPaint);
            canvas.drawText(pathEffectName[i], 0, 130, textPaint);//每个画布的最上面，就是y轴的0点
            // 每绘制一条将画布向下平移180个像素
            canvas.translate(0, 180);//控件的高度要足够大才能平移
        }
        invalidate();//绘制刷新

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (wSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize, 300);
        }
    }


}
