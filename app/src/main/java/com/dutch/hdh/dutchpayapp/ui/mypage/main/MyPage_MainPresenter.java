package com.dutch.hdh.dutchpayapp.ui.mypage.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.MyApplication;

public class MyPage_MainPresenter implements MyPage_MainContract.Presenter{

    private MyPage_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private MyApplication mMyApplication;

    public MyPage_MainPresenter(MyPage_MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mMyApplication = MyApplication.getInstance();
    }


    @Override
    public void setVIew() {
        mView.changeMoneyText(mMyApplication.getUserInfo().getUserMoney()+"");
        mView.changeEmailText(mMyApplication.getUserInfo().getUserEmail());
        mView.changePhoneNumberText(mMyApplication.getUserInfo().getUserPhone());
    }
}
