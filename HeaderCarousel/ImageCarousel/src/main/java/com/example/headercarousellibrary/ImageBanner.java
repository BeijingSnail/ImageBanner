package com.example.headercarousellibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangzhiqiagn on 2017/4/27.
 */

public class ImageBanner extends ViewPager {

    private Activity mActivity;

    /**
     * 装有图片控件的集合
     */
    private List<View> viewList;

    /**
     * 滑动间隔时间
     * 默认2秒
     */
    private int scrollTime = 2000;
    private Timer timer;
    private IndicatorLayout mIndicatorLayout;

    /**
     * @param scrollTime 滑动间隔时间，单位毫秒
     */
    public void setScrollTime(int scrollTime) {
        this.scrollTime = scrollTime;
    }

    /**
     * @param indicatorLayout 设置指示器布局
     */
    public void setDotLinearlayout(IndicatorLayout indicatorLayout) {
        this.mIndicatorLayout = indicatorLayout;
    }

    public ImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start(Activity mainActivity, List<View> imgList) {

        this.mActivity = mainActivity;

        this.viewList = imgList;

        if (mIndicatorLayout != null) {
            setOvalLayout(mIndicatorLayout);
        }

        this.setAdapter(new MyPagerAdapter());

        if (scrollTime != 0 && imgList.size() > 1) {

            //设置viewpage的滑动速度
            new FixedSpeedScroller(mActivity).setDuration(this, 700);

            startTimer();

            this.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startTimer();
                    } else {
                        stopTimer();
                    }
                    return false;
                }
            });
        }
        if (viewList.size() > 1) {
            this.setCurrentItem((Integer.MAX_VALUE / 2)
                    - (Integer.MAX_VALUE / 2) % viewList.size());
        }
    }

    private void setOvalLayout(final IndicatorLayout mIndicatorLayout) {
        if (mIndicatorLayout != null) {
            //移除所有子view防止小圆点累加
            mIndicatorLayout.removeAllViews();
            //传入viewoPager并设置指示器小圆点数量
            mIndicatorLayout.setNumber(this, viewList.size());
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void startTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    mActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            ImageBanner.this.setCurrentItem(ImageBanner.this
                                    .getCurrentItem() + 1);
                        }
                    });
                }
            }, scrollTime, scrollTime);
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        public int getCount() {
            if (viewList.size() == 1) {
                return viewList.size();
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position % viewList.size()));
            return viewList.get(position % viewList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewGroup) container).removeView((View) object);

        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        public Parcelable saveState() {
            return null;
        }

    }
}
