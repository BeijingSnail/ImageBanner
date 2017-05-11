package com.example.bannerlibrary.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.bannerlibrary.OnItemClickListener;

import java.util.List;

/**
 * Created by zhangzhiqiang on 2017/5/10.
 */

public class BannerAdapter extends PagerAdapter {
    private String TAG = BannerAdapter.class.getName();
    private List bannerList;
    private OnItemClickListener bannerItemClickListener;

    public BannerAdapter(List list) {
        this.bannerList = list;
    }

    public void setBannerItemClickListener(OnItemClickListener bannerItemClickListener) {
        this.bannerItemClickListener = bannerItemClickListener;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public int getCount() {
        if (bannerList.size() == 1) {
            return bannerList.size();
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = (View) bannerList.get(position % bannerList.size());
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bannerItemClickListener != null) {
                    bannerItemClickListener.onItemClick(position % bannerList.size());
                } else {
                    Log.e(TAG, "bannerItemClickListener is null");
                }
            }
        });

        return bannerList.get(position % bannerList.size());
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
