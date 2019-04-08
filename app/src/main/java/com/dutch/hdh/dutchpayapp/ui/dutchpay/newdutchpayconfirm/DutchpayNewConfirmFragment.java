package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewPayBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.ItemDecoration;

public class DutchpayNewConfirmFragment extends BaseFragment implements DutchpayNewConfirmContract.View{

    FragmentDutchpayNewPayBinding mBinding;
    DutchpayNewConfirmPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_new_pay,container,false);
        mPresenter = new DutchpayNewConfirmPresenter(this);
        mBinding.setPresenter(mPresenter);
        mBinding.setFragment(this);

        //어댑터 셋팅
        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmConfirmList());
        mBinding.rvMemberlist.setAdapter(mPresenter.getmAdapter());
        mBinding.rvMemberlist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }
}

