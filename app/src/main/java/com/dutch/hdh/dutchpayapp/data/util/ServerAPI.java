package com.dutch.hdh.dutchpayapp.data.util;

import com.dutch.hdh.dutchpayapp.Constants;
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
     * 로그인 요청
     *
     * @param userEmail
     * @param userPassword
     */
    @FormUrlEncoded
    @POST(Constants.USER_REGISTER_REQUEST_URL)
    Call<Void> setUserRegister(
            @Field("username") String userName,
            @Field("email") String userEmail,
            @Field("password") String userPassword,
            @Field("easypassword") String userEasyPassword,
            @Field("security_number") String userRN,
            @Field("phone") String userPhone);


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
