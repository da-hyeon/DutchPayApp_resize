package com.dutch.hdh.dutchpayapp.ui.mypage.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.mypage.change_email.MyPage_ChangeEmailActivity;
import com.dutch.hdh.dutchpayapp.ui.mypage.change_password.MyPage_ChangePasswordActivity;
import com.dutch.hdh.dutchpayapp.ui.mypage.change_phone.MyPage_ChangePhoneActivity;
import com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal.MyPage_WithdrawalActivity;

public class MyPage_MainPresenter implements MyPage_MainContract.Presenter{

    private MyPage_MainContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;

    private MyApplication mMyApplication;

    public MyPage_MainPresenter(MyPage_MainContract.View mView, Context mContext, Activity mActivity ,FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        this.mMyApplication = MyApplication.getInstance();
    }


    @Override
    public void setVIew() {
        mView.changeMoneyText(mMyApplication.getUserInfo().getUserMoney()+"");
        mView.changeEmailText(mMyApplication.getUserInfo().getUserEmail());
        mView.changePhoneNumberText(mMyApplication.getUserInfo().getUserPhone());
    }

    @Override
    public void clickChangeEmail() {
        Intent intent = new Intent(mContext, MyPage_ChangeEmailActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void clickChangePassword() {
        Intent intent = new Intent(mContext, MyPage_ChangePasswordActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void clickChangePhoneNumber() {
        Intent intent = new Intent(mContext, MyPage_ChangePhoneActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    public void clickWithdrawal() {
        Intent intent = new Intent(mContext, MyPage_WithdrawalActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
