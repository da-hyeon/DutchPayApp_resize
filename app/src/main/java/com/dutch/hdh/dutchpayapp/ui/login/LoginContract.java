package com.dutch.hdh.dutchpayapp.ui.login;

public interface LoginContract {

    interface View {
        //show
        void showSuccessDialog(String content);
        void showFailDialog(String content);

        //remove
        void removeAllExceptMains();
    }

    interface Presenter{
        //click
        void clickLogin(String userID , String userPassword , boolean isAutoLoginCheck);
        void clickRegister();
        void clickFindEmail();
        void clickFindPassword();
        void clickSuccessDialog();
    }
}
