package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;

public interface DutchpayNewContract {
    interface View{

        //뷰셋팅
        void setDutchBtColor(boolean flag);
        void setTypeBtColor(boolean flag);
        void setMyCost(String mycost);
        void setMyCostEditable(boolean flag);
        void setMemCount(int count);

        //어댑터 셋팅
        void adapterInit();

        FragmentManager getFragmentManager();
        Activity getActivity();
    }

    interface Presenter{

        //리스트 셋업
        void listInit(Bundle bundle);

        //기능
        void checkCost(String newcost);
        boolean dutchpayLogic();
        void reDutchpayLogic();
        void notifyListRemoved();
        void clickBackPressed();

        //Click
        void onNextClick();
    }

}
