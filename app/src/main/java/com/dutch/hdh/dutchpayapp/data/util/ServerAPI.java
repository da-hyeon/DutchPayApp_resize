package com.dutch.hdh.dutchpayapp.data.util;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
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
    Call<UserInfo> sendUserRegister(
            @Field("data") String data);

}
