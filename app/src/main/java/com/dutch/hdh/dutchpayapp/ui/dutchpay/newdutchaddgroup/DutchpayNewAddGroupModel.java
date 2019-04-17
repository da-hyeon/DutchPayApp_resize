package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

public class DutchpayNewAddGroupModel {

    String Gname;
    int Gicon;
    boolean Gcheck;
    String Gmember;

    int memNum;

    public DutchpayNewAddGroupModel(String gname, int gicon, int memNum, String gmember) {
        this.Gname = gname;
        this.Gicon = gicon;
        this.Gcheck = false;
        this.memNum = memNum;
        this.Gmember = gmember;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public int getGicon() {
        return Gicon;
    }

    public void setGicon(int gicon) {
        Gicon = gicon;
    }

    public boolean isGcheck() {
        return Gcheck;
    }

    public void setGcheck(boolean gcheck) {
        Gcheck = gcheck;
    }

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }

    public String getGmember() {
        return Gmember;
    }

    public void setGmember(String gmember) {
        Gmember = gmember;
    }
}
