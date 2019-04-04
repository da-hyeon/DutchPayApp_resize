package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewInfoBinding;

public class DutchpayNewInfoFragment extends BaseFragment implements DutchpayNewInfoContract.View{

    FragmentDutchpayNewInfoBinding mBinding;
    DutchpayNewInfoPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_new_info,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayNewInfoPresenter(this);
        mBinding.setPresenter(mPresenter);

        //edittext 포커스 맞춤 레이아웃 이동
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return mBinding.getRoot();
    }

}
