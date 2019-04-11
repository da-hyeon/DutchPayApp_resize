package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm.DutchpayNewConfirmFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DutchpayNewInfoPresenter implements DutchpayNewInfoContract.Presenter {

    DutchpayNewInfoContract.View mView;
    Bundle mData;

    public DutchpayNewInfoPresenter(DutchpayNewInfoContract.View mView,Bundle data) {
        this.mView = mView;
        this.mData = data;
    }

    public void onNextClick(){
        ArrayList<String> info = mView.getText();
        String jList = mData.getString("JList");
        Bundle bundle = new Bundle(2);
        bundle.putStringArrayList("Info",info);
        bundle.putString("JList",jList);

        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewConfirmFragment dutchpayNewConfirmFragment = new DutchpayNewConfirmFragment();
        dutchpayNewConfirmFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewConfirmFragment, DutchpayNewConfirmFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewConfirmFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void onShopTextCheanged(CharSequence st, int start, int before, int count) {

        if(count != 0){
            mView.ivShopChange(true);
        } else {
            mView.ivShopChange(false);
        }
    }

    public void onDateTextCheanged(CharSequence st, int start, int before, int count) {

        if(count != 0){
            mView.ivDateChagne(true);
        } else {
            mView.ivDateChagne(false);
        }
    }

    public void onCommentTextCheanged(CharSequence st, int start, int before, int count) {

        if(count+start != 0){
            mView.ivCommentChange(true);
        } else {
            mView.ivCommentChange(false);
        }


        mView.setCount(start+count);
    }
}
