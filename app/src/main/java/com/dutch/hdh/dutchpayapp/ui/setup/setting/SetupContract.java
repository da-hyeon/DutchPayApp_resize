package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.support.v4.app.FragmentManager;

public interface SetupContract {

    interface View{

        FragmentManager getFragmentManager();

    }

    interface Presenter{

        //클릭
        void autoOnClick();
        void autoOffClick();
        void pushOnClick();
        void pushOffClick();
        void marketingOnClick();
        void marketingOffClick();

        void clickGoHomePage();

    }
}
