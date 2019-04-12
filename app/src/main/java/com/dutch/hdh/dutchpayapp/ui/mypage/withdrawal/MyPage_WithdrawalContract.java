package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_WithdrawalContract {
    interface View extends BaseActivityContract.View {

    }
    interface Presenter {
        void clickCancel();
    }
}
