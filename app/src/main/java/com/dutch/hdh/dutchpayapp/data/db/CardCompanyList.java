package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class CardCompanyList {

    private ArrayList<CardCompanyListResult> cardtypelist;

    public ArrayList<CardCompanyListResult> getCardtypelist() {
        return cardtypelist;
    }

    public void setCardtypelist(ArrayList<CardCompanyListResult> cardtypelist) {
        this.cardtypelist = cardtypelist;
    }

    public class CardCompanyListResult {

        private String card_TypeName;
        private String card_TypeCode;

        public String getCard_TypeName() {
            return card_TypeName;
        }

        public void setCard_TypeName(String card_TypeName) {
            this.card_TypeName = card_TypeName;
        }

        public String getCard_TypeCode() {
            return card_TypeCode;
        }

        public void setCard_TypeCode(String card_TypeCode) {
            this.card_TypeCode = card_TypeCode;
        }

    }
}
