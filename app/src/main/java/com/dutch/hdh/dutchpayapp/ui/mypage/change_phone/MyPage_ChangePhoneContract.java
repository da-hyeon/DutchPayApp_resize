package com.dutch.hdh.dutchpayapp.ui.mypage.change_phone;

import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;

public interface MyPage_ChangePhoneContract {
    interface View extends BaseActivityContract.View{

    }
    interface Presenter{
        void clickCancel();
        void clickChange(EditText[] mEditTextArray);
    }
}
