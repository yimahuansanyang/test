package com.example.scratchcards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by zhangdachun on 2017/11/29.
 */

public class ScratchCards extends View {
    private Bitmap mBgBitmap, mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;
    private float mBgBitmapWidth;
    private float LastX, dx, sumX;
    private boolean isFinish = false;
    private int[] resId=new int[]{R.drawable.a3,R.drawable.a11
            ,R.drawable.e1_meitu_1,R.drawable.m1,R.drawable.m2,R.drawable.m3
            ,R.drawable.m4,R.drawable.m5,R.drawable.p1,R.drawable.p3,R.drawable.p2};


    public ScratchCards(Context context) {
        super(context);
        init();
    }


    public ScratchCards(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchCards(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化透明画笔
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化路径
        mPath = new Path();
        //初始化底层图片
        Random rand = new Random();
        int i = rand.nextInt(10);
        mBgBitmap = BitmapFactory.decodeResource(getResources(),
             R.drawable.e1_meitu_1);
        //获取底层宽度
        mBgBitmapWidth = mBgBitmap.getWidth();
        //创建顶层图片
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(),
                mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建顶层画布
        mCanvas = new Canvas(mFgBitmap);
        //顶层画布画上灰色
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                mCanvas.drawPath(mPath, mPaint);
                //重新绘制画面
                invalidate();

                dx = Math.abs(event.getX() - LastX);
                if (dx > 0) {
                    //监听左右滑
                    sumX += dx;
                }
                break;
            case MotionEvent.ACTION_UP:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            if (sumX > mBgBitmapWidth * 4) {
                                isFinish = true;
                                Thread.sleep(1000);
                                postInvalidate();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
        LastX = event.getX();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        if (!isFinish) {
            canvas.drawBitmap(mFgBitmap, 0, 0, null);
        }
    }
}
