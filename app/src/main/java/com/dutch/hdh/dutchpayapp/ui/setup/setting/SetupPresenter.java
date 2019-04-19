package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
        this.autoFlag.set(true);
        this.pushFlag.set(true);
        this.marketingFlag.set(true);
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    public void onInviteClick() {
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        InviteFragment inviteFragment = new InviteFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, inviteFragment, InviteFragment.class.getName());
        fragmentTransaction.addToBackStack(InviteFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 로그아웃
     */
    public void onLogoutClick() {
        setDefaultMainStack();
        mMyApplication.getUserInfo().setUserState(false);
        ((MainActivity) mActivity).showUserInfo(mMyApplication.getUserInfo().isUserState() , null);
        ((MainActivity) mActivity).mPresenter.getMainFragment().showUserInfo(null, 0, false);
        autoLogin(false);
        // mMainFragment.showUserInfo(null, 0, false) ;
    }

    //자동 로그인 온 설정
    @Override
    public void autoOnClick() {
        autoLogin(true);
    }

    //자동 로글인 오프 설정
    @Override
    public void autoOffClick() {
        autoLogin(false);
    }

    //푸시 온 설정
    @Override
    public void pushOnClick() {

    }

    //푸시 오프 설정
    @Override
    public void pushOffClick() {

    }

    //마켓팅 온 설정
    @Override
    public void marketingOnClick() {

    }

    //마켓팅 오프 설정
    @Override
    public void marketingOffClick() {

    }

    /**
     * 메인 프래그먼트 빼고 모두 스택에서 제거
     */
    private void setDefaultMainStack() {
        int count = mFragmentManager.getBackStackEntryCount() - 1;
        for (int i = 0; i < count; ++i) {
            mFragmentManager.popBackStack();
        }
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
}
