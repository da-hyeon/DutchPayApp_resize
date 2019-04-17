package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import com.google.gson.annotations.SerializedName;

public class TempConfirmListModel {

    String name;
    String phone;
    String cost;
    boolean completeFlag;

    public TempConfirmListModel(String name, String phone, String cost, boolean completeFlag) {
        this.name = name;
        this.phone = phone;
        this.cost = cost;
        this.completeFlag = completeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }
}
