package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.content.Context;

public class MyPage_WithdrawalPresenter implements MyPage_WithdrawalContract.Presenter {
    private MyPage_WithdrawalContract.View mView;
    private Context mContext;

    public MyPage_WithdrawalPresenter(MyPage_WithdrawalContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void clickCancel() {
        mView.finish();
    }
}
