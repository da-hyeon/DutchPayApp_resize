package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DutchpayDetail {
    @SerializedName("dutchpaydetail")
    private ArrayList<DutchDetailRoom> roominfo;

    @SerializedName("dutchpaymember")
    private ArrayList<DutchDetailMember> memberinfo;

    public ArrayList<DutchDetailRoom> getRoominfo() {
        return roominfo;
    }

    public void setRoominfo(ArrayList<DutchDetailRoom> roominfo) {
        this.roominfo = roominfo;
    }

    public ArrayList<DutchDetailMember> getMemberinfo() {
        return memberinfo;
    }

    public void setMemberinfo(ArrayList<DutchDetailMember> memberinfo) {
        this.memberinfo = memberinfo;
    }
}
