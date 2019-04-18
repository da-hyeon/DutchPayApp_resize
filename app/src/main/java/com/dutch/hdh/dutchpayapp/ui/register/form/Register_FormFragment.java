package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterFormBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class Register_FormFragment extends BaseFragment implements Register_FormContract.View{

    private FragmentRegisterFormBinding mBinding;
    private Register_FormContract.Presenter mPresenter;

    private EditText mRegisterEditText[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_form, container, false);


        initData();

        //다음 버튼 클릭
        mBinding.btNext.setOnClickListener(v->
                mPresenter.clickRegister(mRegisterEditText)
        );

        //인증번호 요청 버튼 클릭
        mBinding.ibAuthNumber.setOnClickListener(v->
            mPresenter.clickAuthNumber(mBinding.etPhone.getText().toString())
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mRegisterEditText = new EditText[]{
                mBinding.etEmail,
                mBinding.etPassword,
                mBinding.etPasswordCheck,
                mBinding.etName,
                mBinding.etBirth,
                mBinding.etFirstRN,
                mBinding.etPhone,
                mBinding.etCN
        };

        mPresenter = new Register_FormPresenter(this , getContext() , Objects.requireNonNull(getActivity()).getSupportFragmentManager() );
    }

    /**
     * 성공 다이얼로그 보이기
     * OK = 다이얼로그 닫기
     */
    @Override
    public void showSuccessDialog(String title, String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.SUCCESS_TYPE)
        .setTitleText(title)
        .setContentText(content)
        .setConfirmText("확인")
        .setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
        }).show();
    }
}
