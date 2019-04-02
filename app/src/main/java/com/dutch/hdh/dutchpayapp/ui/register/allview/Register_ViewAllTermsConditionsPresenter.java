package com.dutch.hdh.dutchpayapp.ui.register.allview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

public class Register_ViewAllTermsConditionsPresenter implements Register_ViewAllTermsConditionsContract.Presenter {

    private Register_ViewAllTermsConditionsContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    private String mTermsConditionsTitles[];
    private String mTermsConditionsContents[];

    private int mTermsConditionsNumber;
    private boolean mTermsConditionsChecked[];

    public Register_ViewAllTermsConditionsPresenter(Register_ViewAllTermsConditionsContract.View mView, Context mContext, FragmentManager mFragmentManager , Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;

        //제목
        mTermsConditionsTitles = new String[]{
                "서비스 기본 약관 [필수]" ,
                "개인정보 수집 약관 [필수]" ,
                "휴대폰 인증 서비스 약관 [필수]" ,
                "전자거래 이용 약관 [필수]" ,
                "푸시 서비스 약관 [선택]" ,
                "마케팅 이용 동의 약관 [선택]"
        };

        //내용
        mTermsConditionsContents = new String[]{
                "서비스 기본 약관 [필수]" ,
                "개인정보 수집 약관 [필수]" ,
                "휴대폰 인증 서비스 약관 [필수]" ,
                "전자거래 이용 약관 [필수]" ,
                "푸시 서비스 약관 [선택]" ,
                "마케팅 이용 동의 약관 [선택]"
        };
    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null) {
            mTermsConditionsNumber = bundle.getInt("num");
            mTermsConditionsChecked = bundle.getBooleanArray("checkArray");

            mView.changeTOS(mTermsConditionsChecked[mTermsConditionsNumber]);

            mView.changeTitle(mTermsConditionsTitles[mTermsConditionsNumber]);
            mView.changeContent(mTermsConditionsContents[mTermsConditionsNumber]);
        }
    }

    @Override
    public void clickTOS(boolean state) {
            mTermsConditionsChecked[mTermsConditionsNumber] = state;
    }

    @Override
    public void clickBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putInt("num" , mTermsConditionsNumber);
        bundle.putBooleanArray("checkArray", mTermsConditionsChecked);

        Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment = Register_TermsConditionsAgreementFragment.getInstance();
        mRegister_termsConditionsAgreementFragment.setArguments(bundle);
        mFragmentManager.popBackStack();
    }
}
