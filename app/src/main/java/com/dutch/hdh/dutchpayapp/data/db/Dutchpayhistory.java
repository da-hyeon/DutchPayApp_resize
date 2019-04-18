package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Dutchpayhistory {
    @SerializedName("dutchpaytotallist")
    private ArrayList<DutchpaytotalList> DutchpayHistory;

    public ArrayList<DutchpaytotalList> getDutchpayHistoryList() {
        return DutchpayHistory;
    }

    public void setDutchpayHistoryList(ArrayList<DutchpaytotalList> dutchpayHistory) {
        DutchpayHistory = dutchpayHistory;
    }
}
