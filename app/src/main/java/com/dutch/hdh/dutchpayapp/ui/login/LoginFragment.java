package com.dutch.hdh.dutchpayapp.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentLoginBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class LoginFragment extends BaseFragment implements LoginContract.View{

    private FragmentLoginBinding mBinding;
    private LoginContract.Presenter mPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this , getContext() ,getFragmentManager() , getActivity());

        //로그인버튼 클릭
        mBinding.btnLogin.setOnClickListener(v->
                    mPresenter.clickLogin(mBinding.editUserID.getText().toString(), mBinding.editUserPW.getText().toString())
        );

        //회원가입 버튼 클릭
        mBinding.layoutRegister.setOnClickListener(v->
            mPresenter.clickRegister()
        );

        mBinding.vFindEmail.setOnClickListener(v->
            mPresenter.clickFindEmail()
        );

        mBinding.vFindPassword.setOnClickListener(v->
            mPresenter.clickFindPassword()
        );

        return mBinding.getRoot();
    }

    /**
     * 성공 다이얼로그 보이기
     */
    @Override
    public void showSuccessDialog(String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.SUCCESS_TYPE)
                .setTitleText("로그인 성공")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    mPresenter.clickSuccessDialog();
                })
                .show();
    }

    /**
     * 실패 다이얼로그 보이기
     */
    @Override
    public void showFailDialog(String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText("실패")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }

    @Override
    public void removeAllExceptMains(){
        super.setDefaultMainStack();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.editUserID.setText("");
        mBinding.editUserPW.setText("");
    }
}