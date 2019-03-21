package com.example.circlepointbadge;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by zhangdachun on 2017/12/4.
 */

public class ViscosityView  extends View {
    private Paint mPaint;
    //固定圆，并且初始化
    private PointF mFixedCircle = new PointF(150f, 150f);
    //固定圆的半径
    float mFixedRadius = 14f;
    //动圆 并且初始化
    private PointF mDragCircle = new PointF(80f, 80f);
    //动圆  半径
    float mDragRadius = 20f;
    //动圆两个焦点的坐标
    private PointF[] mDragPoints;
    //固定圆的两个焦点坐标
    private PointF[] mFixedPoints;
    //控制焦点
    private PointF mControlPoint;
    //获取状态栏的高度
    private int mStatusBarHeight;

    /**
     * 是否断开
     */
    private boolean isOutToRange = false;

    /**
     * 是否消失（是否可见）
     */
    private boolean isDisappear = false;

    /**
     * 两个圆最远的距离
     */
    float farestDistance = 100f;

    String text = "";

    /**
     * 设置数字
     * @param num
     */
    public void setNumber(int num) {
        text = String.valueOf(num);
    }

    /**
     * 初始化圆的圆心坐标
     * @param x
     * @param y
     */
    public void initCenter(float x, float y) {
        mDragCircle = new PointF(x, y);
        mFixedCircle = new PointF(x, y);
        mControlPoint = new PointF(x, y);
        invalidate();
    }

    public void setOnDisappearListener(OnDisappearListener mListener) {
        this.mListener = mListener;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.mStatusBarHeight = statusBarHeight;
    }

    public OnDisappearListener getOnDisappearListener() {
        return mListener;
    }

    interface OnDisappearListener {
        void onDisappear(PointF mDragCenter);
        void onReset(boolean isOutOfRange);
    }

    private OnDisappearListener mListener;

    /**
     * 清除
     */
    private void disappeared() {
        isDisappear = true;
        invalidate();

        if (mListener != null) {
            mListener.onDisappear(mDragCircle);
        }
    }

    public ViscosityView(Context context) {
        this(context, null);
    }

    public ViscosityView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViscosityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 创建画笔
         */
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /**
         * 设置画笔的颜色
         */
        mPaint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //保持当前画布的状态
        canvas.save();
        //移动画布
        canvas.translate(0, -mStatusBarHeight);
        //根据两个圆的圆心的距离获取固定圆的半径
        float distance = getTempFiexdCircle();

        //计算连接部分
        //1、获取直线与圆的焦点
        float yOffset = mFixedCircle.y - mDragCircle.y;
        float xOffset = mFixedCircle.x - mDragCircle.x;

        //获取斜率
        Double lineK = null;
        if (xOffset != 0) {
            lineK = (double) yOffset / xOffset;
        }
        //通过几何工具获取焦点坐标
        this.mFixedPoints = GeometryUtil.getIntersectionPoints(mFixedCircle, distance, lineK);
        this.mDragPoints = GeometryUtil.getIntersectionPoints(mDragCircle, mDragRadius, lineK);
        //2、获取控制点坐标
        this.mControlPoint = GeometryUtil.getMiddlePoint(mDragCircle, mFixedCircle);

        if (!isDisappear) {
            //画拖拽圆 动圆
            //canvas.drawCircle(80f,80f,20f,mPaint);
            canvas.drawCircle(mDragCircle.x, mDragCircle.y, mDragRadius, mPaint);

            if (!isOutToRange) {
                //画一个固定圆
                //canvas.drawCircle(150f,150f,14f,mPaint);
                canvas.drawCircle(mFixedCircle.x, mFixedCircle.y, distance, mPaint);
                //画连接部分  这个是用的那个贝塞尔曲线绘制的连接部分
                Path path = new Path();
                //跳到某个点1
                path.moveTo(mFixedPoints[0].x, mFixedPoints[0].y);
                //画曲线 1--->2
                path.quadTo(mControlPoint.x, mControlPoint.y, mDragPoints[0].x, mDragPoints[0].y);
                //画直线2---->3
                path.lineTo(mDragPoints[1].x, mDragPoints[1].y);
                //画曲线3---->4
                path.quadTo(mControlPoint.x, mControlPoint.y, mFixedPoints[1].x, mFixedPoints[1].y);

                path.close();
                canvas.drawPath(path, mPaint);
            }
        }
        //恢复画布
        canvas.restore();
    }

    /**
     * 获取临时的固定圆的半径
     * @return
     */
    private float getTempFiexdCircle() {
        //获取到两个圆心之间的距离
        float instance = GeometryUtil.getDistanceBetween2Points(mDragCircle, mFixedCircle);
        //这个是在两个圆之间的实际距离和我们定义的距离之间取得最小值
        instance = Math.min(instance, farestDistance);
        //0.0f--->1.0f>>>>>1.0f---》0.0f
        float percent = instance / farestDistance;
        //估值器
        return evaluate(percent, mFixedRadius, mFixedRadius * 0.2);

    }

    /**
     * 估值器
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }


    /**
     * 重写这个方法，让小球动起来
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = 0;
        float y = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取到按下的时候的坐标(因为我们已经把画布往上移动了状态栏的高度了，或者是我们在这里做判断)
                x = event.getRawX();
                y = event.getRawY();
                isDisappear = false;
                isOutToRange = false;
                //更新动圆的坐标
                updataDragCircle(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的时候获取坐标
                x = event.getRawX();
                y = event.getRawY();
                //更新动圆的坐标
                updataDragCircle(x, y);

                //处理断开
                float distance = GeometryUtil.getDistanceBetween2Points(mDragCircle, mFixedCircle);
                if (distance > farestDistance) {  //如果获取到的距离大于我们定义的最大的距离
                    isOutToRange = true; //断开设置为true
                    invalidate(); //重绘
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isOutToRange) { //如果是断开
                    isOutToRange = false; //设置为false
                    //处理断开
                    float d = GeometryUtil.getDistanceBetween2Points(mDragCircle, mFixedCircle);
                    if (d > farestDistance) {
                        // * a、拖拽超出范围，断开-->松手-->消失
                        //松手还没有放回去
                        //isDisappear = true;
                        disappeared();
                        //重绘一下
                        invalidate();
                    } else {
                        //    * b、拖拽超出范围，断开---->放回去了--->恢复
                        updataDragCircle(mFixedCircle.x, mFixedCircle.y);
                        isDisappear = false;
                        if (mListener != null)
                            mListener.onReset(isOutToRange);
                    }
                } else {
                    final PointF tempDragCircle = new PointF(mDragCircle.x, mDragCircle.y);
                    //    * c、拖拽没有超出范围，断开--->恢复
                    final ValueAnimator mAnim = ValueAnimator.ofFloat(1.0f);
                    mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float percent = mAnim.getAnimatedFraction();
                            PointF p = GeometryUtil.getPointByPercent(tempDragCircle, mFixedCircle, percent);
                            updataDragCircle(p.x, p.y);
                        }
                    });
                    //差之器
                    mAnim.setInterpolator(new OvershootInterpolator(4));
                    mAnim.setDuration(500);
                    mAnim.start();
                }
                break;
            default:
                isOutToRange = false;
                break;
        }
        return true;
    }

    /**
     * 更新拖拽圆的圆心坐标
     * @param rawX
     * @param rawY
     */
    private void updataDragCircle(float rawX, float rawY) {
        //更新的坐标
        mDragCircle.set(rawX, rawY);
        invalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取状态栏的高度
        mStatusBarHeight = Utils.getStatusBarHeight(this);

    }

}
