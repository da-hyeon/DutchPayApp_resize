package com.dutch.hdh.dutchpayapp;

public class Constants {

    /**
     * API URL
     * */
    public static final String IMAGE_URL = "image/";

    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";                       //로그인 요청

    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register3.php";                //회원가입 요청

    public static final String CREATE_GROUP_REQUEST_URL = "dutchpay_groupapplication.php";          //그룹생성 요청
    public static final String SELECT_GROUP_REQUEST_URL = "dutchpay_groupselect.php";               //그룹리스트 요청
    public static final String DELETE_GROUP_REQUEST_URL = "dutchpay_groupdelete.php";               //그룹삭제 요청
    public static final String UPDATE_GROUP_REQUEST_URL = "dutchpay_groupupdate.php";               //그룹삭제 요청

    public static final String SELECT_EVENT_ONGOING_REQUEST_URL = "dutchpay_event.php";               //진행중인 이벤트 목록 요청
    public static final String SELECT_EVENT_ENDPROGRESS_REQUEST_URL = "dutchpay_endevent.php";               //진행중인 이벤트 목록 요청

    public static final String CHANGE_EMAIL_REQUEST_URL = "dutchpay_emailchange.php";               //이메일 변경 요청
    public static final String CHANGE_PASSWORD_REQUEST_URL = "dutchpay_pwchange.php";               //비밀번호 변경 요청
    public static final String CHANGE_PHONENUMBER_REQUEST_URL = "dutchpay_phonechange.php";               //전화번호 변경 요청
}
