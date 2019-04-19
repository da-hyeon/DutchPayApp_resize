package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.payment_password.PaymentPasswordFragment;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.main.PersonalPayment_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.scan.PersonalPayment_ScanContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment_InfomationDialogPresenter implements Payment_InfomationDialogContract.Presenter {

    private Payment_InfomationDialogContract.View mView;
    private PersonalPayment_ScanContract.View mScanView;
    private FragmentManager mFragmentManager;

    private Context mContext;
    private Activity mActivity;

    private MyApplication mMyApplication;

    private String mProductCode;
    private int mProductAmount;

    /**
     * 생성자
     */
    Payment_InfomationDialogPresenter(Payment_InfomationDialogContract.View mView, FragmentManager mFragmentManager ,Context mContext , String mProductCode , int mProductAmount) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mProductCode = mProductCode;
        this.mProductAmount = mProductAmount;

        mMyApplication = MyApplication.getInstance();
    }

    /**
     * scanView 받아오기
     */
    @Override
    public void setScanView(PersonalPayment_ScanContract.View mScanView) {
        this.mScanView = mScanView;
    }

    /**
     * View 세팅
     */
    @Override
    public void initVIew() {
        mView.changeMyMoney(mMyApplication.getUserInfo().getUserMoney());
        mView.changePaymentAmount(mProductAmount);

    }

    /**
     * 다이얼로그 종료 이벤트 처리
     */
    @Override
    public void onDismiss() {
        if (mScanView != null) {
            mScanView.showCamera(1001);
        }
    }

    /**
     * 확인버튼 처리
     */
    @Override
    public void clickOK() {
        mScanView = null;

        Bundle bundle = new Bundle();
        //상품코드
        bundle.putString(Constants.PRODUCT_CODE, mProductCode);
        //상품가격
        bundle.putInt(Constants.PRODUCT_AMOUNT, mProductAmount);
        //입장경로 - 개인결제
        bundle.putBoolean(Constants.ENTRANCE_PATH, true);

        //결제비밀번호 입력을 위해 해당 프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        PaymentPasswordFragment paymentPasswordFragment = new PaymentPasswordFragment();
        paymentPasswordFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, paymentPasswordFragment, PaymentPasswordFragment.class.getName());
        fragmentTransaction.addToBackStack(PaymentPasswordFragment.class.getName());
        fragmentTransaction.commit();

        //다이얼로그 닫기
        mView.hideDialog();
    }

}
