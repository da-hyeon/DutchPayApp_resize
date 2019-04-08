package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

public class TempConfirmListModel {
    String name;
    String phome;
    String cost;
    boolean completeFlag;

    public TempConfirmListModel(String name, String phome, String cost, boolean completeFlag) {
        this.name = name;
        this.phome = phome;
        this.cost = cost;
        this.completeFlag = completeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhome() {
        return phome;
    }

    public void setPhome(String phome) {
        this.phome = phome;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }
}
