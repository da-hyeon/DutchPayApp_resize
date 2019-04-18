package com.dutch.hdh.dutchpayapp;

public class Constants {


    /**
     * IMAGE BASE URL
     */
    public static final String BASE_IMAGE_URL = "https://dutchkor02.cafe24.com/image/";

    /**
     * API URL
     */
    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";                       //로그인 요청

    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register3.php";                //회원가입 요청

    public static final String CREATE_GROUP_REQUEST_URL = "dutchpay_groupapplication.php";          //그룹생성 요청
    public static final String SELECT_GROUP_REQUEST_URL = "dutchpay_groupselect.php";               //그룹리스트 요청
    public static final String DELETE_GROUP_REQUEST_URL = "dutchpay_groupdelete.php";               //그룹삭제 요청
    public static final String UPDATE_GROUP_REQUEST_URL = "dutchpay_groupupdate.php";               //그룹삭제 요청

    public static final String SELECT_EVENT_ONGOING_REQUEST_URL = "dutchpay_event.php";               //진행중인 이벤트 목록 요청
    public static final String SELECT_EVENT_ENDPROGRESS_REQUEST_URL = "dutchpay_endevent.php";               //진행중인 이벤트 목록 요청

    /**
     * 카드
     */
    public static final String DUTCHPAY_CARD_COMPANY_SELECT = "dutchpay_cardselect.php";             //등록할 카드회사 목록 가져오기
    public static final String DUTCHPAY_CARD_REGISTER = "dutchpay_cardregister.php";                 //카드등록
    public static final String DUTCHPAY_CARD_REGISTER_SELECT = "dutchpay_registeredcardselect.php";  //등록한 카드목록 가져오기
    public static final String DUTCHPAY_CARD_DELETE = "dutchpay_carddelete.php";                     //카드 삭제
    public static final String DUTCHPAY_CARD_REPRESENTATIVE_CARD = "dutchpay_cardchange.php";                     //대표카드 설정

    /**
     * 계좌
     */
    public static final String DUTCHPAY_ACCOUNT_BANK_SELECT = "dutchpay_accounttypeselect.php";             //등록할 은행 목록 가져오기
    public static final String DUTCHPAY_ACCOUNT_REGISTER = "dutchpay_accountregister.php";                 //계좌등록
    public static final String DUTCHPAY_ACCOUNT_REGISTER_SELECT = "dutchpay_registeredaccountselect.php";  //등록한 계좌목록 가져오기
    public static final String DUTCHPAY_ACCOUNT_DELETE = "dutchpay_accountdelete.php";                     //계좌 삭제
    public static final String DUTCHPAY_ACCOUNT_REPRESENTATIVE_ACCOUNT = "dutchpay_accountchange.php";        //대표계좌 설정

    /**
     * 주고받기
     */
    public static final String DUTCHPAY_POINT_SEND = "dutchpay_pointgive.php";                         //포인트 주기
    public static final String DUTCHPAY_POINT_RECEIVE = "dutchpay_pointupdate.php";                     //포인트 받기

    /**
     * 사용내역 목록 불러오기
     * */

    public static final String DUTCHPAY_PAY_ONE_WEEK = "dutchpay_payoneweek.php";                         //1주 사용내역
    public static final String DUTCHPAY_PAY_ONE_MONTH = "dutchpay_payonemonth.php";                       //1달 사용내역
    public static final String DUTCHPAY_PAY_3_MONTH = "dutchpay_pay3month.php";                           //3달 사용내역
    public static final String DUTCHPAY_PAY_HISTORY = "dutchpay_payhistory.php";                          //전체목록

    /**
     * Dutchpay State
     */
    public static final int DUTCHPAY_STATE_WAIT = 0;
    public static final int DUTCHPAY_STATE_REQUEST = 1;
    public static final int DUTCHPAY_STATE_COMPLETE = 2;
    public static final int DUTCHPAY_STATE_CANCEL = 3;


}
