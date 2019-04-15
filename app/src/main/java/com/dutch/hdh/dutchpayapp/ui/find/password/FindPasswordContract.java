package com.dutch.hdh.dutchpayapp.ui.find.password;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface FindPasswordContract {
    interface View  extends BaseActivityContract.View{
        void showPasswordLayout();
        void hidePasswordLayout();
    }

    interface Presenter {
        void clickCancel();
        void clickOKButton(EditText[] mEditTextArray);
    }
}
