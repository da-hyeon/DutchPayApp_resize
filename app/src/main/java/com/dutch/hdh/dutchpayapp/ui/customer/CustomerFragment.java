package com.dutch.hdh.dutchpayapp.ui.customer;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentCutsomerBinding;
import com.dutch.hdh.dutchpayapp.databinding.ViewCustomerBinding;

public class CustomerFragment extends BaseFragment implements CustomerContract.View{

    FragmentCutsomerBinding mBinding;
    CustomerPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_cutsomer,container,false);
        mBinding.setFragment(this);
        mPresenter = new CustomerPresenter(this);
        mBinding.setPresenter(mPresenter);

        //FAQ 리스트 셋팅
        mBinding.viFAQlist1.ivOpen.setOnClickListener(v -> {
            mPresenter.onListClick(1);
        });

        mBinding.viFAQlist2.tvTitle.setText("더치머니를 현금으로 전환할 수 있나요?");
        mBinding.viFAQlist2.ivOpen.setOnClickListener(v -> {
            mPresenter.onListClick(2);
        });

        mBinding.viFAQlist3.tvTitle.setText("결제 제한 시간이 있나요?");
        mBinding.viFAQlist3.ivOpen.setOnClickListener(v -> {
            mPresenter.onListClick(3);
        });

        mBinding.viFAQlist4.tvTitle.setText("고객센터 이용가능 시간이 어떻게 되나요?");
        mBinding.viFAQlist4.ivOpen.setOnClickListener(v -> {
            mPresenter.onListClick(4);
        });

        return mBinding.getRoot();
    }


    @Override
    public void listOpen(int position) {
        switch (position){
            case 1 :
                setListOpenView(mBinding.viFAQlist1);
                break;
            case 2 :
                setListOpenView(mBinding.viFAQlist2);
                break;
            case 3 :
                setListOpenView(mBinding.viFAQlist3);
                break;
            case 4 :
                setListOpenView(mBinding.viFAQlist4);
                break;
        }
    }

    @Override
    public void listClose(int position) {
        switch (position){
            case 1 :
                setListCloseView(mBinding.viFAQlist1);
                break;
            case 2 :
                setListCloseView(mBinding.viFAQlist2);
                break;
            case 3 :
                setListCloseView(mBinding.viFAQlist3);
                break;
            case 4 :
                setListCloseView(mBinding.viFAQlist4);
                break;
        }
    }

    @Override
    public void viewChange(int page) {
        if(page == 1){
            mBinding.tvFAQbt.setBackgroundColor(Color.parseColor("#ffd336"));
            mBinding.tvQuestionbt.setBackgroundColor(Color.parseColor("#cfcfcf"));

            mBinding.clFQA.setVisibility(View.VISIBLE);
            mBinding.clQuestion.setVisibility(View.GONE);
        } else {
            mBinding.tvQuestionbt.setBackgroundColor(Color.parseColor("#ffd336"));
            mBinding.tvFAQbt.setBackgroundColor(Color.parseColor("#cfcfcf"));

            mBinding.clFQA.setVisibility(View.GONE);
            mBinding.clQuestion.setVisibility(View.VISIBLE);
        }
    }

    private void setListOpenView(ViewCustomerBinding binding){
        binding.ivOpen.setImageResource(R.drawable.customer_4);
        binding.clContents.setVisibility(View.VISIBLE);
    }

    private void setListCloseView(ViewCustomerBinding binding){
        binding.ivOpen.setImageResource(R.drawable.customer_3);
        binding.clContents.setVisibility(View.GONE);
    }
}
