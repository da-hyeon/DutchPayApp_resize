package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;


import android.content.Context;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardRegisterListAdapter;
import com.dutch.hdh.dutchpayapp.adapter.PayHistoryListAdapter;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;

import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.databinding.FragmentUsageHistoryBinding;

import java.util.ArrayList;


public class PayUsageHistoryFragment extends BaseFragment implements PayUsageHistoryContract.View {

    private FragmentUsageHistoryBinding mBinding;
    private PayUsageHistoryContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    private ArrayList<PayHistoryList.PayHistoryListResult> mPayHistoryListResultArrayList;
    private String buttonType;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_usage_history, container, false);
        mPresenter = new PayUsageHistoryPresenter(this, mMyApplication.getActivity(), getFragmentManager());
        initData();
        return mBinding.getRoot();
    }

    @Override
    public void initData() {

        //전체보기
        mBinding.ivPayAllStatus.setOnClickListener(v ->
                mPresenter.getPayUsageHistoryList(buttonType, mMyApplication.getUserInfo().getUserCode(), "0")
        );
        //더치페이 항목만 보기
        mBinding.ivPayDutchPayStatus.setOnClickListener(v ->
                mPresenter.getPayUsageHistoryList(buttonType, mMyApplication.getUserInfo().getUserCode(), "더치페이")
        );
        //개인결제 항목만 보기
        mBinding.ivPaySoloPayStatus.setOnClickListener(v ->
                mPresenter.getPayUsageHistoryList(buttonType, mMyApplication.getUserInfo().getUserCode(), "단독")
        );
        //주기/받기 항목만 보기
        mBinding.ivPaySendReceiveStatus.setOnClickListener(v -> {
                }
//                mPresenter.clickPaySendReceiveStatus(mPayHistoryListResultArrayList)
        );

        //1주
        mBinding.llWeek.setOnClickListener(v -> {
                    buttonType = "2";
                    mPresenter.getPayUsageHistoryList("2", mMyApplication.getUserInfo().getUserCode(), "0");
                    mPresenter.checkDateSortButton(mBinding.llWeek, true);
                    mPresenter.checkDateSortButton(mBinding.llOneMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llThreeMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llAllDay, false);
                }
        );
        //1개월
        mBinding.llOneMonth.setOnClickListener(v -> {
                    buttonType = "3";
                    mPresenter.getPayUsageHistoryList("3", mMyApplication.getUserInfo().getUserCode(), "0");
                    mPresenter.checkDateSortButton(mBinding.llOneMonth, true);
                    mPresenter.checkDateSortButton(mBinding.llWeek, false);
                    mPresenter.checkDateSortButton(mBinding.llThreeMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llAllDay, false);
                }
        );
        //3개월
        mBinding.llThreeMonth.setOnClickListener(v -> {
                    buttonType = "4";
                    mPresenter.getPayUsageHistoryList("4", mMyApplication.getUserInfo().getUserCode(), "0");
                    mPresenter.checkDateSortButton(mBinding.llThreeMonth, true);
                    mPresenter.checkDateSortButton(mBinding.llOneMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llWeek, false);
                    mPresenter.checkDateSortButton(mBinding.llAllDay, false);
                }
        );

        //전체
        mBinding.llAllDay.setOnClickListener(v -> {
                    buttonType ="1";
                    mPresenter.getPayUsageHistoryList("1", mMyApplication.getUserInfo().getUserCode(), "0");
                    mPresenter.checkDateSortButton(mBinding.llAllDay, true);
                    mPresenter.checkDateSortButton(mBinding.llThreeMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llOneMonth, false);
                    mPresenter.checkDateSortButton(mBinding.llWeek, false);
                }
        );

        mBinding.lvUsageHistory.setOnItemClickListener((parent, view, position, id) -> {
            PayHistoryList.PayHistoryListResult payHistoryListResult = (PayHistoryList.PayHistoryListResult) mBinding.lvUsageHistory.getAdapter().getItem(position);
            mPresenter.clickUsagePayDetail(payHistoryListResult);
        });

        mBinding.tvMyMoney.setText(String.format("%,d", mMyApplication.getUserInfo().getUserMoney()));
        //디폴트
        mBinding.llWeek.performClick();
    }

    @Override
    public void setPayUsageHistoryList(ArrayList<PayHistoryList.PayHistoryListResult> payHistoryListResultArrayList) {
        if (payHistoryListResultArrayList.size() == 0) {
            mBinding.lvUsageHistory.setVisibility(View.GONE);
            mBinding.tvCheckUsagePayList.setVisibility(View.VISIBLE);
        } else {
            mBinding.lvUsageHistory.setVisibility(View.VISIBLE);
            mBinding.tvCheckUsagePayList.setVisibility(View.GONE);
        }
        PayHistoryListAdapter payHistoryListAdapter = new PayHistoryListAdapter(mMyApplication.getActivity(), payHistoryListResultArrayList, this, mPresenter);
        mBinding.lvUsageHistory.setAdapter(payHistoryListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
    }
}
