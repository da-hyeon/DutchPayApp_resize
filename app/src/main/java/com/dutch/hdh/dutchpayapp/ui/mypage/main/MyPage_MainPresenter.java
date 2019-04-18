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

    /**
     * 생성자
     */
    MyPage_MainPresenter(MyPage_MainContract.View mView, Context mContext, Activity mActivity ,FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        this.mMyApplication = MyApplication.getInstance();
    }


    /**
     * 뷰 세팅
     */
    @Override
    public void setVIew() {
        //유저 이름
        mView.changeUserNameText(mMyApplication.getUserInfo().getUserName());

        //유저 더치머니
        mView.changeMoneyText(String.format("%,d" , mMyApplication.getUserInfo().getUserMoney()));

        //유저 이메일
        mView.changeEmailText(mMyApplication.getUserInfo().getUserEmail());

        //유저 전화번호
        mView.changePhoneNumberText(mMyApplication.getUserInfo().getUserPhone());
    }

    /**
     * 이메일 변경하기 클릭 이벤트 처리
     */
    @Override
    public void clickChangeEmail() {
        Intent intent = new Intent(mContext, MyPage_ChangeEmailActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 비밀번호 변경하기 클릭 이벤트 처리
     */
    @Override
    public void clickChangePassword() {
        Intent intent = new Intent(mContext, MyPage_ChangePasswordActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 전화번호 변경하기 클릭 이벤트 처리
     */
    @Override
    public void clickChangePhoneNumber() {
        Intent intent = new Intent(mContext, MyPage_ChangePhoneActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    /**
     * 회원탈퇴하기 클릭 이벤트 처리
     */
    @Override
    public void clickWithdrawal() {
        Intent intent = new Intent(mContext, MyPage_WithdrawalActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
