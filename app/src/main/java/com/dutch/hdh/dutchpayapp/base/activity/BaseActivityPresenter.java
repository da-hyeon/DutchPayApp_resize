package com.dutch.hdh.dutchpayapp.base.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.kinda.alert.KAlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    public BaseActivityPresenter(BaseActivityContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }
}
