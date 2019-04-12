package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.content.Context;

public class MyPage_SelectRefundAccountPresenter implements MyPage_SelectRefundAccountContract.Presenter{
    private MyPage_SelectRefundAccountContract.View mView;
    private Context mContext;

    public MyPage_SelectRefundAccountPresenter(MyPage_SelectRefundAccountContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void clickCancel() {

    }
}
