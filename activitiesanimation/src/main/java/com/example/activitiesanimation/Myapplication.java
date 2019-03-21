package com.example.activitiesanimation;

import android.app.Application;

import com.github.mmin18.layoutcast.LayoutCast;

/**
 * Created by zhangdachun on 2017/12/12.
 */

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            LayoutCast.init(this);
        }
    }
}
