package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class DutchpayhistoryList {

    @SerializedName("결제리스트테이블번호")
    int tablenum;

    @SerializedName("결제가격")
    int cost;

    @SerializedName("카드코드")
    String cardcode;

    @SerializedName("계좌코드")
    String accountcode;

    @SerializedName("결제타입")
    String paytype;

    @SerializedName("이메일")
    String email;

    @SerializedName("유저번호")
    int usernum;

    @SerializedName("결제일시")
    String date;

    @SerializedName("결제완료여부")
    boolean costcomplete;

    @SerializedName("환불여부")
    boolean cancelcomplete;

    @SerializedName("더치페이방결제완료여부")
    boolean dutchcomplete;

    public int getTablenum() {
        return tablenum;
    }

    public void setTablenum(int tablenum) {
        this.tablenum = tablenum;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
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
}
