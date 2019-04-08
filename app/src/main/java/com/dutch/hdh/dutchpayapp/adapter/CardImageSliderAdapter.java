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
import com.dutch.hdh.dutchpayapp.databinding.CardSliderBinding;
import com.dutch.hdh.dutchpayapp.databinding.EventSliderBinding;

import java.util.List;

public class CardImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<Drawable> mImageArray;
    private String check;

    public CardImageSliderAdapter(Context mContext, List<Drawable> mImageArray) {
        this.mContext = mContext;
        this.mImageArray = mImageArray;
    }

    @Override
    public int getCount() {
        return mImageArray.size() + 1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        CardSliderBinding mCardSliderBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_slider, container, false);


        mCardSliderBinding.getRoot().setTag(position);

        if (position == mImageArray.size()) {
            return mCardSliderBinding.getRoot();
        }
        if (position == 0) {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.GONE);
        } else {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.VISIBLE);
        }
        mCardSliderBinding.ivRepresentativeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mCardSliderBinding.ivCardImage.setImageDrawable(mImageArray.get(position));
        container.addView(mCardSliderBinding.getRoot());
        return mCardSliderBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
