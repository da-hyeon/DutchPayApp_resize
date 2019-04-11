package com.dutch.hdh.dutchpayapp.ui.wallet.mywallet;

import android.content.Context;
import android.content.Intent;

import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardActivity;

public class MyWalletPresenter implements MyWalletContract.Presenter{


    private MyWalletContract.View mView;
    private Context mContext;


    public MyWalletPresenter (MyWalletContract.View view, Context context) {
        this.mView = view;
        this.mContext = context;

    }

    @Override
    public void clickCard() {
        Intent intent = new Intent(mContext, MyCardActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void clickAccount() {

    }

    @Override
    public void clickSendReceive() {

    }

    @Override
    public void clickHistory() {

    }

    @Override
    public void clickGiftCard() {

    }
}
