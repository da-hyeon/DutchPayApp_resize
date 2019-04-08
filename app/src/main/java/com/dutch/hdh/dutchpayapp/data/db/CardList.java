package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class CardList {

    private ArrayList<CardListResult> data;


    public ArrayList<CardListResult> getData() {
        return data;
    }

    public void setData(ArrayList<CardListResult> data) {
        this.data = data;
    }

    public static class CardListResult {

        private String cardName;
        private int carCode;

        public CardListResult() {

        }

        public CardListResult (String cardName, int cardCode) {
            this.cardName = cardName;
            this.carCode = cardCode;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public int getCarCode() {
            return carCode;
        }

        public void setCarCode(int carCode) {
            this.carCode = carCode;
        }

    }
}
