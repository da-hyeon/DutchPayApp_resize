package com.dutch.hdh.dutchpayapp.ui.dutchpay.photodetail;

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
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayPhotoDetailBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.DutchpayPhotoContract;

public class DutchpayPhotoDetailFragment extends BaseFragment implements DutchpayPhotoContract.View {

    FragmentDutchpayPhotoDetailBinding mBinding;
    DutchpayPhotoDetailPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_photo_detail,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayPhotoDetailPresenter(this);

        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmPDetailList());
        mBinding.rvMemberList.setAdapter(mPresenter.getmAdapter());
        mBinding.rvMemberList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }
}
