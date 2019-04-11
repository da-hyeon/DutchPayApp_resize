package com.dutch.hdh.dutchpayapp.ui.wallet.addcard;

import android.text.TextWatcher;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.base.BaseContract;

public interface AddCardContract {

    interface View {
        void initData();
        void cardData(String cardName, String cardCode);
    }

    interface Presenter extends BaseContract.Presenter {

        TextWatcher getTextWatcher(EditText editText, int maxLength);
        boolean cardValidate();
        void getCardSelectList();
        void cardAddConfirm(String cardTypeCode);
        void cancelClick();
    }
}
