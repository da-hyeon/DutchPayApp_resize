package com.dutch.hdh.dutchpayapp;

import android.widget.ImageView;

public class Constants {

    public static final String LOG_TAG = "DUTCHPAY_LOG";
    public static final boolean LOG_PRINT = true;


    /**
     * Bundle ID
     */

    /**
     * Extra ID
     */
    //SelectRefundAcountActivity -> WithdrawalActivity
    public static final String ACCOUNT_TYPE_CODE = "accountTypeCode";
    public static final String ACCOUNT_NUMBER = "accountNumber";

    /**
     * API URL
     */
    public static final String IMAGE_URL = "image/";                                                //이미지 경로 URl

    public static final String USER_LOGIN_REQUEST_URL = "dutchpay_login.php";                       //로그인 요청

    public static final String USER_REGISTER_REQUEST_URL = "dutchpay_register3.php";                //회원가입 요청

    public static final String CREATE_GROUP_REQUEST_URL = "dutchpay_groupapplication.php";          //그룹생성 요청
    public static final String SELECT_GROUP_REQUEST_URL = "dutchpay_groupselect.php";               //그룹리스트 요청
    public static final String DELETE_GROUP_REQUEST_URL = "dutchpay_groupdelete.php";               //그룹삭제 요청
    public static final String UPDATE_GROUP_REQUEST_URL = "dutchpay_groupupdate.php";               //그룹삭제 요청

    public static final String SELECT_EVENT_ONGOING_REQUEST_URL = "dutchpay_event.php";             //진행중인 이벤트 목록 요청
    public static final String SELECT_EVENT_ENDPROGRESS_REQUEST_URL = "dutchpay_endevent.php";      //진행중인 이벤트 목록 요청

    public static final String CHANGE_EMAIL_REQUEST_URL = "dutchpay_emailchange.php";               //이메일 변경 요청
    public static final String CHANGE_PASSWORD_REQUEST_URL = "dutchpay_pwchange.php";               //비밀번호 변경 요청
    public static final String CHANGE_PHONENUMBER_REQUEST_URL = "dutchpay_phonechange.php";         //전화번호 변경 요청

    public static final String SEARCH_EMAIL_REQUEST_URL = "dutchpay_searchemail.php";               //아이디 찾기 요청
    public static final String SEARCH_PHONENUMBER_REQUEST_URL = "dutchpay_searchpw.php";         //비밀번호 찾기 요청

    public static final String SELECT_ACCOUNT_REQUEST_URL = "dutchpay_accountselect.php";         //등록된 계좌 요청


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
}
