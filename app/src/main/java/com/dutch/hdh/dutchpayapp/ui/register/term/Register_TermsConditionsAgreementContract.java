package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.os.Bundle;

public interface Register_TermsConditionsAgreementContract {
    interface View{
        void initData();

        void showDialog(String content);

        void changeAllTOS(boolean state);
        void changeTOS(int index, boolean state);
        void changeAllView(int index, int id);
    }

    interface Presenter{
        void refreshData(Bundle bundle);
        void onResume();

        void clickAllTOS(boolean state);
        void clickTOS(int index , boolean state);
        void clickRegister();
        void clickAllView(int index );
    }
}
