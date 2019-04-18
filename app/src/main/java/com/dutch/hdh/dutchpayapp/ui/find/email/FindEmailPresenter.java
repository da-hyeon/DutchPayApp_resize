package com.dutch.hdh.dutchpayapp.ui.find.email;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.SearchEmail;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindEmailPresenter implements FindEmailContract.Presenter {

    private FindEmailContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;

    private String mAuthNumber;

    /**
     * 생성자
     */
    FindEmailPresenter(FindEmailContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 취소 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickCancel() {
        mView.showWarningDialog("경고", "현재 입력된 정보는 저장되지 않습니다. \n 정말 나가시겠습니까?");
        //mView.finishActivity();
    }

    /**
     * 확인 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickOKButton(EditText[] mEditTextArray) {
        if (!isEmpty(mEditTextArray)) {
            //인증번호가 일치하는지
            if (isAuthNumberSame(mEditTextArray[2].getText().toString())) {
                mView.hideKeyboard();

                Call<SearchEmail> findEmail = MyApplication
                        .getRestAdapter()
                        .findEmail(mEditTextArray[0].getText().toString(), mEditTextArray[1].getText().toString());

                findEmail.enqueue(new Callback<SearchEmail>() {
                    @Override
                    public void onResponse(@NonNull Call<SearchEmail> call, @NonNull Response<SearchEmail> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SearchEmail email = response.body();
                                mView.showEmailLayout();
                                mView.changeEmailText(email.getEmail());
                            } else {
                                mView.showFailDialog("실패", "이메일을 찾을 수 없습니다.");
                            }
                        }
                    }


                    @Override
                    public void onFailure(@NonNull Call<SearchEmail> call, @NonNull Throwable t) {
                        mView.showFailDialog("실패", t.getMessage());
                    }
                });
            } else {
                mView.showFailDialog("실패", "인증번호가 일치하지 않습니다.");
            }
        } else {
            mView.showFailDialog("실패", "빈칸을 채워주세요.");
        }
    }


    /**
     * 인증번호요청 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAuthNumber(String phoneNumber) {

        mAuthNumber = numberGen();
        String content = "[더치페이코리아] 인증번호 [" + mAuthNumber + "] 입니다. 정확히 입력해 주시기 바랍니다.";

        if (isPhoneType(phoneNumber)) {
            try {
                //전송
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, content, null, null);
                mView.showSuccessDialog("성공", "인증번호를 요청했습니다.");
            } catch (Exception e) {
                mView.showFailDialog("실패", "인증번호를 요청할 수 없습니다.");
                e.printStackTrace();
            }
        } else {
            mView.showFailDialog("실패", "핸드폰 번호를 정확히 입력해주세요.");
        }

    }

    /**
     * Edittext의 빈칸이 있는지 확인
     */
    private boolean isEmpty(EditText[] mEditTextArray) {
        for (EditText editTexts : mEditTextArray) {
            if (editTexts.getText().toString().equals(""))
                return true;
        }
        return false;
    }


    /**
     * 6자리 난수생성
     */
    private static String numberGen() {

        Random rand = new Random();
        StringBuilder numStr = new StringBuilder(); //난수가 저장될 변수

        for (int i = 0; i < 6; i++) {

            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));

            numStr.append(ran);
        }
        return numStr.toString();
    }

    /**
     * 입력한 숫자가 핸드폰번호 타입인지 체크
     */
    private boolean isPhoneType(String phoneNumber) {
        return phoneNumber.length() >= 10 && phoneNumber.length() <= 11;
    }

    /**
     * 요청한 인증번호 값과 일치하는지 확인.
     */
    private boolean isAuthNumberSame(String authNumber) {
        if (mAuthNumber != null) {
            return mAuthNumber.equals(authNumber);
        }
        return false;
    }

}
