package com.dutch.hdh.dutchpayapp.ui.mypage.main;

public interface MyPage_MainContract {
    interface View{
        void changeMoneyText(String content);
        void changeEmailText(String content);
        void changePhoneNumberText(String content);
    }
    interface Presenter{
        void setVIew();

        void clickChangeEmail();
        void clickChangePassword();
        void clickChangePhoneNumber();
        void clickWithdrawal();
    }

}
