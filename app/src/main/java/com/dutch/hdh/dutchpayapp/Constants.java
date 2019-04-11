package com.dutch.hdh.dutchpayapp;

public class Constants {

    /**
     * API URL
     */
    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";                        //로그인 요청
    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register.php";                  //회원가입 요청

    public static final String DUTCHPAY_CARD_SELECT = "dutchpay_cardselect.php";                     //등록할 카드목록 가져오기
    public static final String DUTCHPAY_CARD_REGISTER = "dutchpay_cardregister.php";                 //카드등록
    public static final String DUTCHPAY_CARD_REGISTER_SELECT = "dutchpay_registeredcardselect.php";  //등록한 카드목록 가져오기
    public static final String DUTCHPAY_CARD_DELETE = "dutchpay_carddelete.php";                     //카드 삭제


    /**
     * Dutchpay State
     */
    public static final int DUTCHPAY_STATE_WAIT = 0;
    public static final int DUTCHPAY_STATE_REQUEST = 1;
    public static final int DUTCHPAY_STATE_COMPLETE = 2;
    public static final int DUTCHPAY_STATE_CANCEL = 3;


    /**
     * Test userCode
     */
    public static final String USER_CODE = "5";

}
