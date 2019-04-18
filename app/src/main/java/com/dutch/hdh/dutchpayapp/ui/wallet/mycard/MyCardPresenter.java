package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.ui.wallet.addcard.AddCardActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.managementcard.ManagementCardActivity;
import com.dutch.hdh.dutchpayapp.util.Trace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCardPresenter implements MyCardContract.Presenter {

    private Context mContext;
    private MyCardContract.View mView;
    private MyApplication mMyApplication;

    public MyCardPresenter(Context context, MyCardContract.View view) {
        this.mContext = context;
        this.mView = view;
        mMyApplication = MyApplication.getInstance();
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


    @Override
    public void getRegisterCardList() {
        Call<CardRegisterList> cardRegisterList = mMyApplication
                .getRestAdapter()
                .getRegisterCardList(mMyApplication.getUserInfo().getUserCode());

        cardRegisterList.enqueue(new Callback<CardRegisterList>() {
            @Override
            public void onResponse(Call<CardRegisterList> call, Response<CardRegisterList> response) {
                if (response.body() == null) {

                } else {
                    Trace.e("getRegisterCardList", "getRegisterCardList");
                    mView.setRegisterCardList(response.body().getCardlist());
                }
            }

            @Override
            public void onFailure(Call<CardRegisterList> call, Throwable t) {

            }
        });
    }

    @Override
    public void setRepresentativeCard(String mainCardCode, String subCardCode) {

        Call<Void> cardRegisterList = MyApplication.getInstance()
                .getRestAdapter()
                .setCardRepresentativeCard(mainCardCode, subCardCode);

        cardRegisterList.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() == null) {
                    getRegisterCardList();
                    Trace.e("setRepresentativeCard", "setRepresentativeCard");
                } else {
                    Trace.e("setRepresentativeCard", "setRepresentativeCard");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
