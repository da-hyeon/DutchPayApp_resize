package com.dutch.hdh.dutchpayapp.base.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.MyApplication;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;


    public BaseActivityPresenter(BaseActivityContract.View mView, Context mContext,  Activity mActivity ,FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }
}
