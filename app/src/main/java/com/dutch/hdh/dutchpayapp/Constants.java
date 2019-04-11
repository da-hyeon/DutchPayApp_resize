package com.dutch.hdh.dutchpayapp;

public class Constants {

    /**
     * API URL
     * */
    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";        //로그인 요청
    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register3.php";        //회원가입 요청

    /**
     *  Dutchpay State
     */
    public static final int DUTCHPAY_STATE_WAIT = 0;
    public static final int DUTCHPAY_STATE_REQUEST = 1;
    public static final int DUTCHPAY_STATE_COMPLETE = 2;
    public static final int DUTCHPAY_STATE_CANCEL = 3;
}
