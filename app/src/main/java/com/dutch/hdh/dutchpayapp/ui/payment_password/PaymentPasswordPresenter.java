package com.dutch.hdh.dutchpayapp.ui.payment_password;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentPasswordPresenter implements PaymentPasswordContract.Presenter {

    private PaymentPasswordContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    private String mPassword;

    //개인결제
    private String mProductCode;
    private int mProductAmount;

    //더치페이
    private ArrayList<String> mDutchInfo;
    private String mDutchJson;

    //true = 개인결제 , false = 더치페이
    private boolean mPath;

    public PaymentPasswordPresenter(PaymentPasswordContract.View mView, Context mContext, FragmentManager mFragmentManager , Bundle bundle) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        if(bundle != null){
            //공용
            mPath = bundle.getBoolean(Constants.ENTRANCE_PATH);

            //개인결제
            mProductCode = bundle.getString(Constants.PRODUCT_CODE);
            mProductAmount = bundle.getInt(Constants.PRODUCT_AMOUNT);


            //더치페이
            mDutchInfo = bundle.getStringArrayList(Constants.PAYMENT_INFO);
            mDutchJson = bundle.getString(Constants.PAYMENT_LIST_JSON);

        }
        mMyApplication = MyApplication.getInstance();
        mPassword = "";
    }

    @Override
    public void initRandomNumber() {
        //난수생성
        ArrayList<Integer> randomNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomNumber.add(i);
        }

        Collections.shuffle(randomNumber);
        for (int i = 0; i < 10; i++) {
            mView.showRandomNumber(i, randomNumber.get(i).toString());
        }
    }

    @Override
    public void clickNumber(String numberText) {
        if (mPassword.length() < 6) {
            mPassword += numberText;
        }

        for (int i = 0; i < 6; i++) {
            if (i < mPassword.length()) {
                mView.dotImagesUpdate(i, true);
            } else {
                mView.dotImagesUpdate(i, false);
            }
        }
    }

    @Override
    public void clickDeleteButton() {
        mPassword = "";

        for (int i = 0; i < 6; i++) {
            mView.dotImagesUpdate(i, false);
        }
    }

    @Override
    public void clickOKButton() {
        if(isEmpty()) {
            mView.showFailDialog("실패", "결제 비밀번호는 6자리 입니다.");
        } else {
            if (isSame()) {
                //입장경로 - 개인결제
                if (mPath) {
                    Call<String> productPayment;
                    productPayment = MyApplication
                            .getRestAdapter()
                            .updateQRCodePayment(mProductCode,
                                    mMyApplication.getUserInfo().getUserCode());


                    productPayment.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            if (response.isSuccessful()) {

                                //오류검출
                                if (response.body() != null && response.body().equals("alreadyPayment")) {
                                    mView.showFailDialog("실패", "해당 상품은 이미 결제가 완료 되었습니다.");
                                    mFragmentManager.popBackStack();
                                    return;
                                }

                                mView.showSuccessDialog("성공", "결제 완료");
                                mMyApplication.getUserInfo().setUserMoney(mMyApplication.getUserInfo().getUserMoney() - mProductAmount);

                            }
                            //DB 접근 오류
                            else {
                                mView.showFailDialog("실패", "해당 상품을 찾을 수 없습니다.");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                            //서버통신 오류
                            mView.showFailDialog("실패", "서버와 통신할 수 없습니다.");
                        }
                    });
                }
                //입장경로 - 더치페이
                else {
                    //데이터 분석
                    String dutchTitle = mDutchInfo.get(0);
                    int dutchCost = Integer.parseInt(mDutchInfo.get(3));
                    String dutchContent = mDutchInfo.get(1);
                    String dutchMessage = mDutchInfo.get(2);

                    //더치페이 개설 및 금액 결제
                    Call<Void> setnewDutchpay = MyApplication
                            .getRestAdapter()
                            .setNewDutchpay(Integer.parseInt(mMyApplication.getUserInfo().getUserCode()),
                                    dutchTitle,
                                    dutchCost,
                                    dutchContent,
                                    dutchMessage,
                                    mDutchJson);

                    setnewDutchpay.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.e("response",response.message());

                            mView.showSuccessDialog("성공", "결제 완료");
                            mMyApplication.getUserInfo().setUserMoney(mMyApplication.getUserInfo().getUserMoney() - dutchCost);

                            //더치페이 완료
                            mMyApplication.setDutchpayGroup(false);
                            mMyApplication.setDutchpayNewFragment(null);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            //서버통신 오류
                            mView.showFailDialog("실패", "서버와 통신할 수 없습니다.");
                        }
                    });
                }
            }

            else {

                mView.showFailDialog("실패", "결제 비밀번호가 틀렸습니다.");
            }
        }
    }

    /**
     * 비밀번호 6자리 입력 조회
     */
    private boolean isEmpty(){
        if(mPassword.length() < 6){
            return true;
        }
        return false;
    }

    /**
     * 비밀번호가 같은지 조회
     */
    private boolean isSame(){
        if(mMyApplication.getUserInfo().getUserEasyPassword().equals(mPassword)){
            return true;
        }
        return false;
    }


}
