package com.dutch.hdh.dutchpayapp.ui.customer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentCutsomerBinding;

public class CustomerFragment extends BaseFragment {

    FragmentCutsomerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_cutsomer,container,false);
        mBinding.setFragment(this);

        //뷰 셋팅
        mBinding.viFAQlist2.tvTitle.setText("더치머니를 현금으로 전환할 수 있나요?");

        mBinding.viFAQlist3.tvTitle.setText("결제 제한 시간이 있나요?");

        mBinding.viFAQlist4.tvTitle.setText("고객센터 이용가능 시간이 어떻게 되나요?");

        return mBinding.getRoot();
    }
}
