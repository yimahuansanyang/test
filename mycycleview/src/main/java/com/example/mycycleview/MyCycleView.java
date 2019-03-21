package com.example.mycycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangdachun on 2017/11/29.
 *圆形音乐旋转盘
 */

public class MyCycleView extends View {

    //view的宽和高
    int mHeight = 0;
    int mWidth = 0;
    //圆形图片
    Bitmap bitmap = null;
    //圆形图片的真实半径
    int radius = 0;
    //旋转动画的矩形
    Matrix matrix = new Matrix();
    //旋转动画的角度
    int degrees = 0;

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            matrix.postRotate(degrees++, radius / 2, radius / 2);
            //重绘
            invalidate();
            mHandler.postDelayed(runnable, 10);
        }
    };

    public MyCycleView(Context context) {
        super(context);
        init();
    }

    public MyCycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取圆形盘的图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m5);
        mHandler.postDelayed(runnable, 50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量的模式
        mWidth = measuredWidth(widthMeasureSpec);
        mHeight = measuredHeight(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    private int measuredHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode==MeasureSpec.EXACTLY){
            mHeight=size;
        }else {
            int value = getPaddingTop() + getPaddingBottom() + bitmap.getHeight();
            if (mode == MeasureSpec.AT_MOST) {
                //由图片和Padding决定高度，但是不能超过View的高
                mHeight = Math.min(value, size);
            }
        }
        return mHeight;
    }

    private int measuredWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode==MeasureSpec.EXACTLY){
            mWidth=size;
        }else {
            int value=getPaddingLeft()+getPaddingRight()+bitmap.getWidth();
            if (mode==MeasureSpec.AT_MOST){
                mWidth=Math.min(value,size);
            }

        }
        return mWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.concat(matrix);
        //真实的半径必须是View的宽高最小值
        radius = Math.min(mWidth, mHeight);
        //如果图片本身宽高太大，进行相应的缩放
        bitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        //画圆形图片
        canvas.drawBitmap(createCircleImage(bitmap, radius), 0, 0, null);
        matrix.reset();
    }

    private Bitmap createCircleImage(Bitmap bitmap, int radius) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        //产生一个同样大小的画布
        Canvas canvas = new Canvas(target);
        //首先绘制圆形
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        //使用SRC_IN模式显示后画图的交集处
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制图片，从（0，0）画
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return target;
    }
}
