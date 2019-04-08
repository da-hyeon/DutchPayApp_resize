package com.dutch.hdh.dutchpayapp.data.db;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;

public class DirectInputParticipants implements Parcelable {
    private String name;
    private TextWatcher nameTextWatcher;

    private String phoneNumber;
    private TextWatcher phoneNumberWatcher;


    public DirectInputParticipants() {
        name = "";
        phoneNumber = "";

        //EditText 변경 리스너 생성
        nameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //변경된 값을 저장한다
                name = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        //EditText 변경 리스너 생성
        phoneNumberWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //변경된 값을 저장한다
                phoneNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

    }

    protected DirectInputParticipants(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<DirectInputParticipants> CREATOR = new Creator<DirectInputParticipants>() {
        @Override
        public DirectInputParticipants createFromParcel(Parcel in) {
            return new DirectInputParticipants(in);
        }

        @Override
        public DirectInputParticipants[] newArray(int size) {
            return new DirectInputParticipants[size];
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

    public TextWatcher getNameTextWatcher() {
        return nameTextWatcher;
    }

    public void setNameTextWatcher(TextWatcher nameTextWatcher) {
        this.nameTextWatcher = nameTextWatcher;
    }

    public TextWatcher getPhoneNumberWatcher() {
        return phoneNumberWatcher;
    }

    public void setPhoneNumberWatcher(TextWatcher namePhoneNumberWatcher) {
        this.phoneNumberWatcher = namePhoneNumberWatcher;
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
