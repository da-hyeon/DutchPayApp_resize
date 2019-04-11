package com.dutch.hdh.dutchpayapp.ui.dutchpay.start;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayStartBinding;

public class DutchpayStartFragment extends BaseFragment implements DutchpayStartContract.View{

    private FragmentDutchpayStartBinding mBinding;
    private DutchpayStartPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_start,container,false);
        mPresenter = new DutchpayStartPresenter(this);
        mBinding.setFragment(this);
        mBinding.setPresenter(mPresenter);

        //리스트 초기 생성
        mPresenter.listInit();

        //내역 필터링 버튼 설정
        mBinding.rgFiltering.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == mBinding.rbIng.getId()){
                mPresenter.onIngClick();
            } else if(checkedId == mBinding.rbComplete.getId()){
                mPresenter.onCompleteClick();
            } else {
                mPresenter.onAllClick();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setPayList(mPresenter.getmStartList());
        mBinding.recyclerView.setAdapter(mPresenter.getmAdapter());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.recyclerView.addItemDecoration(new ItemDecoration(25));
    }
}
