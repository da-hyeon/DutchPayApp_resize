package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AccountList {
    @SerializedName("accountlist")
    ArrayList<Account> accountArrayList;

    public ArrayList<Account> getAccountArrayList() {
        return accountArrayList;
    }

    public void setAccountArrayList(ArrayList<Account> accountArrayList) {
        this.accountArrayList = accountArrayList;
    }
}
