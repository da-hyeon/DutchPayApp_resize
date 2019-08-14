package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class TempNewListModel {
    String name;
    String cost;
    String phone;

    boolean completeFlag;

    boolean editableFlag;
    boolean editedCheck;

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.toString().equals("") || Integer.parseInt(s.toString()) == 0){
                cost = "0";
            } else {
                cost = s.toString();
            }

            editedCheck = true;
        }
    };;

    public TempNewListModel(String name, String phone) {
        this.name = name;
        this.cost = "0";
        this.phone = phone;
        this.completeFlag = false;
        this.editableFlag = false;
        this.editedCheck = false;
        this.tw =  new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().equals("") || Integer.parseInt(s.toString()) == 0){
                    cost = "0";
                } else {
                    cost = s.toString();
                }

                editedCheck = true;
            }
        };
    }

    public TempNewListModel(String name, String phone, String cost, boolean completeFlag, boolean editableFlag, boolean editedCheck) {
        this.name = name;
        this.cost = cost;
        this.phone = phone;
        this.completeFlag = completeFlag;
        this.editableFlag = editableFlag;
        this.editedCheck = editedCheck;
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
