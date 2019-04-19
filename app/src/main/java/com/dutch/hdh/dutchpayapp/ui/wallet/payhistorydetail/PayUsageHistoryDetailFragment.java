package com.dutch.hdh.dutchpayapp.ui.wallet.payhistorydetail;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.databinding.FragmentUsageHistoryDetailBinding;
import com.google.gson.Gson;


public class PayUsageHistoryDetailFragment extends BaseFragment implements PayUsageHistoryDetailContract.View {

    private FragmentUsageHistoryDetailBinding mBinding;
    private PayUsageHistoryDetailContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    private PayHistoryList.PayHistoryListResult mPayHistoryListResult;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMyApplication = MyApplication.getInstance();
        if (getArguments() != null) {
            mPayHistoryListResult = new Gson().fromJson(getArguments().getString(Constants.PAY_USAGE_HISTORY), PayHistoryList.PayHistoryListResult.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_usage_history_detail, container, false);
        mPresenter = new PayUsageHistoryDetailPresenter(this, mMyApplication.getActivity(), getFragmentManager());
        initData();
        return mBinding.getRoot();
    }

    @Override
    public void initData() {
        if (mPayHistoryListResult != null) {
            mBinding.tvUsageDate.setText(mPayHistoryListResult.getProgress_Date());
            if ("단독".equals(mPayHistoryListResult.getPay_Types())) {
                mBinding.tvUsageName.setText(mPayHistoryListResult.getSingleTitle());
            } else {
                mBinding.tvUsageName.setText(mPayHistoryListResult.getDutchPay_Title());
            }
            mBinding.tvUsagePrice.setText(String.format("%,d", Integer.parseInt(mPayHistoryListResult.getPayMent_Price()))+"원");
            mBinding.ivUsagePayStatus.setImageResource(mPresenter.getPayStatus(mPayHistoryListResult.getPay_Types()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
    }
}
