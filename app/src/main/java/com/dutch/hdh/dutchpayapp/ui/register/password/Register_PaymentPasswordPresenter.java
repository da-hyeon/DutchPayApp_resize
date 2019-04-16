package com.dutch.hdh.dutchpayapp.ui.register.password;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.ui.register.success.Register_SuccessFragment;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_PaymentPasswordPresenter implements Register_PaymentPasswordContract.Presenter {

    private Register_PaymentPasswordContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication myApplication;

    //비밀번호 확인
    private String mPassword;
    private String mPasswordCheck;

    // false = 비밀번호 1차입력 , true = 비밀번호 2차 입력
    private boolean isPasswordCheck;

    Register_PaymentPasswordPresenter(Register_PaymentPasswordContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mPassword = "";
        isPasswordCheck = false;
        myApplication = MyApplication.getInstance();
    }

    @Override
    public void getData(Bundle bundle) {

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
        if (!isPasswordCheck) {
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
        } else {
            if (mPasswordCheck.length() < 6) {
                mPasswordCheck += numberText;
            }

            for (int i = 0; i < 6; i++) {
                if (i < mPasswordCheck.length()) {
                    mView.dotImagesUpdate(i, true);
                } else {
                    mView.dotImagesUpdate(i, false);
                }
            }
        }
    }

    @Override
    public void clickDeleteButton() {
        if (!isPasswordCheck) {
            mPassword = "";
        } else {
            mPasswordCheck = "";
        }

        for (int i = 0; i < 6; i++) {
            mView.dotImagesUpdate(i, false);
        }
    }

    @Override
    public void clickOKButton(Bundle bundle) {

        if (!isPasswordCheck) {
            if (mPassword.length() == 6) {
                isPasswordCheck = true;
                mView.updateView();
                mView.changeTitle("결제시 이용할 암호를 한번 더 입력해주세요.");
                mView.changeMiddleTitle("결제 비밀번호 확인");
                clickDeleteButton();

            } else {
                mView.showFailDialog("결제 비밀번호는 6자리 입니다.");
            }
        } else {
            if (mPasswordCheck.length() == 6) {
                if (isSame()) {

                    String userName = bundle.getString("userName");
                    String userEmail = bundle.getString("userEmail");
                    String userPassword = bundle.getString("userPassword");
                    String userRN = bundle.getString("userRN");
                    String userPhone = bundle.getString("userPhone");

                    Call<UserInfo> userRegister = MyApplication
                            .getRestAdapter()
                            .setUserRegister(userName,
                                    userEmail,
                                    userPassword,
                                    mPassword ,
                                    userRN,
                                    userPhone);

                    userRegister.enqueue(new Callback<UserInfo>() {
                        @Override
                        public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                            myApplication.setUserInfo(response.body());
                            myApplication.getUserInfo().setUserState(true);

                            mView.showSuccessDialog("회원가입이 완료 되었습니다.");
                            myApplication.getUserInfo().setUserState(true);
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                            Log.d("fail" , t.getLocalizedMessage());
                            Log.d("fail" , t.getMessage());
                        }
                    });
                } else {
                    mView.showFailDialog("비밀번호가 맞지 않습니다.");
                    clickDeleteButton();
                }
            } else {
                mView.showFailDialog("결제 비밀번호는 6자리 입니다.");
            }
        }
    }

    /**
     * 회원가입 성공 다이얼로그 클릭 이벤트 처리
     */
    @Override
    public void clickSuccessDialog() {

        mView.removeAllExceptMains();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        Register_SuccessFragment register_successFragment = new Register_SuccessFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, register_successFragment , Register_SuccessFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_SuccessFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 비밀번호가 서로 같은지 조회
     */
    private boolean isSame() {
        return mPassword.equals(mPasswordCheck);
    }
}