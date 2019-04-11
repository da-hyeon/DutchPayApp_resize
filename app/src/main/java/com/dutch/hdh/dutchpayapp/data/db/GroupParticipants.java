package com.dutch.hdh.dutchpayapp.data.db;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupParticipants implements Parcelable {
    private String name;
    private String phoneNumber;

    public GroupParticipants(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    protected GroupParticipants(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<GroupParticipants> CREATOR = new Creator<GroupParticipants>() {
        @Override
        public GroupParticipants createFromParcel(Parcel in) {
            return new GroupParticipants(in);
        }

        @Override
        public GroupParticipants[] newArray(int size) {
            return new GroupParticipants[size];
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
