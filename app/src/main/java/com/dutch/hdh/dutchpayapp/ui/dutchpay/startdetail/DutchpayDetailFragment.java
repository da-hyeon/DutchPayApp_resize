package com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail;

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
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayListDetailBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class DutchpayDetailFragment extends BaseFragment implements DutchpayDetailContract.View{

    FragmentDutchpayListDetailBinding mBinding;
    DutchpayDetailPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_list_detail,container,false);
        mPresenter = new DutchpayDetailPresenter(this);
        mBinding.setFragment(this);
        mBinding.setPresenter(mPresenter);

        //리스트 초기 생성
        mPresenter.listInit(getArguments());

        return mBinding.getRoot();
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmDetailList());
        mBinding.recyclerView2.setAdapter(mPresenter.getmAdapter());
        mBinding.recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    public void setDutchpayTitle(String title) {
        mBinding.tvShop.setText(title);
    }

    @Override
    public void setDutchpayCost(String cost) {
        mBinding.tvCost.setText(cost);
    }

    @Override
    public void setDutchpayMemCount(String memCount) {
        mBinding.tvMemCount.setText(memCount);
    }

    @Override
    public void setPointButtonGone() {
        mBinding.btPointSend.setVisibility(View.GONE);
    }

    /**
     * 성공 다이얼로그 보이기
     * OK = 되돌아가기
     */
    @Override
    public void showSuccessDialog(String title, String content) {

        KAlertDialog dialog = new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.SUCCESS_TYPE);

        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            mPresenter.clickSuccessDialog();
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

}
