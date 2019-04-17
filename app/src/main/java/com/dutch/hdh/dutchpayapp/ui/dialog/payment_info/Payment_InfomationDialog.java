package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Window;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.custom_dialog.BaseCustomDialog;
import com.dutch.hdh.dutchpayapp.databinding.DialogPaymentInfomationBinding;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.scan.PersonalPayment_ScanContract;
import com.kinda.alert.KAlertDialog;

public class Payment_InfomationDialog extends BaseCustomDialog implements Payment_InfomationDialogContract.View {

    private DialogPaymentInfomationBinding mBinding;
    private Payment_InfomationDialogContract.Presenter mPresenter;
    private FragmentManager mFragmentManager;

    /**
     * QR 코드를 통한 경로 생성자
     */
    public Payment_InfomationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_payment_infomation, null, false);
        setContentView(mBinding.getRoot());

        initData();

        mBinding.btOK.setOnClickListener(v ->
                mPresenter.clickOK()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mPresenter.initVIew();
    }

    /**
     * 성공 다이얼로그 보이기
     */
    @Override
    public void showSuccessDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    mFragmentManager.popBackStack();
                    sDialog.dismissWithAnimation();
                    dismiss();
                })
                .show();
    }

    /**
     * 실패 다이얼로그 보이기
     */
    @Override
    public void showFailDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    mFragmentManager.popBackStack();
                    sDialog.dismissWithAnimation();
                    dismiss();
                })
                .show();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void changePaymentAmount(int amount) {
        mBinding.tvPaymentAmount.setText(String.format("%,d", amount) + " 원");
    }

    @Override
    public void changeMyMoney(int money) {
        mBinding.tvMyDutchMoney.setText(String.format("%,d", money) + " 원");
    }


    @Override
    public void dismiss() {
        super.dismiss();
        mPresenter.onDismiss();
    }

    public void setDialog(PersonalPayment_ScanContract.View mScanView, FragmentManager mFragmentManager, String mProductCode, int mProductAmount) {
        mPresenter = new Payment_InfomationDialogPresenter(this, getContext(), mProductCode, mProductAmount);
        mPresenter.setScanView(mScanView);

        this.mFragmentManager = mFragmentManager;
    }
}
