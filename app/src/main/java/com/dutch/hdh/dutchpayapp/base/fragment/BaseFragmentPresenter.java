package com.dutch.hdh.dutchpayapp.base.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class BaseFragmentPresenter implements BaseFragmentContract.Presenter {
    private BaseFragmentContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public BaseFragmentPresenter(BaseFragmentContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }
}
