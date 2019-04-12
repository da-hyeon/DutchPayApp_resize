package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.text.Editable;
import android.text.TextWatcher;

public class TempNewListModel {
    String name;
    String cost;
    String phone;
    boolean completeFlag;

    boolean editableFlag;
    boolean editedCheck;

    TextWatcher tw;

    public TempNewListModel(String name, String Cost, String phone, boolean completeFlag) {
        this.name = name;
        this.cost = Cost;
        this.phone = phone;
        this.completeFlag = completeFlag;
        this.editableFlag = false;
        this.editedCheck = false;
        this.tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cost = s.toString();
                editedCheck = true;
            }
        };
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

    public TextWatcher getTw() {
        return tw;
    }
}
