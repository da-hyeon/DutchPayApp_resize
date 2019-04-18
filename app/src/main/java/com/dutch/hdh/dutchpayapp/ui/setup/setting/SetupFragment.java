package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSetupBinding;

public class SetupFragment extends BaseFragment implements SetupContract.View{

    FragmentSetupBinding mBinding;
    SetupPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_setup,container,false);
        mBinding.setFragment(this);
        mPresenter = new SetupPresenter(this);
        mBinding.setPresenter(mPresenter);

        return mBinding.getRoot();
    }
}