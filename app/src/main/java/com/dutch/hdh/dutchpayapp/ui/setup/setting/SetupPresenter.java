package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.setup.invite.InviteFragment;

public class SetupPresenter implements SetupContract.Presenter{

    SetupContract.View mView;

    public SetupPresenter(SetupContract.View mView) {
        this.mView = mView;
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
}
