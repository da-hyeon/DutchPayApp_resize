package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

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
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewStartBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.ItemDecoration;

public class DutchpayNewFragment extends BaseFragment implements DutchpayNewContract.View {

    FragmentDutchpayNewStartBinding mBinding;
    DutchpayNewlPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_new_start,container,false);
        mPresenter = new DutchpayNewlPresenter(this);
        mBinding.setFragment(this);

        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmNewList());
        mBinding.rvMemberlist.setAdapter(mPresenter.getmAdapter());
        mBinding.rvMemberlist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.rvMemberlist.addItemDecoration(new ItemDecoration(38));
    }
}
