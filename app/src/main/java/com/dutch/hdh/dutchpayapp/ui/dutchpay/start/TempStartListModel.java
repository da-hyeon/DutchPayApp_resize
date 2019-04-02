package com.dutch.hdh.dutchpayapp.ui.dutchpay.start;

public class TempStartListModel {
    String shop;
    String card;
    String cost;
    String date;
    int state;

    public TempStartListModel(String shop, String card, String cost, String date, int state) {
        this.shop = shop;
        this.card = card;
        this.cost = cost;
        this.date = date;
        this.state = state;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
