package com.example.admin.headercarousel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.headercarousellibrary.ImageBanner;
import com.example.headercarousellibrary.IndicatorLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> imageList;

    private int[] images = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};

    private ImageBanner imageBanner;

    private IndicatorLayout indicatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBanner = (ImageBanner) findViewById(R.id.iamge_banner);
        indicatorLayout = (IndicatorLayout) findViewById(R.id.indicator_layout);
        initData();
        //设置滑动时间间隔，不设置时默认2秒
        imageBanner.setScrollTime(3000);
        //为ImageBanner设置指示器
        imageBanner.setDotLinearlayout(indicatorLayout);
        //开始轮播
        imageBanner.start(this, imageList);
    }


    /**
     * 这里使用本地图片代替网络数据
     */
    private void initData() {
        imageList = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageList.add(imageView);
        }
    }

}
