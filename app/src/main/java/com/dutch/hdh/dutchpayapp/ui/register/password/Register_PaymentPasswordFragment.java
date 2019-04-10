package com.dutch.hdh.dutchpayapp.ui.register.password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterPaymentPasswordBinding;
import com.kinda.alert.KAlertDialog;

public class Register_PaymentPasswordFragment extends BaseFragment implements Register_PaymentPasswordContract.View {

    private FragmentRegisterPaymentPasswordBinding mBinding;
    private Register_PaymentPasswordContract.Presenter mPresenter;

    private TextView mNumberTextViews[];
    private ImageView mDotImage[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_payment_password, container, false);


        //객체생성 및 데이터 초기화
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
                mPresenter.clickOKButton(getArguments())
        );

        return mBinding.getRoot();
    }
    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {

        mPresenter = new Register_PaymentPasswordPresenter(this , getContext() , getFragmentManager());


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
    public void updateView() {
        mPresenter.initRandomNumber();
    }

    /**
     * 버튼 숫자 보여주기
     */
    @Override
    public void showRandomNumber(int index , String randomNumber) {
        mNumberTextViews[index].setText(randomNumber);
    }

    @Override
    public void showSuccessDialog(String content) {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setTitleText("환영합니다.")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog ->{
                    sDialog.dismissWithAnimation();
                    mPresenter.clickSuccessDialog();
                })
                .show();
    }

    @Override
    public void showFailDialog(String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText("실패")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {sDialog.dismissWithAnimation();})
                .show();
    }

    /**
     * dot Image 업데이트
     */
    @Override
    public void dotImagesUpdate(int index, boolean checkState) {
        if(checkState){
            mDotImage[index].setImageResource(R.drawable.password_on);
        } else {
            mDotImage[index].setImageResource(R.drawable.password_off);
        }
    }

    @Override
    public void changeTitle(String content) {
        mBinding.tvTitle.setText(content);
    }

    @Override
    public void changeMiddleTitle(String content) {
        mBinding.tvMiddleTitle.setText(content);
    }

    @Override
    public void removeAllExceptMains() {
        super.setDefaultMainStack();
    }
}
