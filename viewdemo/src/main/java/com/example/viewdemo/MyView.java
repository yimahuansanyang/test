package com.example.viewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zhangdachun on 2017/12/13.
 */

public class MyView  extends ViewGroup{


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (getChildCount()>0){
            View childAt = getChildAt(0);
            childAt.layout(0,0,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount()>0){
            View childAt = getChildAt(0);
           measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }

    }
}
