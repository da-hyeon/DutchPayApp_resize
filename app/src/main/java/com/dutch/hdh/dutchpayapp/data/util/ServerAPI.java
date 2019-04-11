package com.dutch.hdh.dutchpayapp.data.util;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.data.db.EventList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
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
     */
    @GET(Constants.USER_LOGIN_REQUEST_URL)
    Call<UserInfo> getFineUser(@Query("userEmail") String userEmail, @Query("userPassword") String userPassword);

    /**
     * 회원가입 요청
     * @param userName
     * @param userEmail
     * @param userPassword
<<<<<<< HEAD
     * @param userEasyPassword
     * @param userRN
     * @param userPhone
     * @return
=======
>>>>>>> sungguen
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
<<<<<<< HEAD
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


    @FormUrlEncoded
    @POST(Constants.UPDATE_GROUP_REQUEST_URL)
    Call<Void> updateGroup(
            @Field("groupacode") String groupacode ,
            @Field("groupcontent") String groupcontent ,
            @Field("peoplenumber") String peoplenumber);

    @GET(Constants.SELECT_EVENT_ONGOING_REQUEST_URL)
    Call<EventList> selectOnGoingEvent();

    @GET(Constants.SELECT_EVENT_ENDPROGRESS_REQUEST_URL)
    Call<EventList> selectEndProgressEvent();

    /**
     * 등록할 카드목록가져오기
     */
    @GET(Constants.DUTCHPAY_CARD_SELECT)
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

}
