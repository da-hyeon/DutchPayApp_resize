package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;

public interface DutchpayNewContract {
    interface View{

        //어댑터 셋팅
        void adapterInit();

    }

    interface Presenter{

        //리스트 셋업
        void listInit();

        //Click

    }

}
