package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.CardSliderBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardContract;

import java.util.ArrayList;

public class CardImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<CardRegisterList.CardRegisterListResult> mCardRegisterListResultArrayList;
    private String check;
    private MyCardContract.View mView;

    public CardImageSliderAdapter(Context mContext, ArrayList<CardRegisterList.CardRegisterListResult> cardRegisterListResultArrayList, MyCardContract.View view) {
        this.mContext = mContext;
        this.mCardRegisterListResultArrayList = cardRegisterListResultArrayList;
        this.mView = view;
    }


    /**
     * 대표카드 정보 가져오기
     */

    public CardRegisterList.CardRegisterListResult getRepresentativeCard() {
        if (mCardRegisterListResultArrayList.size() != 0) {
            for (int i = 0; i < mCardRegisterListResultArrayList.size(); i++) {
                if ("0".equals(mCardRegisterListResultArrayList.get(i).getCard_Choice())) {
                    return mCardRegisterListResultArrayList.get(i);
                }
            }
        }
        return null;
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
         * cardChoice 0번일때 대표카드
         * */
        if ("0".equals(mCardRegisterListResultArrayList.get(position).getCard_Choice())) {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.GONE);
        } else {
            mCardSliderBinding.ivRepresentativeCard.setVisibility(View.VISIBLE);
        }
        mCardSliderBinding.ivRepresentativeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.showRepresentativeCardDialog(getRepresentativeCard().getCard_Code(), mCardRegisterListResultArrayList.get(position).getCard_Code());
            }
        });

        Glide.with(mContext)
                .load(Constants.BASE_IMAGE_URL + mCardRegisterListResultArrayList.get(position).getUpload_Name())
                .fitCenter()
                .into(mCardSliderBinding.ivCardImage);

        container.addView(mCardSliderBinding.getRoot());
        return mCardSliderBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
