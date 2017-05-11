package com.example.bannerlibrary;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by zhangzhiqiang on 2017/5/10.
 */

public class IndicatorImageBaner extends RelativeLayout {
    private ImageBanner imageBanner;
    private IndicatorLayout indicatorLayout;

    public IndicatorImageBaner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        if (imageBanner != null) {
            removeView(imageBanner);
        }

        if (indicatorLayout != null) {
            removeView(indicatorLayout);
        }

        imageBanner = new ImageBanner(getContext());
        imageBanner.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(imageBanner);

        indicatorLayout = new IndicatorLayout(getContext(),null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicatorLayout.setLayoutParams(lp);
        indicatorLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        indicatorLayout.setPadding(0, 0, 0, 10);
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
