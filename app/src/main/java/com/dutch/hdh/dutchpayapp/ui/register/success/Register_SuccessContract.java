package com.dutch.hdh.dutchpayapp.ui.register.success;

public interface Register_SuccessContract {
    interface View {
        void removeAllExceptMains();
    }
     interface Presenter {
        void clickAppStart();
     }
}
