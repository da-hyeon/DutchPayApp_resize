package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Activity;
import android.content.Context;

import com.dutch.hdh.dutchpayapp.MyApplication;

public class Payment_InfomationDialogPresenter implements Payment_InfomationDialogContract.Presenter{

    private Payment_InfomationDialogContract.View mView;
    private Context mContext;
    private Activity mActivity;

    private MyApplication mMyApplication;

    private int mAmount;

    Payment_InfomationDialogPresenter(Payment_InfomationDialogContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;

        this.mView.initData();

        mMyApplication = MyApplication.getInstance();
    }
}
