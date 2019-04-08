package com.dutch.hdh.dutchpayapp.ui.wallet.addcard;

import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.data.db.CardList;

import java.util.ArrayList;

public interface AddCardContract {

    interface View {
        void initData();
        void cardData(String cardName, int cardCode);
    }

    interface Presenter {


        TextWatcher getTextWatcher(EditText editText, int maxLength);

        boolean cardValidate();
        void cardSelect();
        ArrayList<CardList.CardListResult> getCardDummyData();
        void cardAddConfirm(String cardCompany, String cardNumber, String cardYear, String cardMonth, String cardCVC);

        void cancelClick();
    }
}
