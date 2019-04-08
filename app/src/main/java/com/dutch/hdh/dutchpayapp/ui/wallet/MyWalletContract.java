package com.dutch.hdh.dutchpayapp.ui.wallet;


public interface MyWalletContract {
    interface View {
        //init
        void initData();

        //show
        void showDutchMoney(int userDutchMoney);
    }

    interface Presenter {
        //click
        void clickCard();

        void clickAccount();

        void clickSendReceive();

        void clickHistory();

        void clickGiftCard();
    }
}
