package com.dutch.hdh.dutchpayapp.ui.dialog.charge_money;

public interface ChargeMoneyDialogContract {
    interface View{
        void showHangleText(String content);

        void changeAmountText(String content);
        void changeChargeAccountBackgroundAndTextColor(boolean state);
        void changeChargeCardBackgroundAndTextColor(boolean state);
        void changeInfoText(String content);

        void removeDialog();
    }
    interface Presenter{
        void inputAmountText(String num);

        void clickChargeAccount();
        void clickChargeCard();
        void clickCancel();
    }
}
