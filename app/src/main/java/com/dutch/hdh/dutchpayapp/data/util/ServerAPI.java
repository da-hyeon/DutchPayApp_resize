package com.dutch.hdh.dutchpayapp.data.util;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.data.db.AccountList;
import com.dutch.hdh.dutchpayapp.data.db.EventList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.data.db.SearchEmail;
import com.dutch.hdh.dutchpayapp.data.db.SearchPassword;
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
     *
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
     *
     * @param groupaname
     * @param usercode
     * @param groupcontent
     * @param peoplenumber
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CREATE_GROUP_REQUEST_URL)
    Call<Void> createGroup(
            @Field("groupaname") String groupaname,
            @Field("usercode") String usercode,
            @Field("groupcontent") String groupcontent,
            @Field("peoplenumber") String peoplenumber);


    /**
     * 그룹목록 요청
     *
     * @param usercode
     * @return
     */
    @GET(Constants.SELECT_GROUP_REQUEST_URL)
    Call<MyGroup> getGroupList(@Query("usercode") String usercode);

    /**
     * 그룹삭제 요청
     *
     * @param groupacode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.DELETE_GROUP_REQUEST_URL)
    Call<Void> deleteGroup(
            @Field("groupacode") String groupacode);


    /**
     * 그룹 업데이트 요청
     *
     * @param groupacode
     * @param groupcontent
     * @param peoplenumber
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.UPDATE_GROUP_REQUEST_URL)
    Call<Void> updateGroup(
            @Field("groupacode") String groupacode,
            @Field("groupaname") String groupaname,
            @Field("groupcontent") String groupcontent,
            @Field("peoplenumber") String peoplenumber);

    /**
     * 진행중 이벤트 요청
     *
     * @return
     */
    @GET(Constants.SELECT_EVENT_ONGOING_REQUEST_URL)
    Call<EventList> selectOnGoingEvent();

    /**
     * 진행종료 이벤트 요청
     *
     * @return
     */
    @GET(Constants.SELECT_EVENT_ENDPROGRESS_REQUEST_URL)
    Call<EventList> selectEndProgressEvent();

    /**
     * 이메일 변경 요청
     *
     * @param email
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_EMAIL_REQUEST_URL)
    Call<Void> changeEmail(@Field("email") String email,
                           @Field("usercode") String usercode);

    /**
     * 비밀번호 변경 요청
     *
     * @param password
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PASSWORD_REQUEST_URL)
    Call<Void> changePassword(@Field("password") String password,
                              @Field("usercode") String usercode);

    /**
     * 전화번호 변경 요청
     *
     * @param phone
     * @param usercode
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.CHANGE_PHONENUMBER_REQUEST_URL)
    Call<Void> changePhoneNumber(@Field("phone") String phone,
                                 @Field("usercode") String usercode);

    /**
     * 이메일 찾기 요청
     *
     * @param name
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.SEARCH_EMAIL_REQUEST_URL)
    Call<SearchEmail> findEmail(@Field("name") String name,
                                @Field("phone") String phone);

    /**
     * 비밀번호 찾기 요청
     *
     * @param email
     * @param name
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.SEARCH_PHONENUMBER_REQUEST_URL)
    Call<SearchPassword> findPhoneNumber(@Field("email") String email,
                                         @Field("name") String name,
                                         @Field("phone") String phone);

    /**
     * 등록된 계좌 요청
     *
     * @param usercode
     * @return
     */
    @GET(Constants.SELECT_ACCOUNT_REQUEST_URL)
    Call<AccountList> selectAccount(@Query("usercode") String usercode);

}
