package com.dutch.hdh.dutchpayapp;

public class Constants {


    /**
     * SharedPreferences
     */
    //File name
    public static final String  USER_INFOMATION = "userInfo"; //저장된 유저정보 파일이름

    //Data
    public static final String  USER_ID = "userID"; //저장된 유저ID
    public static final String  USER_PASSWORD = "userPassword"; //저장된 유저Password


    /**
     * ErrorCode
     */
    public static final String USER_WITHDRAWAL = "withdrawal"; //탈퇴한 회원 Msg

    /**
     * API URL
     */
    public static final String BASE_IMAGE_URL = "https://dutchkor02.cafe24.com/image/";

    /**

     * API URL
     */
    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";                       //로그인 요청

    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register3.php"; //회원가입 요청
    public static final String USER_DELETE_REQUEST_URL = "dutchpay_memberdelete.php"; //회원탈퇴 요청

    public static final String CREATE_GROUP_REQUEST_URL = "dutchpay_groupapplication.php"; //그룹생성 요청
    public static final String SELECT_GROUP_REQUEST_URL = "dutchpay_groupselect.php"; //그룹리스트 요청
    public static final String SELECT_GROUP_REQUEST_URL_2 = "dutchpay_groupselect1.php"; //그룹리스트(유저 ver) 요청
    public static final String DELETE_GROUP_REQUEST_URL = "dutchpay_groupdelete.php"; //그룹삭제 요청
    public static final String UPDATE_GROUP_REQUEST_URL = "dutchpay_groupupdate.php"; //그룹삭제 요청


    public static final String LOG_TAG = "DUTCHPAY_LOG";
    public static final boolean LOG_PRINT = true;


    /**
     * Bundle ID
     */
    //PaymentDialog + DutchpayNewConfirmFragment -> PaymentPasswordFragment
    public static final String ENTRANCE_PATH = "entrancePath";

    //PaymentDialog -> PaymentPasswordFragment
    public static final String PRODUCT_CODE = "productCode";
    public static final String PRODUCT_AMOUNT = "productAmount";

    //DutchpayNewConfirmFragment -> PaymentPasswordFragment
    public static final String PAYMENT_INFO = "dutchpayInfo";
    public static final String PAYMENT_LIST_JSON = "dutchpayGroupJson";
    public static final String PAY_USAGE_HISTORY = "payUsageHistory";


    /**
     * Extra ID
     */
    //SelectRefundAcountActivity -> WithdrawalActivity
    public static final String ACCOUNT_TYPE_CODE = "accountTypeCode";
    public static final String ACCOUNT_NUMBER = "accountNumber";

    //PaymentPassword -> ReceiptActivity
    public static final String PAYMENT_DATE = "date";
    public static final String PAYMENT_STORE_NAME = "storeName";
    public static final String PAYMENT_AMOUNT = "amount";
    public static final String PAYMENT_STORE_LOCATION = "storeLocation";

    /**
     * API URL
     */
    public static final String IMAGE_URL = "image/";                                                //이미지 경로 URl


    /**
     * 카드
     */
    public static final String DUTCHPAY_CARD_COMPANY_SELECT = "dutchpay_cardselect.php";             //등록할 카드회사 목록 가져오기
    public static final String DUTCHPAY_CARD_REGISTER = "dutchpay_cardregister.php";                 //카드등록
    public static final String DUTCHPAY_CARD_REGISTER_SELECT = "dutchpay_registeredcardselect.php";  //등록한 카드목록 가져오기
    public static final String DUTCHPAY_CARD_DELETE = "dutchpay_carddelete.php";                     //카드 삭제
    public static final String DUTCHPAY_CARD_REPRESENTATIVE_CARD = "dutchpay_cardchange.php";                     //대표카드 설정
    public static final String DUTCHPAY_HISTORY_REQUEST_URL = "dutchpay_dutchpaytotallist.php"; //더치페이 내역 요청
    public static final String DUTCHPAY_DETAIL_REQUEST_URL = "dutchpay_dutchpaydetail.php"; //더치페이 내역 상세 요청
    public static final String DUTCHPAY_NEW_REQUEST_URL = "dutchpay_dutchpay.php"; //더치페이 신설 요청
    public static final String DUTCHPAY_MEMBER_PAY_REQUEST_URL = "dutchpay_dutchpayinside.php"; //더치페이 포인트 지불 요청

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

