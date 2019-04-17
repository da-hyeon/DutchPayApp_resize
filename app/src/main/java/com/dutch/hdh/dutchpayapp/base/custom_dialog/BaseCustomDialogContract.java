package com.dutch.hdh.dutchpayapp.base.custom_dialog;

public interface BaseCustomDialogContract {
    interface View{
        void showSuccessDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showFailDialog(String title, String content);
    }
    interface Presenter{

    }
}
