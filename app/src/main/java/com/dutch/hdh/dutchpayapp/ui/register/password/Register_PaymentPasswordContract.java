package com.dutch.hdh.dutchpayapp.ui.register.password;

import android.os.Bundle;

public interface Register_PaymentPasswordContract {

    interface View{
        void updateView();

        void showRandomNumber(int index ,String randomNumber);
        void showSuccessDialog(String content);
        void showFailDialog(String content);

        void dotImagesUpdate(int index, boolean checkState);

        void changeTitle(String content);
        void changeMiddleTitle(String content);

        void removeAllExceptMains();
    }

    interface Presenter{
        void getData(Bundle bundle);
        void initRandomNumber();

        void clickNumber(String numberText);
        void clickDeleteButton();
        void clickOKButton();
        void clickSuccessDialog();
    }
}
