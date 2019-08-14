package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.main.fragment.MainFragment;
import com.dutch.hdh.dutchpayapp.ui.setup.invite.InviteFragment;

import java.util.Observable;

import static android.content.Context.MODE_PRIVATE;

public class SetupPresenter implements SetupContract.Presenter {

    SetupContract.View mView;

    public ObservableBoolean autoFlag = new ObservableBoolean();
    public ObservableBoolean pushFlag = new ObservableBoolean();
    public ObservableBoolean marketingFlag = new ObservableBoolean();

    private MyApplication mMyApplication;
    private Activity mActivity;
    private FragmentManager mFragmentManager;

    public SetupPresenter(SetupContract.View mView, Activity mActivity, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.autoFlag.set(false);
        this.pushFlag.set(true);
        this.marketingFlag.set(true);
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 자동 로그인 스위치
     */
    @Override
    public void swAutoLoginClick(){
        if(autoFlag.get()){
            autoOffClick();
        } else {
            autoOnClick();
        }
        autoFlag.set( !(autoFlag.get()) );
    }

    /**
     * 푸시 알림 스위치
     */
    @Override
    public void swPushClick(){
        if(pushFlag.get()){
            pushOffClick();
        } else {
            pushOnClick();
        }
        pushFlag.set( !(pushFlag.get()) );
    }

    /**
     *  마켓팅 이용동의 스위치
     */
    @Override
    public void swMarketingClick(){
        if(marketingFlag.get()){
            marketingOffClick();
        } else {
            marketingOnClick();
        }
        marketingFlag.set( !(marketingFlag.get()) );
    }

    /**
     * 친구 초대 클릭
     */
    @Override
    public void onInviteClick() {
        moveToInviteFragment();
    }

    /**
     * 로그아웃 클릭
     */
    @Override
    public void onLogoutClick() {
        mView.setDefaultMainStack();
        mMyApplication.getUserInfo().setUserState(false);
        ((MainActivity) mActivity).showUserInfo(mMyApplication.getUserInfo().isUserState() , null);
        ((MainActivity) mActivity).mPresenter.getMainFragment().showUserInfo(null, 0, false);
        autoLogin(false);
        // mMainFragment.showUserInfo(null, 0, false) ;
    }

    /**
     * 홈페이지 버튼 클릭
     */
    @Override
    public void clickGoHomePage() {
        //홈페이지 이동
    }

    /**
     *  자동 로그인 저장 상태 반영
     */
    @Override
    public void AutologinInit(){
        SharedPreferences sharedPreferences = mActivity.getSharedPreferences(Constants.USER_SETTING, MODE_PRIVATE);
        boolean autologin = sharedPreferences.getBoolean(Constants.USER_AUTOLOGIN, false);

        autoFlag.set(autologin);
    }

    /**
     *  변경 사항 저장
     */
    private void settingSave(){
        SharedPreferences sharedPreferences = mActivity.getSharedPreferences(Constants.USER_SETTING, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //앱 내에 유저설정 저장
        editor.putBoolean(Constants.USER_AUTOLOGIN, !(autoFlag.get()) ); //자동 로그인 설정 저장

        editor.commit();
    }

    /**
     * 자동로그인 처리
     */
    private void autoLogin(boolean isAutoLoginCheck){
        SharedPreferences sharedPreferences = mActivity.getSharedPreferences(Constants.USER_INFOMATION, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (isAutoLoginCheck) {
            //앱 내에 유저정보 저장
            editor.putString(Constants.USER_ID, mMyApplication.getUserInfo().getUserEmail()); // 이메일 저장
            editor.putString(Constants.USER_PASSWORD, mMyApplication.getUserInfo().getUserPassword()); // 비밀번호 저장
        } else {
            editor.putString(Constants.USER_ID,""); // 이메일 저장
            editor.putString(Constants.USER_PASSWORD, ""); // 비밀번호 저장
        }

        editor.commit();
    }

    //자동 로그인 온 처리
    private void autoOnClick() {
        autoLogin(true);
        settingSave();
    }

    //자동 로그인 오프 처리
    private void autoOffClick() {
        autoLogin(false);
        settingSave();
    }

    //푸시 온 처리
    private void pushOnClick() {

    }

    //푸시 오프 처리
    private void pushOffClick() {

    }

    //마켓팅 온 처리
    private void marketingOnClick() {

    }

    //마켓팅 오프 처리
    private void marketingOffClick() {

    }

    /**
     * 친구 초대 페이지 이동
     */
    private void moveToInviteFragment(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        InviteFragment inviteFragment = new InviteFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, inviteFragment, InviteFragment.class.getName());
        fragmentTransaction.addToBackStack(InviteFragment.class.getName());
        fragmentTransaction.commit();
    }

    //홈페이지로 이동
}
