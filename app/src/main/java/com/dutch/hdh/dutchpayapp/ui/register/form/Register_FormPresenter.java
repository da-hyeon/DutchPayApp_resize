package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;
import com.kinda.alert.KAlertDialog;

public class Register_FormPresenter implements Register_FormContract.Presenter {

    private Register_FormContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private EditText mRegisterEditText[];

    Register_FormPresenter(Register_FormContract.View mView, Context mContext, FragmentManager mFragmentManager ) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void clickRegister(EditText[] EditTextArray) {
        mRegisterEditText = EditTextArray;

        if(!isEmpty()) {
            if(isPasswordSame()) {

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


                fragmentTransaction.replace(R.id.flFragmentContainer, mRegister_paymentPasswordFragment , Register_PaymentPasswordFragment.class.getName());
                fragmentTransaction.addToBackStack(Register_PaymentPasswordFragment.class.getName());
                fragmentTransaction.commit();
            }
            else {
                mView.showDialog("비밀번호를 확인해주세요.");
            }
        } else {
            mView.showDialog("빈칸을 채워주세요.");
        }

    }

    //비어있는지 확인
    private boolean isEmpty(){
        for( EditText editText : mRegisterEditText){
            if( editText.getText().toString().equals("")){
                return true;
            }
        }
        return false;
    }

    //비밀번호가 같은지 확인
    private boolean isPasswordSame(){
        if(mRegisterEditText[1].getText().toString().equals(mRegisterEditText[2].getText().toString())){
            return true;
        }
        return false;
    }
}
