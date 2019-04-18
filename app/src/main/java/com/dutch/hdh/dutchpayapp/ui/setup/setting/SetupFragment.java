package com.dutch.hdh.dutchpayapp.ui.setup.setting;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSetupBinding;

import static android.content.Context.MODE_PRIVATE;

public class SetupFragment extends BaseFragment implements SetupContract.View{

    FragmentSetupBinding mBinding;
    SetupPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_setup,container,false);
        mBinding.setFragment(this);
        mPresenter = new SetupPresenter(this , getActivity(), getFragmentManager());
        mBinding.setPresenter(mPresenter);

        //저장한 유저 셋팅 불러오기
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_SETTING, MODE_PRIVATE);
        boolean autologin = sharedPreferences.getBoolean(Constants.USER_AUTOLOGIN, false);

        String t = autologin ? "t" : "f";
        Log.e("autologin ->", t);
        mPresenter.autoFlag.set(autologin);

        mBinding.swAutoLogin.setOnClickListener(v -> {
            if(mPresenter.autoFlag.get()){
                mPresenter.autoOffClick();
            } else {
                mPresenter.autoOnClick();
            }
            mPresenter.autoFlag.set( !(mPresenter.autoFlag.get()));
        });

        mBinding.swPush.setOnClickListener(v -> {
            if(mPresenter.pushFlag.get()){
                mPresenter.pushOffClick();
            } else {
                mPresenter.pushOnClick();
            }
            mPresenter.pushFlag.set( !(mPresenter.pushFlag.get()));
        });

        mBinding.swMarketing.setOnClickListener(v -> {
            if(mPresenter.marketingFlag.get()){
                mPresenter.marketingOffClick();
            } else {
                mPresenter.marketingOnClick();
            }
            mPresenter.marketingFlag.set( !(mPresenter.marketingFlag.get()));
        });


        return mBinding.getRoot();
    }
}