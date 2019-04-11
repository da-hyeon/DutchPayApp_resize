package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

public class TempNewListModel {
    String name;
    String cost;
    String phone;
    boolean completeFlag;

    boolean editableFlag;
    boolean editedCheck;

    public TempNewListModel(String name, String cost, String phone, boolean completeFlag) {
        this.name = name;
        this.cost = cost;
        this.phone = phone;
        this.completeFlag = completeFlag;
        this.editableFlag = false;
        this.editedCheck = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }

    public boolean isEditableFlag() {
        return editableFlag;
    }

    public void setEditableFlag(boolean editableFlag) {
        this.editableFlag = editableFlag;
    }

    public boolean isEditedCheck() {
        return editedCheck;
    }

    public void setEditedCheck(boolean editedCheck) {
        this.editedCheck = editedCheck;
    }
}
