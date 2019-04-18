package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.support.v4.app.FragmentManager;

public interface DutchpayNewConfirmContract {

    interface View{
        //어댑터 셋팅
        void adapterInit();

        FragmentManager getFragmentManager();
    }

    interface Presenter{

        //리스트 셋업
        void listInit();

        //Click
        void onPayClick();

    }
}
