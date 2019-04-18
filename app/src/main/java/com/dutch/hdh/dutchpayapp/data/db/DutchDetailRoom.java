package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class DutchDetailRoom {

    @SerializedName("상점명")
    String shop;

    @SerializedName("총결제금액")
    int cost;

    @SerializedName("결제인원")
    int memcount;

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMemcount() {
        return memcount;
    }

    public void setMemcount(int memcount) {
        this.memcount = memcount;
    }
}
