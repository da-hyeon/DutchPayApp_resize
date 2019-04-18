package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.databinding.ObservableBoolean;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.setup.invite.InviteFragment;

import java.util.Observable;

public class SetupPresenter implements SetupContract.Presenter{

    SetupContract.View mView;

    public ObservableBoolean autoFlag = new ObservableBoolean();
    public ObservableBoolean pushFlag = new ObservableBoolean();
    public ObservableBoolean marketingFlag = new ObservableBoolean();

    public SetupPresenter(SetupContract.View mView) {
        this.mView = mView;
        this.autoFlag.set(true);
        this.pushFlag.set(true);
        this.marketingFlag.set(true);
    }

    public void onInviteClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        InviteFragment inviteFragment = new InviteFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,inviteFragment, InviteFragment.class.getName());
        fragmentTransaction.addToBackStack(InviteFragment.class.getName());
        fragmentTransaction.commit();
    }

    //로그아웃 클릭
    public void onLogoutClick(){

    }

    //자동 로그인 온 설정
    @Override
    public void autoOnClick() {

    }

    //자동 로글인 오프 설정
    @Override
    public void autoOffClick() {

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
}
