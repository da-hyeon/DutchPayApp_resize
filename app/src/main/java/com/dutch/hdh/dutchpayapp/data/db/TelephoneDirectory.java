package com.dutch.hdh.dutchpayapp.data.db;

import android.os.Parcel;
import android.os.Parcelable;

public class TelephoneDirectory implements Parcelable {
    private String name;
    private String phoneNumber;
    private boolean checkState;

    public TelephoneDirectory(String name, String phoneNumber, boolean checkState) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.checkState = checkState;
    }

    protected TelephoneDirectory(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        checkState = in.readByte() != 0;
    }

    public static final Creator<TelephoneDirectory> CREATOR = new Creator<TelephoneDirectory>() {
        @Override
        public TelephoneDirectory createFromParcel(Parcel in) {
            return new TelephoneDirectory(in);
        }

        @Override
        public TelephoneDirectory[] newArray(int size) {
            return new TelephoneDirectory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
    }
}
