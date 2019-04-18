package com.dutch.hdh.dutchpayapp.base.custom_dialog;

import android.content.Context;

public class BaseCustomDialogPresenter implements BaseCustomDialogContract.Presenter {

    private BaseCustomDialogContract.View mView;
    private Context mContext;

    public BaseCustomDialogPresenter(BaseCustomDialogContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
