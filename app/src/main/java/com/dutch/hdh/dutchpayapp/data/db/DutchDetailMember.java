package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class DutchDetailMember {

    @SerializedName("dutchPay_Pcode")
    int memDutchcode;

    @SerializedName("dutchPay_Price")
    int memCost;

    @SerializedName("dutchPay_Code")
    int roomCode;

    @SerializedName("멤버이름")
    String naem;

    @SerializedName("user_Code")
    int memCode;

    @SerializedName("방장0번멤버1번")
    int leaderFlag;

    @SerializedName("개인결제여부")
    String payComplete;

    @SerializedName("선결제여부")
    String prePayed;

    public int getMemDutchcode() {
        return memDutchcode;
    }

    public void setMemDutchcode(int memDutchcode) {
        this.memDutchcode = memDutchcode;
    }

    public int getMemCost() {
        return memCost;
    }

    public void setMemCost(int memCost) {
        this.memCost = memCost;
    }

    public int getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public int getMemCode() {
        return memCode;
    }

    public void setMemCode(int memCode) {
        this.memCode = memCode;
    }

    public int getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(int leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public String getPayComplete() {
        return payComplete;
    }

    public void setPayComplete(String payComplete) {
        this.payComplete = payComplete;
    }

    public String getPrePayed() {
        return prePayed;
    }

    public void setPrePayed(String prePayed) {
        this.prePayed = prePayed;
    }
}
