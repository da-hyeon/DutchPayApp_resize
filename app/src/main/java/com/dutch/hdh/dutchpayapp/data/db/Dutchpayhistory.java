package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Dutchpayhistory {
    @SerializedName("payhistory")
    private ArrayList<DutchpayhistoryList> DutchpayHistory;

    public ArrayList<DutchpayhistoryList> getDutchpayHistoryList() {
        return DutchpayHistory;
    }

    public void setDutchpayHistoryList(ArrayList<DutchpayhistoryList> dutchpayHistory) {
        DutchpayHistory = dutchpayHistory;
    }
}
