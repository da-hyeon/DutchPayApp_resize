package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.ui.wallet.addcard.AddCardActivity;

import java.util.ArrayList;
import java.util.List;

public class MyCardPresenter implements MyCardContract.Presenter {

    private Context mContext;
    private MyCardContract.View mView;

    public MyCardPresenter(Context context, MyCardContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void setAdapter(ViewPager viewPager, TabLayout tabLayout) {
        //슬라이드 이미지 저장
        List<Drawable> imageArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            imageArray.add(ContextCompat.getDrawable(mContext, R.drawable.card));
        }
        CardImageSliderAdapter cardImageSliderAdapter = new CardImageSliderAdapter(mContext, imageArray);
        viewPager.setAdapter(cardImageSliderAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void clickCancel() {
        if (mContext instanceof MyCardActivity) {
            ((MyCardActivity) mContext).onBackPressed();
        }
    }

    @Override
    public void clickLeft(ViewPager viewPager) {
        viewPager.arrowScroll(View.FOCUS_LEFT);
    }

    @Override
    public void clickRight(ViewPager viewPager) {
        viewPager.arrowScroll(View.FOCUS_RIGHT);
    }

    @Override
    public void clickCardManagement() {
        Log.e("clickCardManagement", "clickCardManagement");
        Intent intent = new Intent(mContext, AddCardActivity.class);
        mContext.startActivity(intent);
        if (mContext instanceof MyCardActivity) {
            ((MyCardActivity) mContext).overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        }
    }
}
