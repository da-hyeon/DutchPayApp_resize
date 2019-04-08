package com.dutch.hdh.dutchpayapp.ui.dutchpay.detail;

public class TempDetailListModel {
    String name;
    boolean imageFlag;
    String coast;
    int state;

    public TempDetailListModel(String name, boolean imageFlag, String coast, int state) {
        this.name = name;
        this.imageFlag = imageFlag;
        this.coast = coast;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(boolean imageFlag) {
        this.imageFlag = imageFlag;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
