package com.dutch.hdh.dutchpayapp.data.db;

public class PersonalPaymentInformation {
    private String storeName;
    private String date;
    private int amount;

    public PersonalPaymentInformation(String storeName, String date, int amount) {
        this.storeName = storeName;
        this.date = date;
        this.amount = amount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
