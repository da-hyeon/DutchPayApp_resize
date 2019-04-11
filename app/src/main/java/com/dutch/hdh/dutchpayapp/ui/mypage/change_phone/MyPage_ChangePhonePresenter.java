package com.dutch.hdh.dutchpayapp.ui.mypage.change_phone;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class MyPage_ChangePhonePresenter implements MyPage_ChangePhoneContract.Presenter{

    private MyPage_ChangePhoneContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public MyPage_ChangePhonePresenter(MyPage_ChangePhoneContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

}
