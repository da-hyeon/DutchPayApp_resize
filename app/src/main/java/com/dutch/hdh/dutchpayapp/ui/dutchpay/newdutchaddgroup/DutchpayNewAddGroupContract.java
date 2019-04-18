package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public interface DutchpayNewAddGroupContract {

    interface View{

        //어댑터 셋팅
        void adapterInit();

        FragmentManager getFragmentManager();

    }

    interface Presenter{

        //리스트 셋팅
        void listInit();

        void addMemCount(int count);

        //클릭 이벤트
        void onPlusClick();
    }
}
