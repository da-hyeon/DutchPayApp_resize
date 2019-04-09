package com.dutch.hdh.dutchpayapp.ui.mypage.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class MyPage_MainPresenter implements MyPage_MainContract.Presenter{
    private MyPage_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public MyPage_MainPresenter(MyPage_MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

}
