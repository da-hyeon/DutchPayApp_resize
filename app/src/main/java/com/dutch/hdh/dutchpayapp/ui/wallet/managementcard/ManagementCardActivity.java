package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardManagementListAdapter;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.data.db.CardManagement;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardManagementBinding;

import java.util.ArrayList;

public class ManagementCardActivity extends BaseActivity implements ManagementCardContract.View {

    private ActivityCardManagementBinding mBinding;
    private ManagementCardContract.Presenter mPresenter;
    private CardManagementListAdapter mCardManagementListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_management);
        mPresenter = new ManagementCardPresenter(this, this, mBinding);
        initData();
    }

    @Override
    public void initData() {
        mBinding.tvCancel.setOnClickListener(v ->
                mPresenter.cancelClick()
        );

        mCardManagementListAdapter = new CardManagementListAdapter(ManagementCardActivity.this, mPresenter.getCardManagementDummyData());
        mBinding.lvCardList.setAdapter(mCardManagementListAdapter);
    }

    @Override
    public void setAdapter() {

    }

    @Override
    public void setCardManagementListRefresh(ArrayList<CardManagement.CardManagementListResult> cardManagementListResultArrayList) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
