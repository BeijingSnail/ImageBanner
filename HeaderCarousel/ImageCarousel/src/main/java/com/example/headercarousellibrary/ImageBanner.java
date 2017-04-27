package com.example.headercarousellibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
     */
    private int scrollTime = 0;
    private Timer timer;
    private int oldIndex = 0;
    private int curIndex = 0;

    public ImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start(Activity mainActivity, List<View> imgList,
                      int scrollTime, LinearLayout ovalLayout, int ovalLayoutId,
                      int ovalLayoutItemId, int focusedId, int normalId) {
        mActivity = mainActivity;
        viewList = imgList;
        this.scrollTime = scrollTime;
        setOvalLayout(ovalLayout, ovalLayoutId, ovalLayoutItemId, focusedId,
                normalId);
        this.setAdapter(new MyPagerAdapter());
        if (scrollTime != 0 && imgList.size() > 1) {
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

    private void setOvalLayout(final LinearLayout ovalLayout, int ovalLayoutId,
                               final int ovalLayoutItemId, final int focusedId, final int normalId) {
        if (ovalLayout != null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            //移除所有子view不然小圆点会累加
            ovalLayout.removeAllViews();
            for (int i = 0; i < viewList.size(); i++) {
                ovalLayout.addView(inflater.inflate(ovalLayoutId, null));

            }
            ovalLayout.getChildAt(0).findViewById(ovalLayoutItemId)
                    .setBackgroundResource(focusedId);
            this.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int i) {
                    curIndex = i % viewList.size();
                    if (ovalLayoutItemId == 0 || ovalLayout.getChildAt(oldIndex) == null || ovalLayout.getChildAt(curIndex) == null) {
                        return;
                    }
                    ovalLayout.getChildAt(oldIndex).findViewById(ovalLayoutItemId)
                            .setBackgroundResource(normalId);
                    ovalLayout.getChildAt(curIndex).findViewById(ovalLayoutItemId)
                            .setBackgroundResource(focusedId);
                    oldIndex = curIndex;
                }

                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                public void onPageScrollStateChanged(int arg0) {
                }
            });
        }
    }

    public int getCurIndex() {
        return curIndex;
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

        public Object instantiateItem(View v, int i) {
            if (((ViewPager) v).getChildCount() == viewList.size()) {
                ((ViewPager) v)
                        .removeView(viewList.get(i % viewList.size()));
            }
            ((ViewPager) v).addView(viewList.get(i % viewList.size()), 0);
            return viewList.get(i % viewList.size());
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