    public static final String DUTCHPAY_PAY_USAGE_HISTORY = "dutchpay_totallist.php";                         //사용내역

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
    public static final String SELECT_EVENT_ONGOING_REQUEST_URL = "dutchpay_event.php";             //진행중인 이벤트 목록 요청
    public static final String SELECT_EVENT_ENDPROGRESS_REQUEST_URL = "dutchpay_endevent.php";      //진행중인 이벤트 목록 요청

    public static final String CHANGE_EMAIL_REQUEST_URL = "dutchpay_emailchange.php";               //이메일 변경 요청
    public static final String CHANGE_PASSWORD_REQUEST_URL = "dutchpay_pwchange.php";               //비밀번호 변경 요청
    public static final String CHANGE_PHONENUMBER_REQUEST_URL = "dutchpay_phonechange.php";         //전화번호 변경 요청

    public static final String SEARCH_EMAIL_REQUEST_URL = "dutchpay_searchemail.php";               //아이디 찾기 요청
    public static final String SEARCH_PHONENUMBER_REQUEST_URL = "dutchpay_searchpw.php";         //비밀번호 찾기 요청

    public static final String SELECT_ACCOUNT_REQUEST_URL = "dutchpay_accountselect.php";         //등록된 계좌 요청

    public static final String SELECT_PRODUCT_QRCODE_REQUEST_URL = "dutchpay_singlepayproductselect.php";         //상품정보 요청(QR코드 전용)
    public static final String SELECT_PRODUCT_PAYMENT_NUMBER_REQUEST_URL = "dutchpay_singlepayproductcode.php";         //상품정보 요청(결제번호 전용)

    public static final String UPDATE_PAYMENT_QRCODE_REQUEST_URL = "dutchpay_singlepay.php";         //상품 결제 요청 (포인트 차감)

    /**
     * 은행 이미지 경로
     */
    public static int backImageID(int num) {
        switch (num) {
            case 0:
                return R.drawable.bs_bank;

            case 1:
                return R.drawable.citi_bank;

            case 2:
                return R.drawable.dg_bank;

            case 3:
                return R.drawable.gj_bank;

            case 4:
                return R.drawable.gn_bank;

            case 5:
                return R.drawable.hana_bank;

            case 6:
                return R.drawable.ibk_bank;

            case 7:
                return R.drawable.jb_bank;

            case 8:
                return R.drawable.jj_bank;

            case 9:
                return R.drawable.k_bank;

            case 10:
                return R.drawable.kakao_bank;

            case 11:
                return R.drawable.kb_bank1;

            case 12:
                return R.drawable.kdb_bank;

            case 13:
                return R.drawable.nh_bank;

            case 14:
                return R.drawable.sc_bank;

            case 15:
                return R.drawable.sh_bank;

            case 16:
                return R.drawable.shinhan_bank;

            case 17:
                return R.drawable.wr_bank;

            default:
                return 100;
        }
    }

    /**
     * 은행 Color 경로
     */
    public static int backColorID(int num) {
        switch (num) {
            case 0:
                return R.color.textSelect;

            case 1:
                return R.color.textSelect;

            case 2:
                return R.color.textSelect;

            case 3:
                return R.color.textSelect;

            case 4:
                return R.color.textSelect;

            case 5:
                return R.color.hanaBank;

            case 6:
                return R.color.ibkBank;

            case 7:
                return R.color.textSelect;

            case 8:
                return R.color.textSelect;

            case 9:
                return R.color.textSelect;

            case 10:
                return R.color.textSelect;

            case 11:
                return R.color.kbBank;

            case 12:
                return R.color.textSelect;

            case 13:
                return R.color.nhBank;

            case 14:
                return R.color.textSelect;

            case 15:
                return R.color.textSelect;

            case 16:
                return R.color.shBank;

            case 17:
                return R.color.textSelect;

            default:
                return 100;
        }
    }

    /**
     * 더치페이 그룹 아이콘 경로
     */
    public static int groupIconID(int num) {
        switch (num) {
            case 0:
                return R.drawable.group_icon01;
            case 1:
                return R.drawable.group_icon02;
            case 2:
                return R.drawable.group_icon03;
            case 3:
                return R.drawable.group_icon04;
            case 4:
                return R.drawable.group_icon05;
            case 5:
                return R.drawable.group_icon06;
            default:
                return R.drawable.group_icon01;
        }
    }
}

