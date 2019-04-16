package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.allview.Register_ViewAllTermsConditionsFragment;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;

public class Register_TermsConditionsAgreementPresenter implements Register_TermsConditionsAgreementContract.Presenter {

    private Register_TermsConditionsAgreementContract.View mView;
    private Context mContext;

    private FragmentManager mFragmentManager;

    private boolean mAllTOS;
    private boolean mTOSCheckArray[];

    Register_TermsConditionsAgreementPresenter(Register_TermsConditionsAgreementContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;

        mTOSCheckArray = new boolean[6];
        mAllTOS = false;
    }

    @Override
    public void refreshData(Bundle bundle) {
        if (bundle != null) {
            boolean allCheck = true;
            mTOSCheckArray = bundle.getBooleanArray("checkArray");

            if (mTOSCheckArray != null) {
                for (int i = 0; i < mTOSCheckArray.length; i++) {
                    mView.changeTOS(i, mTOSCheckArray[i]);
                    if ( !mTOSCheckArray[i] ){
                        allCheck = false;
                    }
                }
            }
            mAllTOS = allCheck;
        } else {
            for (int i = 0; i < mTOSCheckArray.length; i++) {
                mView.changeTOS(i, false);
                mTOSCheckArray[i] = false;
            }
            mAllTOS = false;
        }

        mView.changeAllTOS(mAllTOS);
    }

    @Override
    public void onResume() {
        mView.initData();

        boolean allCheck = true;
        for (int i = 0; i < mTOSCheckArray.length; i++) {
            mView.changeTOS(i, mTOSCheckArray[i]);
            if (mTOSCheckArray[i]) {
                mView.changeAllView(i, R.drawable.view_all_on);
            } else {
                mView.changeAllView(i, R.drawable.view_all_off);
                allCheck = false;
            }
        }
        mView.changeAllTOS(allCheck);
    }

    @Override
    public void clickAllTOS(boolean state) {
        for (int i = 0; i < mTOSCheckArray.length; i++) {
            mView.changeTOS(i, state);
            mTOSCheckArray[i] = state;
            if (state) {
                mView.changeAllView(i, R.drawable.view_all_on);
            } else {
                mView.changeAllView(i, R.drawable.view_all_off);
            }
        }
    }

    @Override
    public void clickTOS(int index, boolean state) {
        mTOSCheckArray[index] = state;

        if (state) {
            mView.changeAllView(index, R.drawable.view_all_on);
        } else {
            mView.changeAllView(index, R.drawable.view_all_off);
        }

        for (boolean aMTOSCheckArray : mTOSCheckArray) {
            if (!aMTOSCheckArray) {
                mView.changeAllTOS(false);
                return;
            }
        }
        mView.changeAllTOS(true);
    }

    @Override
    public void clickRegister() {

        for (int i = 0; i < 4; i++) {
            if (!mTOSCheckArray[i]) {
                mView.showDialog("필수 항목에 동의해주세요.");
                return;
            }
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,0,0,  R .anim.fade_out);

        Register_FormFragment mRegister_FormFragment = new Register_FormFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, mRegister_FormFragment , Register_FormFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_FormFragment.class.getName());
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
    }

    @Override
    public void clickAllView(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("num", index);
        bundle.putBooleanArray("checkArray", mTOSCheckArray);

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0,0, R.anim.fade_out);

        Register_ViewAllTermsConditionsFragment register_viewAllTermsConditionsFragment = new Register_ViewAllTermsConditionsFragment();
        register_viewAllTermsConditionsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, register_viewAllTermsConditionsFragment , Register_ViewAllTermsConditionsFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_ViewAllTermsConditionsFragment.class.getName());
        fragmentTransaction.commit();
    }
}
