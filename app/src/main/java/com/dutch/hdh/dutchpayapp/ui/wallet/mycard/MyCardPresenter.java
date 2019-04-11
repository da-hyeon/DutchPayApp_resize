package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.ui.wallet.addcard.AddCardActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.managementcard.ManagementCardActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCardPresenter implements MyCardContract.Presenter {

    private Context mContext;
    private MyCardContract.View mView;

    public MyCardPresenter(Context context, MyCardContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void setAdapter(ViewPager viewPager, TabLayout tabLayout) {
        Call<CardRegisterList> cardRegisterList = MyApplication.getInstance()
                .getRestAdapter()
                .getRegisterCardList(Constants.USER_CODE);

        cardRegisterList.enqueue(new Callback<CardRegisterList>() {
            @Override
            public void onResponse(Call<CardRegisterList> call, Response<CardRegisterList> response) {
                if (response.body() == null) {

                } else {
                    CardImageSliderAdapter cardImageSliderAdapter = new CardImageSliderAdapter(mContext, response.body().getCardlist());
                    viewPager.setAdapter(cardImageSliderAdapter);
                    tabLayout.setupWithViewPager(viewPager, true);
                }
            }

            @Override
            public void onFailure(Call<CardRegisterList> call, Throwable t) {

            }
        });
    }

    @Override
    public void clickCancel() {
        if (mContext instanceof MyCardActivity) {
            ((MyCardActivity) mContext).onBackPressed();
        }
    }

    @Override
    public void clickLeft(ViewPager viewPager, TabLayout tabLayout) {
        viewPager.arrowScroll(View.FOCUS_LEFT);
        if (viewPager.getVisibility() == View.GONE) {
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void clickRight(ViewPager viewPager, TabLayout tabLayout) {
        viewPager.arrowScroll(View.FOCUS_RIGHT);

//        Log.e("탭포지션", tabLayout.getSelectedTabPosition()+"");
//        Log.e("카운터", viewPager.getAdapter().getCount()+"");
        if (tabLayout.getSelectedTabPosition() + 1 == viewPager.getAdapter().getCount()) {
            viewPager.setVisibility(View.GONE);
        }
    }

    @Override
    public void clickCardManagement() {
        Log.e("clickCardManagement", "clickCardManagement");
        Intent intent = new Intent(mContext, ManagementCardActivity.class);
        mContext.startActivity(intent);
        if (mContext instanceof MyCardActivity) {
            ((MyCardActivity) mContext).overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        }
    }

    @Override
    public void clickCardAdd() {
        Log.e("clickCardManagement", "clickCardManagement");
        Intent intent = new Intent(mContext, AddCardActivity.class);
        mContext.startActivity(intent);
        if (mContext instanceof MyCardActivity) {
            ((MyCardActivity) mContext).overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        }
    }
}
