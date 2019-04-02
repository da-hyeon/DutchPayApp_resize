package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.widget.EditText;

public interface Register_FormContract {

    interface View{
        void showDialog(String content);
    }

    interface Presenter{
        void clickRegister(EditText[] EditTextArray);
    }
}
