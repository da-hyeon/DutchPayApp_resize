package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class CardManagement {

    private ArrayList<CardManagementListResult> data;

    public ArrayList<CardManagementListResult> getData() {
        return data;
    }

    public void setData(ArrayList<CardManagementListResult> data) {
        this.data = data;
    }

    public static class CardManagementListResult {

        private String cardName;
        private String cardNumber;
        private int cardCode;

        public CardManagementListResult(String cardName, String cardNumber, int cardCode) {
            this.cardName = cardName;
            this.cardNumber = cardNumber;
            this.cardCode = cardCode;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public int getCardCode() {
            return cardCode;
        }

        public void setCardCode(int cardCode) {
            this.cardCode = cardCode;
        }

    }
}
