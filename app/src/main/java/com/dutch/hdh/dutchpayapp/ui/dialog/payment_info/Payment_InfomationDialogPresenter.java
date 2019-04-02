package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.dutch.hdh.dutchpayapp.MyApplication;

public class Payment_InfomationDialogPresenter implements Payment_InfomationDialogContract.Presenter{

    private Payment_InfomationDialogContract.View mView;
    private Context mContext;
    private Activity mActivity;

    private MyApplication mMyApplication;

    private int mAmount;

    public Payment_InfomationDialogPresenter(Payment_InfomationDialogContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;

        this.mView.initData();

        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void getIntent(Intent intent) {
        mAmount = intent.getIntExtra("amount" , 0);
        int userMoney = mMyApplication.getUserInfo().getUserMoney();

        if( userMoney >= mAmount){
            //결제 진행
        } else {
            //충전 유도
        }
    }
}
