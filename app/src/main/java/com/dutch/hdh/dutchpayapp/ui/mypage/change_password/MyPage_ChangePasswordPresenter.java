package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class MyPage_ChangePasswordPresenter implements MyPage_ChangePasswordContract.Presenter {

    private MyPage_ChangePasswordContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public MyPage_ChangePasswordPresenter(MyPage_ChangePasswordContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }
}
