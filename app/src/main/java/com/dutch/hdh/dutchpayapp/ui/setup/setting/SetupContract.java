package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.support.v4.app.FragmentManager;

public interface SetupContract {

    interface View{

        FragmentManager getFragmentManager();

        void setDefaultMainStack();

    }

    interface Presenter{

        void AutologinInit();

        //클릭
        void swAutoLoginClick();
        void swPushClick();
        void swMarketingClick();

        void clickGoHomePage();
        void onLogoutClick();
        void onInviteClick();
    }
}
