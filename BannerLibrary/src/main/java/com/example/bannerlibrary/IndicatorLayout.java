package com.example.bannerlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zhangzhiqiang on 2017/5/2.
 */

public class IndicatorLayout extends LinearLayout implements ViewPager.OnPageChangeListener {

    private int viewNumber;
    private Context mContext;
    private int defaultColor;
    private int currentColor;
    private int oldIndex;

    public IndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray arrray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorLayout);
        defaultColor = arrray.getColor(R.styleable.IndicatorLayout_defaultColor, Color.WHITE);
        currentColor = arrray.getColor(R.styleable.IndicatorLayout_currentColor, Color.RED);
        arrray.recycle();
    }

    public void setNumber(ViewPager viewPager, int number) {
        this.viewNumber = number;
        viewPager.setOnPageChangeListener(this);
        init();
    }

    private void init() {
        for (int i = 0; i < viewNumber; i++) {
            CircleView view = new CircleView(mContext);
            //设置宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            //设置margin
            params.setMargins(2, 0, 2, 0);
            view.setLayoutParams(params);
            //先全部设置默认颜色
            view.setCircleColor(defaultColor);
            this.addView(view);
        }
        //将第一个设置指示颜色
        CircleView firstView = (CircleView) getChildAt(0);
        firstView.setCircleColor(currentColor);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int curIndex = position % viewNumber;
        CircleView oldView = (CircleView) getChildAt(oldIndex);
        oldView.setCircleColor(defaultColor);
        CircleView newView = (CircleView) getChildAt(curIndex);
        newView.setCircleColor(currentColor);
        oldIndex = curIndex;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
