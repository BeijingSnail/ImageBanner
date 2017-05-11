package com.example.bannerlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by zhangzhiqiang on 2017/5/10.
 */

public class IndicatorImageBanner extends RelativeLayout {
    private ImageBanner imageBanner;
    private IndicatorLayout indicatorLayout;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    //Indicator位置
    private int gravity;
    //Indicator透明度
    private float alpha;
    private int delay;

    public IndicatorImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arrray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorImageBanner);
        paddingLeft = (int) arrray.getDimension(R.styleable.IndicatorImageBanner_indicator_paddingLeft, 0);
        paddingRight = (int) arrray.getDimension(R.styleable.IndicatorImageBanner_indicator_paddingRight, 0);
        paddingTop = (int) arrray.getDimension(R.styleable.IndicatorImageBanner_indicator_paddingTop, 0);
        paddingBottom = (int) arrray.getDimension(R.styleable.IndicatorImageBanner_indicator_paddingBottom, 0);
        gravity = arrray.getInteger(R.styleable.IndicatorImageBanner_indicator_gravity, 1);
        delay = arrray.getInteger(R.styleable.IndicatorImageBanner_indicator_delay, 2000);
        alpha = arrray.getFloat(R.styleable.IndicatorImageBanner_indicator_alpha, 1);
        arrray.recycle();
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (imageBanner != null) {
            removeView(imageBanner);
        }
        if (indicatorLayout != null) {
            removeView(indicatorLayout);
        }
        imageBanner = new ImageBanner(getContext());
        imageBanner.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        imageBanner.setScrollTime(delay);
        addView(imageBanner);

        indicatorLayout = new IndicatorLayout(getContext(), attrs);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicatorLayout.setLayoutParams(lp);
        indicatorLayout.setGravity(gravity);
        indicatorLayout.setAlpha(alpha);
        indicatorLayout.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        addView(indicatorLayout);

        imageBanner.setDotLinearlayout(indicatorLayout);
    }

    /**
     * @param scrollTime 滑动间隔时间，单位毫秒
     */
    public void setScrollTime(int scrollTime) {
        imageBanner.setScrollTime(scrollTime);
    }

    public void start(Activity mainActivity, List<View> imgList) {
        imageBanner.start(mainActivity, imgList);
    }

    public void setBannerItemClickListener(OnItemClickListener itemClickListener) {
        imageBanner.setBannerItemClickListener(itemClickListener);
    }

}
