package com.dutch.hdh.dutchpayapp.ui.mypage.change_email;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class MyPage_ChangeEmailPresenter implements MyPage_ChangeEmailContract.Presenter{
    private MyPage_ChangeEmailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public MyPage_ChangeEmailPresenter(MyPage_ChangeEmailContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }
}
