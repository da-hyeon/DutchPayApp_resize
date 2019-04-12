package com.dutch.hdh.dutchpayapp.base.activity;

public interface BaseActivityContract {
    interface View{
        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);

        void finish();
    }
    interface Presenter{
    }
}
