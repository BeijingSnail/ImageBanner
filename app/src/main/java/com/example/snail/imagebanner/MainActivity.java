package com.example.snail.imagebanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bannerlibrary.IndicatorImageBanner;
import com.example.bannerlibrary.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> imageList;

    private int[] images = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};

    private IndicatorImageBanner indicatorImageBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicatorImageBanner = (IndicatorImageBanner) findViewById(R.id.indicator_iamge_banner);
        initData();
        //开始轮播
        indicatorImageBanner.start(this, imageList);
        indicatorImageBanner.setBannerItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "点击第" + position + "张", Toast.LENGTH_SHORT).show();
            }
        });
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
