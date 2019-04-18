package com.dutch.hdh.dutchpayapp.ui.wallet.mywallet;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentWalletBinding;

public class MyWalletFragment extends BaseFragment implements MyWalletContract.View {

    private FragmentWalletBinding mBinding;
    private MyWalletContract.Presenter mPresenter;
    private MyApplication mMyApplication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        mPresenter = new MyWalletPresenter(this, getContext(), getFragmentManager());
        initView();
        return mBinding.getRoot();
    }

    private void initView() {

        //카드 메뉴
        mBinding.llCard.setOnClickListener(v -> {
                    mPresenter.clickCard();
                    mMyApplication.getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_in);
                }
        );
        //계좌 메뉴
        mBinding.llAccount.setOnClickListener(v ->
                {
                    mPresenter.clickAccount();
                    mMyApplication.getActivity().overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_in);
                }
        );
        //주고받기 메뉴
        mBinding.llSendAndReceive.setOnClickListener(v ->
                mPresenter.clickSendReceive()
        );
        //사용내역 메뉴
        mBinding.llHistory.setOnClickListener(v ->
                {
                }
        );
        //상품권 메뉴
        mBinding.llGiftCard.setOnClickListener(v ->
                {
                }
        );

        mBinding.tvMyMoney.setText(String.format("%,d", mMyApplication.getUserInfo().getUserMoney()));

    }

    @Override
    public void initData() {

    }

    @Override
    public void showDutchMoney(int userDutchMoney) {

    }
}
