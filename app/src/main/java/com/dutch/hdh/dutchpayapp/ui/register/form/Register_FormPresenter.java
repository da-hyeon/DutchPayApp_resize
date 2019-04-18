package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;

import java.util.Random;

public class Register_FormPresenter implements Register_FormContract.Presenter {

    private Register_FormContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private EditText mRegisterEditText[];

    private String mAuthNumber;

    Register_FormPresenter(Register_FormContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    /**
     * 회원가입 클릭 버튼 이벤트 처리
     */
    @Override
    public void clickRegister(EditText[] EditTextArray) {
        mRegisterEditText = EditTextArray;

        //비어있는지 확인
        if (!isEmpty()) {
            //비밀번호가 일치하는지
            if (isPasswordSame()) {
                //인증번호가 일치하는지
                if(isAuthNumberSame(mRegisterEditText[7].getText().toString())) {
                    Register_PaymentPasswordFragment mRegister_paymentPasswordFragment = new Register_PaymentPasswordFragment();

                    //2차비밀번호 설정 완료시 DB에 넘겨줄 데이터 Bundle을 SuccessFragment에 전송하기 위함.
                    Bundle bundle = new Bundle();
                    bundle.putString("userEmail", mRegisterEditText[0].getText().toString());
                    bundle.putString("userPassword", mRegisterEditText[1].getText().toString());
                    bundle.putString("userName", mRegisterEditText[3].getText().toString());
                    bundle.putString("userRN", mRegisterEditText[4].getText().toString());
                    bundle.putString("userGender", mRegisterEditText[5].getText().toString());
                    bundle.putString("userPhone", mRegisterEditText[6].getText().toString());
                    mRegister_paymentPasswordFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);


                    fragmentTransaction.replace(R.id.flFragmentContainer, mRegister_paymentPasswordFragment, Register_PaymentPasswordFragment.class.getName());
                    fragmentTransaction.addToBackStack(Register_PaymentPasswordFragment.class.getName());
                    fragmentTransaction.commit();
                } else{
                    mView.showFailDialog("실패", "인증번호가 일치하지 않습니다.");
                }
            } else {
                mView.showFailDialog("실패", "비밀번호를 확인해주세요.");
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
        String content = "[더치페이코리아] 인증번호 ["+ mAuthNumber +"] 입니다. 정확히 입력해 주시기 바랍니다.";

        if(isPhoneType(phoneNumber)) {
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
            mView.showFailDialog("실패" , "핸드폰 번호를 정확히 입력해주세요.");
        }

    }

    /**
     * EditText 비어있는지 확인
     */
    private boolean isEmpty() {
        for (EditText editText : mRegisterEditText) {
            if (editText.getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 비밀번호가 같은지 확인
     */
    private boolean isPasswordSame() {
        return mRegisterEditText[1].getText().toString().equals(mRegisterEditText[2].getText().toString());
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
    private boolean isPhoneType(String phoneNumber){
        return phoneNumber.length() >= 10 && phoneNumber.length() <= 11;
    }

    /**
     * 요청한 인증번호 값과 일치하는지 확인.
     */
    private boolean isAuthNumberSame(String authNumber){
        if(mAuthNumber !=null) {
            return mAuthNumber.equals(authNumber);
        }
        return false;
    }
}