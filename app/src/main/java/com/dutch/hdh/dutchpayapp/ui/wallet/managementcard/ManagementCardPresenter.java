package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardManagementListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.CardManagement;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardManagementBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.addcard.AddCardActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementCardPresenter implements ManagementCardContract.Presenter {

    private Context mContext;
    private ManagementCardContract.View mView;
    private ActivityCardManagementBinding mBinding;

    public ManagementCardPresenter(Context context, ManagementCardContract.View view, ActivityCardManagementBinding activityCardManagementBinding) {
        this.mContext = context;
        this.mView = view;
        this.mBinding = activityCardManagementBinding;
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
    public void cardDelete(String cardCode) {
        Call<Void> cardDelete = MyApplication.getInstance()
                .getRestAdapter()
                .setCardDelete(cardCode);

        cardDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() == null) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

    }

    @Override
    public void getCardRegisterList() {

    }

    @Override
    public ArrayList<CardManagement.CardManagementListResult> getCardManagementDummyData() {
        ArrayList<CardManagement.CardManagementListResult> managementListResultArrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            managementListResultArrayList.add(new CardManagement.CardManagementListResult("하나카드", "1111-****-****-1111", i));
        }
        return managementListResultArrayList;
    }

    @Override
    public void cancelClick() {
        if (mContext instanceof ManagementCardActivity) {
            ((ManagementCardActivity) mContext).onBackPressed();
        }
    }
}
