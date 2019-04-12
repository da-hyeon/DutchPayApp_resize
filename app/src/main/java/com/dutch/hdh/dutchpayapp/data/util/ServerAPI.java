package com.dutch.hdh.dutchpayapp.data.util;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.EventList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerAPI {

    /**
     * 로그인 요청
     *
     * @param userEmail
     * @param userPassword
     * @return
     */
    @GET(Constants.USER_LOGIN_REQUEST_URL)
    Call<UserInfo> getFineUser(@Query("userEmail") String userEmail, @Query("userPassword") String userPassword);

    /**
     * 회원가입 요청
     * @param userName
     * @param userEmail
     * @param userPassword
     * @param userEasyPassword
     * @param userRN
     * @param userPhone
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.USER_REGISTER_REQUEST_URL)
    Call<UserInfo> setUserRegister(
            @Field("username") String userName,
            @Field("email") String userEmail,
            @Field("password") String userPassword,
            @Field("easypassword") String userEasyPassword,
            @Field("security_number") String userRN,
            @Field("phone") String userPhone);


    /**
     * 그룹생성 요청
     * @param groupaname
     * @param usercode
     * @param groupcontent
     * @param peoplenumber
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CREATE_GROUP_REQUEST_URL)
    Call<Void> createGroup(
            @Field("groupaname") String groupaname ,
            @Field("usercode") String usercode ,
            @Field("groupcontent") String groupcontent ,
            @Field("peoplenumber") String peoplenumber);


    /**
     * 그룹목록 요청
     * @param usercode
     * @return
     */
    @GET(Constants.SELECT_GROUP_REQUEST_URL)
    Call<MyGroup> getGroupList(@Query("usercode") String usercode);

    /**
     * 그룹삭제 요청
     * @param groupacode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.DELETE_GROUP_REQUEST_URL)
    Call<Void> deleteGroup(
            @Field("groupacode") String groupacode);



    /**
     * 등록할 카드목록가져오기
     */
    @GET(Constants.DUTCHPAY_CARD_COMPANY_SELECT)
    Call<CardCompanyList> getCardSelectList();

    /**
     * 카드등록 요청
     *
     * @param cardno
     * @param cardtypecode
     * @param usercode
     */
    @FormUrlEncoded
    @POST(Constants.DUTCHPAY_CARD_REGISTER)
    Call<Void> setCardRegister(
            @Field("cardno") String cardno,
            @Field("cardtypecode") String cardtypecode,
            @Field("usercode") String usercode);

    /**
     * 등록한 카드목록 요청
     *
     * @param usercode
     */
    @GET(Constants.DUTCHPAY_CARD_REGISTER_SELECT)
    Call<CardRegisterList> getRegisterCardList(
            @Query("usercode") String usercode);


    /**
     * 카드삭제 요청
     *
     * @param cardcode
     */
    @GET(Constants.DUTCHPAY_CARD_DELETE)
    Call<Void> setCardDelete(
            @Query("cardcode") String cardcode);


    /**
     * 그룹 업데이트 요청
     * @param groupacode
     * @param groupcontent
     * @param peoplenumber
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.UPDATE_GROUP_REQUEST_URL)
    Call<Void> updateGroup(
            @Field("groupacode") String groupacode ,
            @Field("groupaname") String groupaname ,
            @Field("groupcontent") String groupcontent ,
            @Field("peoplenumber") String peoplenumber);

    /**
     * 진행중 이벤트 요청
     * @return
     */
    @GET(Constants.SELECT_EVENT_ONGOING_REQUEST_URL)
    Call<EventList> selectOnGoingEvent();

    /**
     * 진행종료 이벤트 요청
     * @return
     */
    @GET(Constants.SELECT_EVENT_ENDPROGRESS_REQUEST_URL)
    Call<EventList> selectEndProgressEvent();

    /**
     * 이메일 변경 요청
     * @param email
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_EMAIL_REQUEST_URL)
    Call<Void> changeEmail(@Field("email") String email ,
                              @Field("usercode") String usercode );

    /**
     * 비밀번호 변경 요청
     * @param password
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PASSWORD_REQUEST_URL)
    Call<Void> changePassword(@Field("password") String password ,
                           @Field("usercode") String usercode );

    /**
    * 더치페이 내역 요청
     * @param usercode
     */
    @FormUrlEncoded
    @POST(Constants.DUTCHPAY_HISTORY_REQUEST_URL)
    Call<Dutchpayhistory> getDutchapyHistoryList(@Field("usercode") String usercode);

     /**
     * 전화번호 변경 요청
     * @param phone
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PHONENUMBER_REQUEST_URL)
    Call<Void> changePhoneNumber(@Field("phone") String phone ,
                              @Field("usercode") String usercode );
}
