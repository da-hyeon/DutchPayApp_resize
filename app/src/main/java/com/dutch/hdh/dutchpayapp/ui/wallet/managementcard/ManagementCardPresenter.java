package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardManagementBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.addcard.AddCardActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardActivity;
import com.dutch.hdh.dutchpayapp.util.Trace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementCardPresenter implements ManagementCardContract.Presenter {

    private Context mContext;

    private ManagementCardContract.View mView;
    private ActivityCardManagementBinding mBinding;
    private MyApplication mMyApplication;

    public ManagementCardPresenter(Context context, ManagementCardContract.View view, ActivityCardManagementBinding activityCardManagementBinding) {
        this.mContext = context;
        this.mView = view;
        this.mBinding = activityCardManagementBinding;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void clickCardAdd() {
        Intent intent = new Intent(mContext, AddCardActivity.class);
        mContext.startActivity(intent);
        if (mContext instanceof ManagementCardActivity) {
            ((ManagementCardActivity) mContext).overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        }
    }

    @Override
    public void setCardDelete(String cardCode) {
        Call<Void> cardDelete = MyApplication.getInstance()
                .getRestAdapter()
                .setCardDelete(cardCode);

        cardDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    getRegisterCardList();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

    }

    @Override
    public void getRegisterCardList() {
        Call<CardRegisterList> cardRegisterList = MyApplication.getInstance()
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
    public void cancelClick() {
        if (mContext instanceof ManagementCardActivity) {
            ((ManagementCardActivity) mContext).onBackPressed();
        }
    }

    @Override
    public boolean isRepresentativeCard(String cardChoice) {
        if ("0".equals(cardChoice)) {
            return true;
        }
        return false;
    }
}
