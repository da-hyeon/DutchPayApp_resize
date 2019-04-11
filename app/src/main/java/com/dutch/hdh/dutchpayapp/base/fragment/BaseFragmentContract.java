package com.dutch.hdh.dutchpayapp.base.fragment;

public interface BaseFragmentContract {
    interface View {
        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);

    }
    interface Presenter {

    }
}
