package com.dutch.hdh.dutchpayapp.ui.register.form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterFormBinding;
import com.kinda.alert.KAlertDialog;

public class Register_FormFragment extends BaseFragment implements Register_FormContract.View{

    private FragmentRegisterFormBinding mBinding;
    private Register_FormContract.Presenter mPresenter;

    private EditText mRegisterEditText[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_form, container, false);

        initData();

        mBinding.btNext.setOnClickListener(v->
                mPresenter.clickRegister(mRegisterEditText)
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

        mPresenter = new Register_FormPresenter(this , getContext() , getActivity().getSupportFragmentManager() );
    }

    @Override
    public void showDialog(String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText("실패")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }
}
