package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.CardSliderBinding;

import java.util.ArrayList;

public class CardImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<CardRegisterList.CardRegisterListResult> mCardRegisterListResultArrayList;
    private String check;

    public CardImageSliderAdapter(Context mContext, ArrayList<CardRegisterList.CardRegisterListResult> mImageArray) {
        this.mContext = mContext;
        this.mCardRegisterListResultArrayList = mImageArray;
    }

    @Override
    public int getCount() {
        return mCardRegisterListResultArrayList.size() + 1;
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

        if (position == mCardRegisterListResultArrayList.size()) {
            return mCardSliderBinding.getRoot();
        }
        /**
         * 대표카드 정보가 없어서
         * 무조건 첫번째가 대표카드로 처리
         *
         * */
        if ("0".equals(mCardRegisterListResultArrayList.get(position).getCard_Choice())) {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.GONE);
        } else {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.VISIBLE);
        }
        mCardSliderBinding.ivRepresentativeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        mCardSliderBinding.ivCardImage.setImageDrawable(mCardRegisterListResultArrayList.get(position));
        container.addView(mCardSliderBinding.getRoot());
        return mCardSliderBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
