package com.dutch.hdh.dutchpayapp.ui.register.success;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterSuccessBinding;


public class Register_SuccessFragment extends BaseFragment implements Register_SuccessContract.View {

    private FragmentRegisterSuccessBinding mBinding;
    private Register_SuccessContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_success, container, false);

        mPresenter = new Register_SuccessPresenter( this , getContext() , getActivity() , getFragmentManager());

        //앱시작 버튼 클릭
        mBinding.btnAppStart.setOnClickListener(v ->
            mPresenter.clickAppStart()

        );

        return mBinding.getRoot();
    }

    @Override
    public void removeAllExceptMains() {
        super.setDefaultMainStack();
    }
}
