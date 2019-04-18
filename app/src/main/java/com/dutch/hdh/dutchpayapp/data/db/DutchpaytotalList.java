package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class DutchpaytotalList {

    @SerializedName("총금액")
    int cost;

    @SerializedName("상점명")
    String shop;

    @SerializedName("방생성날짜")
    String date;

    @SerializedName("내부결제완료여부")
    boolean costcomplete;

    @SerializedName("더치페이코드")
    int dutchpayid;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCostcomplete() {
        return costcomplete;
    }

    public void setCostcomplete(boolean costcomplete) {
        this.costcomplete = costcomplete;
    }

    public int getDutchpayid() {
        return dutchpayid;
    }

    public void setDutchpayid(int dutchpayid) {
        this.dutchpayid = dutchpayid;
    }
}
