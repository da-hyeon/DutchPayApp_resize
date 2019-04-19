package com.dutch.hdh.dutchpayapp.base.activity;

public interface BaseActivityContract {
<<<<<<< HEAD
    interface View{

        void isPermissionCheck();

=======
    interface View {

        void showCommonDialog(String title, String content, boolean isBack);
>>>>>>> sungguen
        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);

        void finish();

        void hideKeyboard();
    }

    interface Presenter{
    }
}
