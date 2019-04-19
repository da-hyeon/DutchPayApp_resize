package com.dutch.hdh.dutchpayapp.ui.payment_password;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentPaymentPasswordBinding;
import com.dutch.hdh.dutchpayapp.ui.receipt.ReceiptActivity;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;


public class PaymentPasswordFragment extends BaseFragment implements PaymentPasswordContract.View {

    private PaymentPasswordContract.Presenter mPresenter;
    private FragmentPaymentPasswordBinding mBinding;

    private TextView mNumberTextViews[];
    private ImageView mDotImage[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_password, container, false);
        mPresenter = new PaymentPasswordPresenter(this , getContext() , getActivity(), getFragmentManager() , getArguments());

        initData();

        //숫자 버튼
        for (int i = 0; i < mNumberTextViews.length; i++) {
            int finalI = i;
            mNumberTextViews[i].setOnClickListener(v->
                    mPresenter.clickNumber(mNumberTextViews[finalI].getText().toString())
            );
        }

        //삭제 버튼
        mBinding.viewDelete.setOnClickListener(v ->
                mPresenter.clickDeleteButton()
        );

        //확인 버튼
        mBinding.viewOk.setOnClickListener(v ->
                mPresenter.clickOKButton()
        );

        return mBinding.getRoot();
    }
    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        //숫자데이터
        mNumberTextViews = new TextView[]{
                mBinding.txtPw0,
                mBinding.txtPw1,
                mBinding.txtPw2,
                mBinding.txtPw3,
                mBinding.txtPw4,
                mBinding.txtPw5,
                mBinding.txtPw6,
                mBinding.txtPw7,
                mBinding.txtPw8,
                mBinding.txtPw9
        };

        // 점 표시
        mDotImage = new ImageView[]{
                mBinding.imageDot,
                mBinding.imageDot1,
                mBinding.imageDot2,
                mBinding.imageDot3,
                mBinding.imageDot4,
                mBinding.imageDot5
        };
        mPresenter.initRandomNumber();
    }

    @Override
    public void showRandomNumber(int index, String randomNumber) {
        mNumberTextViews[index].setText(randomNumber);
    }

    @Override
    public void dotImagesUpdate(int index, boolean checkState) {
        if(checkState){
            mDotImage[index].setImageResource(R.drawable.password_on);
        } else {
            mDotImage[index].setImageResource(R.drawable.password_off);
        }
    }

    /**
     * 성공 다이얼로그 보이기
     * OK = 되돌아가기
     */
    @Override
    public void showSuccessDialog(String title, String content) {

        KAlertDialog dialog = new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.SUCCESS_TYPE);

        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            mPresenter.clickSuccessDialog();
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }




}
