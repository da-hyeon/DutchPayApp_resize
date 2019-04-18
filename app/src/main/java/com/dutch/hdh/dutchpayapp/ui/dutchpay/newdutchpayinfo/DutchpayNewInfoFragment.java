package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewInfoBinding;

import java.util.ArrayList;

public class DutchpayNewInfoFragment extends BaseFragment implements DutchpayNewInfoContract.View{

    FragmentDutchpayNewInfoBinding mBinding;
    DutchpayNewInfoPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_new_info,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayNewInfoPresenter(this,getArguments());
        mBinding.setPresenter(mPresenter);

        //edittext 포커스 맞춤 레이아웃 이동
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return mBinding.getRoot();
    }

    @Override
    public void ivShopChange(boolean flag) {
        if(flag){
            mBinding.ivShop.setImageResource(R.drawable.dutchpay5_1);
        } else {
            mBinding.ivShop.setImageResource(R.drawable.dutchpay4_1);
        }
    }

    @Override
    public void ivDateChagne(boolean flag) {
        if(flag){
            mBinding.ivDate.setImageResource(R.drawable.dutchpay5_2);
        } else {
            mBinding.ivDate.setImageResource(R.drawable.dutchpay4_2);
        }
    }

    @Override
    public void ivCommentChange(boolean flag) {
        if(flag){
            mBinding.ivComment.setImageResource(R.drawable.dutchpay5_3);
        } else {
            mBinding.ivComment.setImageResource(R.drawable.dutchpay4_3);
        }
    }

    @Override
    public void setCount(int count) {
        mBinding.tvCount.setText(count+"/40자");
    }

    @Override
    public ArrayList<String> getText() {
        ArrayList<String> info = new ArrayList<>();

        info.add(mBinding.etShop.getText().toString());
        info.add(mBinding.etDate.getText().toString());
        info.add(mBinding.etComment.getText().toString());

        return info;
    }

    /**
     * 뒤로가기 처리
     */
    public void onBackPressed(){
        mPresenter.clickBackPressed();
    }
}
