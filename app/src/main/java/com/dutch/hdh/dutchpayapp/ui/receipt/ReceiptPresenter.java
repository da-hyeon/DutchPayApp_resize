package com.dutch.hdh.dutchpayapp.ui.receipt;

import android.content.Context;
import android.content.Intent;

import com.dutch.hdh.dutchpayapp.Constants;

import java.util.Objects;

public class ReceiptPresenter implements ReceiptContract.Presenter {

    private ReceiptContract.View mView;
    private Context mContext;
    private Intent mIntent;

    /**
     * 생성자
     */
    ReceiptPresenter(ReceiptContract.View mView, Context mContext, Intent mIntent) {
        this.mView = mView;
        this.mContext = mContext;
        this.mIntent = mIntent;

    }

    /**
     * 뷰 세팅
     */
    @Override
    public void setVIew() {
        String date = Objects.requireNonNull(mIntent.getExtras()).getString(Constants.PAYMENT_DATE);
        String storeName = Objects.requireNonNull(mIntent.getExtras()).getString(Constants.PAYMENT_STORE_NAME);
        int amount = mIntent.getExtras().getInt(Constants.PAYMENT_AMOUNT);
        String storeLocation = Objects.requireNonNull(mIntent.getExtras()).getString(Constants.PAYMENT_STORE_LOCATION);

        mView.changeDate(date);
        mView.changeStoreName(storeName);
        mView.changeAmount(amount);
        mView.changeLocatiion(storeLocation);
    }

    @Override
    public void clickClose() {
        mView.finish();
    }
}
