package com.example.shapeimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.github.siyamed.shapeimageview.mask.PorterImageView;

/**
 * Created by zhangdachun on 2017/12/4.
 */

public class MyPorterShapeImageView extends PorterImageView {
    public MyPorterShapeImageView(Context context) {
        super(context);
    }

    public MyPorterShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPorterShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void paintMaskCanvas(Canvas maskCanvas, Paint maskPaint, int width, int height) {

    }
}
