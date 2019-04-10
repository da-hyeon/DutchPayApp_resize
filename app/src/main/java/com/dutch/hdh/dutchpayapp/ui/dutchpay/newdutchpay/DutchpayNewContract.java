package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;

public interface DutchpayNewContract {
    interface View{

        //뷰셋팅
        void setDutchBtColor(boolean flag);
        void setTypeBtColor(boolean flag);
        void setMyCost(String mycost);

        //어댑터 셋팅
        void adapterInit();

        FragmentManager getFragmentManager();
    }

    interface Presenter{

        //리스트 셋업
        void listInit();

        //기능
        void checkCost(String newcost);
        boolean dutchpayLogic();
        void reDutchpayLogic(TempNewListModel item);

        //Click
        void onNextClick();
    }

}
