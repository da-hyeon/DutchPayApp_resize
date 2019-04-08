package com.dutch.hdh.dutchpayapp.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentInfoBinding;
import com.dutch.hdh.dutchpayapp.databinding.ViewInfoListBinding;

public class InfoFragment extends BaseFragment {

    FragmentInfoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),R.layout.fragment_info,container,false);
        mBinding.setFragment(this);

        mBinding.viList2.tvTitle.setText("개인결제 진행방법");
        mBinding.viList2.ivIcon.setImageResource(R.drawable.info_icon2);

        mBinding.viList3.tvTitle.setText("주고받기 진행방법");
        mBinding.viList3.ivIcon.setImageResource(R.drawable.info_icon3);

        mBinding.viList4.tvTitle.setText("더치머니 충전방법");
        mBinding.viList4.ivIcon.setImageResource(R.drawable.info_icon4);

        return mBinding.getRoot();
    }
}
