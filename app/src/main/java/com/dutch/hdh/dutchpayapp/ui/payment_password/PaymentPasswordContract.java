package com.dutch.hdh.dutchpayapp.ui.payment_password;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface PaymentPasswordContract {
    interface View extends BaseFragmentContract.View {
        void showRandomNumber(int index ,String randomNumber);
        void dotImagesUpdate(int index, boolean checkState);
    }
    interface Presenter{
        void initRandomNumber();

        void clickNumber(String numberText);
        void clickDeleteButton();
        void clickOKButton();
    }
}
