package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment implements MainFragmentContract.View{

    private FragmentMainBinding mBinding;
    private MainFragmentContract.Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Trace.e("onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mPresenter = new MainFragmentPresenter(this, getContext() , getFragmentManager() , getActivity());

        initData();

        //개인결제 시작하기 버튼 클릭
        mBinding.ivSoloPay.setOnClickListener(v->{
            mPresenter.clickPayment();
        });

        //더치페이 시작하기 버튼 클릭
        mBinding.ivDutchPay.setOnClickListener(v->{

        });

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        //뷰페이저에 어댑터 연결요청 + TabLayer 붙이기
        mPresenter.setAdapter(mBinding.vpMainVP, mBinding.tabMainTL);
        mPresenter.initLoginState();
    }

    @Override
    public void showUserInfo(String userName, int userDutchMoney, boolean state) {
        if (state) {
            mBinding.layoutSuccessLogin.setVisibility(View.VISIBLE);
            mBinding.layoutNoneLogin.setVisibility(View.GONE);

            mBinding.txtUserName.setText(userName + "님, 안녕하세요!");
            mBinding.txtUserDutchMoney.setText(String.format("%,d", userDutchMoney) + " ");
        } else {
            mBinding.layoutSuccessLogin.setVisibility(View.GONE);
             mBinding.layoutNoneLogin.setVisibility(View.VISIBLE);
        }
    }
}
