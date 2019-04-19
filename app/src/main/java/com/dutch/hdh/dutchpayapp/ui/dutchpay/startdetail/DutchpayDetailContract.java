package com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public interface DutchpayDetailContract {

    interface View{

        //뷰변동
        void setDutchpayTitle(String title);
        void setDutchpayCost(String cost);
        void setDutchpayMemCount(String memCount);
        void setPointButtonGone();

        //어댑터 초기 설정
        void adapterInit();

        FragmentManager getFragmentManager();

        void showSuccessDialog(String a,String b);
        void showFailDialog(String a, String b);
    }

    interface Presenter{

        //리스트 셋업
        void listInit(Bundle bundle);

    }
}
