package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface Register_FormContract {

    interface View extends BaseFragmentContract.View {
    }

    interface Presenter{
        void clickRegister(EditText[] EditTextArray);
        void clickAuthNumber(String PhoneNumber);
    }
}
