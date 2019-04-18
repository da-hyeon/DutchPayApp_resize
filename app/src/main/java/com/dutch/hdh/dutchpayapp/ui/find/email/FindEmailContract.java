package com.dutch.hdh.dutchpayapp.ui.find.email;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface FindEmailContract {
    interface View extends BaseActivityContract.View{
        void showEmailLayout();
        void hideEmailLayout();

        void changeEmailText(String email);
    }
    interface Presenter{
        void clickCancel();
        void clickOKButton(EditText[] mEditTextArray);
    }
}
