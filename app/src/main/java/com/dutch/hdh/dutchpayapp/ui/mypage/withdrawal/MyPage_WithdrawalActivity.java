package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageWithdrawalBinding;
import com.kinda.alert.KAlertDialog;

public class MyPage_WithdrawalActivity extends BaseActivity implements MyPage_WithdrawalContract.View {

    private ActivityMyPageWithdrawalBinding mBinding;
    private MyPage_WithdrawalContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_withdrawal);
        mBinding.setWithdrawalActivity(this);
        mPresenter = new MyPage_WithdrawalPresenter(this ,this , this);

        initData();

        //취소 버튼 클릭
        mBinding.vCancel.setOnClickListener(v->
                mPresenter.clickCancel()
        );

        //환불계좌변경 View 클릭
        mBinding.vBankBackground.setOnClickListener(v->
            mPresenter.clickChangeRefundAccount()
        );

        //환불계좌변경 버튼 클릭
        mBinding.btChangeRefundAccount.setOnClickListener(v->
                mPresenter.clickChangeRefundAccount()
        );

        mBinding.btWihdrawal.setOnClickListener(v->
            mPresenter.clickWithdrawal()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
            mPresenter.initView(getIntent());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void changeRefundAmount(int amount) {
        mBinding.tvRefundAmount.setText(String.format("%,d" , amount) + "원");
    }

    /**
     * 대표계좌 배경컬러 변경하기
     */
    @Override
    public void changeBankBackground(@DrawableRes int id) {
        mBinding.vBankBackground.setBackgroundResource(id);
    }

    /**
     * 대표계좌 배경이미지 변경하기
     */
    @Override
    public void changeBankImage(@DrawableRes int id) {
        mBinding.ivBank.setImageResource(id);
    }

    /**
     * 대표계좌번호 변경하기
     */
    @Override
    public void changeAccountNumber(String accountNumber) {
        mBinding.tvAccountNumber.setText(accountNumber);
    }

    /**
     * 성공 다이얼로그 보이기
     * OK = 되돌아가기
     */
    @Override
    public void showSuccessDialog(String title, String content) {

        KAlertDialog dialog = new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE);

        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            mPresenter.clickOKSuccessDialog();
        });
        dialog.show();
    }


    /**
     * 경고 다이얼로그 보이기
     * OK = 되돌아가기
     * Cancel = 다이얼로그 감추기
     */
    @Override
    public void showWarningDialog(String title, String content) {

        KAlertDialog dialog = new KAlertDialog(this, KAlertDialog.WARNING_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            mPresenter.clickOKFailDialog();
        });
        dialog.setCancelText("취소");
        dialog.setCancelClickListener(KAlertDialog::dismissWithAnimation);
        dialog.show();
    }
}
