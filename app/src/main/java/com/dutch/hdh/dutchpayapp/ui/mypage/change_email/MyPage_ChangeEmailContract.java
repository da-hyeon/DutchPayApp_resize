package com.dutch.hdh.dutchpayapp.ui.mypage.change_email;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_ChangeEmailContract {
    interface View extends BaseActivityContract.View{
        void changeEmailText(String email);
    }
    interface Presenter{
        void setVIew();

        void clickCancel();
        void clickChange(EditText[] mEditTextArray);
    }
}
