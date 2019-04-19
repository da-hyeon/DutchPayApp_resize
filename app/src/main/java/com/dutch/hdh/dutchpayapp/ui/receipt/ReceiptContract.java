package com.dutch.hdh.dutchpayapp.ui.receipt;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface ReceiptContract {
    interface View extends BaseActivityContract.View {
        void changeDate(String date);
        void changeStoreName(String name);
        void changeAmount(int amount);
        void changeLocatiion(String location);
    }
    interface Presenter{
        void setVIew();
        void clickClose();
    }
}
