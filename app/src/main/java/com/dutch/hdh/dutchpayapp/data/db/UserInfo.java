package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("user_Code")
    private String userCode;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("user_Name")
    private String userName;

    @SerializedName("security_Number")
    private String userSecurityNumber;

    @SerializedName("phone")
    private String userPhone;

    @SerializedName("status")
    private String userStatus;

    @SerializedName("easy_Pwd")
    private String userEasyPassword;

    @SerializedName("user_Pwd")
    private String userPassword;

    @SerializedName("myPoint")
    private int userMoney;

    private boolean userState;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSecurityNumber() {
        return userSecurityNumber;
    }

    public void setUserSecurityNumber(String userSecurityNumber) {
        this.userSecurityNumber = userSecurityNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserEasyPassword() {
        return userEasyPassword;
    }

    public void setUserEasyPassword(String userEasyPassword) {
        this.userEasyPassword = userEasyPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }
}
