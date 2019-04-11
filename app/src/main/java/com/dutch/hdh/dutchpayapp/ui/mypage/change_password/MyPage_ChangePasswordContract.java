package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_ChangePasswordContract {
    interface View extends BaseActivityContract.View{

    }
    interface Presenter{
        void clickCancel();
        void clickChange(EditText[] mEditTextArray);
    }
}
