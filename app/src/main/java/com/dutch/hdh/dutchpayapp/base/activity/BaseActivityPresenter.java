package com.dutch.hdh.dutchpayapp.base.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.kinda.alert.KAlertDialog;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public BaseActivityPresenter(BaseActivityContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

}
