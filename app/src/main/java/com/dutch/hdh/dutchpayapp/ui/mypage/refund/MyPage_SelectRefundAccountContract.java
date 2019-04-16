package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_SelectRefundAccountContract {
    interface View extends BaseActivityContract.View {
        void changeBankBackground(int id);
        void changeBankImage(int id);
        void changeAccountNumber(String accountNumber);
    }
    interface Presenter{
        void initView(ListView listView);
        void clickRepresentativeAccount();
        void clickCancel();
    }
}
