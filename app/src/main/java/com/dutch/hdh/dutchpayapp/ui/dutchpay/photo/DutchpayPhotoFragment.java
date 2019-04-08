package com.dutch.hdh.dutchpayapp.ui.dutchpay.photo;

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
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayPhotoBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.ItemDecoration;

public class DutchpayPhotoFragment extends BaseFragment implements DutchpayPhotoContract.View {

    FragmentDutchpayPhotoBinding mBinding;
    DutchpayPhotoPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_photo,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayPhotoPresenter(this);

        //리스트 초기
        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setPhotoList(mPresenter.getmPhotoList());
        mBinding.rvPhotoList.setAdapter(mPresenter.getmAdapter());
        mBinding.rvPhotoList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.rvPhotoList.addItemDecoration(new ItemDecoration(22));
    }
}
