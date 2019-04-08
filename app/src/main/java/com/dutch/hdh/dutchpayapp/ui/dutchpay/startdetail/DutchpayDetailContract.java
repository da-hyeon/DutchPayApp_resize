package com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail;

import android.support.v4.app.FragmentManager;

public interface DutchpayDetailContract {

    interface View{

        //어댑터 초기 설정
        void adapterInit();

        FragmentManager getFragmentManager();
    }

    interface Presenter{

        //리스트 셋업
        void listInit();

    }
}
