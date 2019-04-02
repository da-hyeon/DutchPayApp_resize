package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

public class TempNewListModel {
    String name;
    String cost;
    String phone;
    boolean completeFlag;

    public TempNewListModel(String name, String cost, String phone, boolean completeFlag) {
        this.name = name;
        this.cost = cost;
        this.phone = phone;
        this.completeFlag = completeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }
}
