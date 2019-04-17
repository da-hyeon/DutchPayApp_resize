package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import com.dutch.hdh.dutchpayapp.base.custom_dialog.BaseCustomDialogContract;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.scan.PersonalPayment_ScanContract;

public interface Payment_InfomationDialogContract {
    interface View extends BaseCustomDialogContract.View {
        void changePaymentAmount(int amount);
        void changeMyMoney(int money);
    }
    interface Presenter{
        void setScanView(PersonalPayment_ScanContract.View mScanView);
        void initVIew();
        void onDismiss();

        void clickOK();
    }
}
