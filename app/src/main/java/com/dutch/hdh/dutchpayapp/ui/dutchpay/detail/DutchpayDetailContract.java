package com.dutch.hdh.dutchpayapp.ui.dutchpay.detail;

public interface DutchpayDetailContract {

    interface View{

        //어댑터 초기 설정
        void adapterInit();

    }

    interface Presenter{

        //리스트 셋업
        void listInit();

    }
}
