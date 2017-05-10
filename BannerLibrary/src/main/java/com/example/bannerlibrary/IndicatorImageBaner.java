package com.example.bannerlibrary;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by zhangzhiqiang on 2017/5/10.
 */

public class IndicatorImageBaner extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private ImageBanner imageBanner;

    public IndicatorImageBaner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        if (imageBanner != null) {
            removeView(imageBanner);
        }

        imageBanner = new ImageBanner(getContext());
        imageBanner.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        addView(imageBanner);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
