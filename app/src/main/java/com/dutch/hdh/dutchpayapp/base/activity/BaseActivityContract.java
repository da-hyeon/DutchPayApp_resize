package com.dutch.hdh.dutchpayapp.base.activity;

import android.support.v4.app.FragmentManager;

public interface BaseActivityContract {
    interface View{

        void isPermissionCheck();


        void showCommonDialog(String title, String content, boolean isBack);
        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);

        void finish();

        void hideKeyboard();

    }

    interface Presenter{
    }
}
