package com.dutch.hdh.dutchpayapp.data.db;

public class TelephoneDirectory {
    private String name;
    private String phoneNumber;
    private boolean checkState;

    public TelephoneDirectory(String name, String phoneNumber, boolean checkState) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.checkState = checkState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isCheckState() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }
}
