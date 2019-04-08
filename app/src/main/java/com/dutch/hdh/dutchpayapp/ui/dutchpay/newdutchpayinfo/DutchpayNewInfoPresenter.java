package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm.DutchpayNewConfirmFragment;

public class DutchpayNewInfoPresenter implements DutchpayNewInfoContract.Presenter {

    DutchpayNewInfoContract.View mView;

    public DutchpayNewInfoPresenter(DutchpayNewInfoContract.View mView) {
        this.mView = mView;
    }

    public void onNextClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewConfirmFragment dutchpayNewConfirmFragment = new DutchpayNewConfirmFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewConfirmFragment, DutchpayNewConfirmFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewConfirmFragment.class.getName());
        fragmentTransaction.commit();
    }
}
