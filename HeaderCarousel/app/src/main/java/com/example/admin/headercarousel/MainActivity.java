package com.example.admin.headercarousel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.headercarousellibrary.ImageBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> imageList;

    private int[] images = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};

    private ImageBanner imageBanner;

    private LinearLayout dotLinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBanner = (ImageBanner) findViewById(R.id.iamge_banner);
        dotLinearlayout = (LinearLayout) findViewById(R.id.dot_ll);
        initData();
        imageBanner.setDotLinearlayout(dotLinearlayout, R.layout.ad_bottom_item, R.id.ad_item_v,
                R.color.currentcolor, R.color.defaultcolor);
//        imageBanner.setDotLinearlayout(dotLinearlayout,R.layout.ad_bottom_item, R.id.ad_item_v,
//                R.drawable.dot_focused, R.drawable.dot_normal);
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
            //设置图片显示模式
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageList.add(imageView);
        }
    }

}
