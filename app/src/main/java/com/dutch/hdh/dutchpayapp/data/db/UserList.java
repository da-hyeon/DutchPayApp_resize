package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class UserList {

    String name;

    @SerializedName("phoneNumber")
    String phone;

    String price;
    boolean prepay_status;

    public UserList(String name, String phone, String price, boolean prepay_status) {
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.prepay_status = prepay_status;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isPrepay_status() {
        return prepay_status;
    }

    public void setPrepay_status(boolean prepay_status) {
        this.prepay_status = prepay_status;
    }
}
