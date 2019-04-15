package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class SearchPassword {
    @SerializedName("password")
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
