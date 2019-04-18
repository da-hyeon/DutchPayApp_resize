package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.content.Intent;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_WithdrawalContract {
    interface View extends BaseActivityContract.View {

        void changeRefundAmount(int amount);
        void changeBankBackground(int id);
        void changeBankImage(int id);
        void changeAccountNumber(String accountNumber);
    }
    interface Presenter {
        void initView(Intent intent);
        void clickCancel();
        void clickChangeRefundAccount();
    }
}
