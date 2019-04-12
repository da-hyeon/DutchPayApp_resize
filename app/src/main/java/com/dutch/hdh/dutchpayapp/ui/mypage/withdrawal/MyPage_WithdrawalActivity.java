package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageWithdrawalBinding;

public class MyPage_WithdrawalActivity extends BaseActivity implements MyPage_WithdrawalContract.View {

    private ActivityMyPageWithdrawalBinding mBinding;
    private MyPage_WithdrawalContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_withdrawal);
        mBinding.setWithdrawalActivity(this);
        mPresenter = new MyPage_WithdrawalPresenter(this ,this);

        mBinding.vCancel.setOnClickListener(v->
                mPresenter.clickCancel()
        );
        initData();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {

    }

}
