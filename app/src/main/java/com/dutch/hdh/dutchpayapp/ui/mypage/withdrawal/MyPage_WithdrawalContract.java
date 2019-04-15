package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_WithdrawalContract {
    interface View extends BaseActivityContract.View {

        void changeBankBackground(int id);
        void changeBankImage(int id);
        void changeAccountNumber(String accountNumber);
    }
    interface Presenter {
        void initView();
        void clickCancel();
        void clickChangeRefundAccount();
    }
}
