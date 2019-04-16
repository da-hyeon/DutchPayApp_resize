package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class DutchpayhistoryList {

    @SerializedName("결제가격")
    int cost;

    @SerializedName("결제타입")
    String paytype;

    @SerializedName("결제일시")
    String date;

    @SerializedName("결제완료여부")
    boolean costcomplete;

    @SerializedName("환불여부")
    boolean cancelcomplete;

    @SerializedName("더치페이방결제완료여부")
    boolean dutchcomplete;

    @SerializedName("더치페이상품명")
    String dutchpayname;

    @SerializedName("카드이름")
    String cardname;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
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

    public boolean isCancelcomplete() {
        return cancelcomplete;
    }

    public void setCancelcomplete(boolean cancelcomplete) {
        this.cancelcomplete = cancelcomplete;
    }

    public boolean isDutchcomplete() {
        return dutchcomplete;
    }

    public void setDutchcomplete(boolean dutchcomplete) {
        this.dutchcomplete = dutchcomplete;
    }

    public String getDutchpayname() {
        return dutchpayname;
    }

    public void setDutchpayname(String dutchpayname) {
        this.dutchpayname = dutchpayname;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
