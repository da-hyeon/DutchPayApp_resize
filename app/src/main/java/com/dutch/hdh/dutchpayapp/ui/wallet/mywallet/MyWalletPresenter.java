package com.dutch.hdh.dutchpayapp.ui.wallet.mywallet;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;
import com.dutch.hdh.dutchpayapp.ui.wallet.myaccount.MyAccountActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardActivity;
import com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive.SendReceiveFragment;

public class MyWalletPresenter implements MyWalletContract.Presenter{


    private MyWalletContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;


    public MyWalletPresenter (MyWalletContract.View view, Context context, FragmentManager fragmentManager) {
        this.mView = view;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void clickCard() {
        Intent intent = new Intent(mContext, MyCardActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void clickAccount() {
        Intent intent = new Intent(mContext, MyAccountActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void clickSendReceive() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,0,0,  R .anim.fade_out);
        SendReceiveFragment sendReceiveFragment = new SendReceiveFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, sendReceiveFragment , SendReceiveFragment.class.getName());
        fragmentTransaction.addToBackStack(SendReceiveFragment.class.getName());
        fragmentTransaction.commit();
    }

    @Override
    public void clickHistory() {
    }

    @Override
    public void clickGiftCard() {

    }
}
