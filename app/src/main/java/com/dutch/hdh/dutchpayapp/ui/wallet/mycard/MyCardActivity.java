package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardInfoBinding;

public class MyCardActivity extends BaseActivity implements MyCardContract.View {

    private ActivityCardInfoBinding mBinding;
    private MyCardContract.Presenter mPresenter;
    private MyApplication mMyApplication;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_info);
        mPresenter = new MyCardPresenter(this, this);
        mMyApplication = MyApplication.getInstance();
        initData();

        //취소
        mBinding.tvCancel.setOnClickListener(v -> {
                    mPresenter.clickCancel();
                }
        );
        //카드등록
        mBinding.llCardAdd.setOnClickListener(v -> {
                    mPresenter.clickCardAdd();
                }
        );
        mBinding.ivCardRegistrationManagement.setOnClickListener(v -> {
                    mPresenter.clickCardManagement();
                }
        );
        //카드 목록 버튼
        mBinding.ivLeftArrow.setOnClickListener(v ->
                mPresenter.clickLeft(mBinding.vpCardList, mBinding.tlIndicator)
        );
        mBinding.ivRightArrow.setOnClickListener(v ->
                mPresenter.clickRight(mBinding.vpCardList, mBinding.tlIndicator)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {
        mPresenter.setAdapter(mBinding.vpCardList, mBinding.tlIndicator);
    }
}
