package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AccountList {
    @SerializedName("accounttypelist")
    ArrayList<Account> accountArrayList;
}
