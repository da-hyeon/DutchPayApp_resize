package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPassword")
    private String userPassword;

    @SerializedName("userPaymentPassword")
    private String userPaymentPassword;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userRN")
    private int userRN;

    @SerializedName("userGender")
    private int userGender;

    @SerializedName("userPhone")
    private String userPhone;

    @SerializedName("userMoney")
    private int userMoney;

    private boolean userState;

    public UserInfo() {

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPaymentPassword() {
        return userPaymentPassword;
    }

    public void setUserPaymentPassword(String userPaymentPassword) {
        this.userPaymentPassword = userPaymentPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserRN() {
        return userRN;
    }

    public void setUserRN(int userRN) {
        this.userRN = userRN;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }

    public boolean isUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPaymentPassword='" + userPaymentPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userRN=" + userRN +
                ", userGender=" + userGender +
                ", userPhone='" + userPhone + '\'' +
                ", userMoney=" + userMoney +
                ", userState=" + userState +
                '}';
    }
}
