package com.example.recyclerviewdeno;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by zhangdachun on 2017/12/21.
 */

public class RectImageView extends AppCompatImageView {
    public RectImageView(Context context) {
        super(context);
    }

    public RectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 设置大小为宽度的三分之二
        int halfWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width / 3 * 2, widthMode);
        super.onMeasure(widthMeasureSpec, halfWidthMeasureSpec);
    }
}
