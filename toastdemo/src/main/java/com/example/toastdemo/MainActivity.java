package com.example.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int touchCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        touchCount = 0;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();

    }

    public void finish() {
        //按2次返回键退出
        touchCount++;
        if (touchCount == 1) {
            //弹出自定义Toast
            ToastUtil();
        } else if (touchCount == 2) {
            super.finish();
        }
    }

    private void ToastUtil() {
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(View.inflate(this, R.layout.view_toast, null));
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setMargin(0, 0);
        toast.show();
    }
}
