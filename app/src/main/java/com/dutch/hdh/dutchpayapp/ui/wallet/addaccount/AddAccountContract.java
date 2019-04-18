package com.dutch.hdh.dutchpayapp.ui.wallet.addaccount;

import android.text.TextWatcher;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.data.db.AccountRegisterList;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;

import java.util.ArrayList;

public interface AddAccountContract extends BaseActivityContract {

    interface View extends BaseActivityContract.View{
        void initData();

        void bankData(String bankName, String bankCode);

    }

    interface Presenter extends BaseFragmentContract.Presenter {

        TextWatcher getTextWatcher(EditText editText, int maxLength);

        boolean accountValidate();

        void getAccountSelectList();

        void accountAddConfirm(String accountTypeCode);

        void cancelClick();
    }
}
