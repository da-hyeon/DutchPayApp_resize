package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.EventSliderBinding;

import java.util.List;

public class EventImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<Drawable> mImageArray;

    public EventImageSliderAdapter(Context mContext, List<Drawable> mImageArray) {
        this.mContext = mContext;
        this.mImageArray = mImageArray;
    }

    @Override
    public int getCount() {
        return mImageArray.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        EventSliderBinding mEventSliderBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.event_slider, container, false);
        mEventSliderBinding.getRoot().setTag(position);

        mEventSliderBinding.ivEventImage.setImageDrawable(mImageArray.get(position));
        container.addView(mEventSliderBinding.getRoot());
        return mEventSliderBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
