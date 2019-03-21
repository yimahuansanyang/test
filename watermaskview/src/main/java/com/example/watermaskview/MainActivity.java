package com.example.watermaskview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView sour_pic;
    private ImageView wartermark_pic;
    private Bitmap waterBitmap;
    private Bitmap watermarkBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        sour_pic = (ImageView) findViewById(R.id.sour_pic);
        Bitmap sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.weixin_meitu_1);
        wartermark_pic = (ImageView) findViewById(R.id.wartermark_pic);
        sour_pic.setImageBitmap(sourBitmap);
        waterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg_8_meitu_5);
        Bitmap waterMaskCenter = ImageUtil.createWaterMaskCenter(sourBitmap, waterBitmap);
        watermarkBitmap = ImageUtil.createWaterMaskRightBottom(this, waterMaskCenter, waterBitmap, 0, 0);
        wartermark_pic.setImageBitmap(waterMaskCenter);

    }
}
