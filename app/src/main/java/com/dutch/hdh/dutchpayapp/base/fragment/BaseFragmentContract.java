package com.dutch.hdh.dutchpayapp.base.fragment;

public interface BaseFragmentContract {
    interface View {

        void setDefaultMainStack();

        void showCommonDialog(String title, String content, boolean isBack);

        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);

        void hideKeyboard();

    }
    interface Presenter {

    }
}
