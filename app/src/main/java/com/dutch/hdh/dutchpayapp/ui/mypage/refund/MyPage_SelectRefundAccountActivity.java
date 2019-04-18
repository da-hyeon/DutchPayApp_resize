package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageSelectRefundAccountBinding;

public class MyPage_SelectRefundAccountActivity extends BaseActivity implements MyPage_SelectRefundAccountContract.View{

    private MyPage_SelectRefundAccountContract.Presenter mPresenter;
    private ActivityMyPageSelectRefundAccountBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this ,R.layout.activity_my_page_select_refund_account);
        mBinding.setRefundActivity(this);
        mPresenter = new MyPage_SelectRefundAccountPresenter(this , this);
        initData();

        //대표계좌 클릭
        mBinding.vRepresentativeAccount.setOnClickListener(v->
            mPresenter.clickRepresentativeAccount()
        );
        //뒤로가기 클릭
        mBinding.vCancel.setOnClickListener(v->
            mPresenter.clickCancel()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mPresenter.initView(mBinding.lvListView);
    }

    /**
     * 대표계좌 배경컬러 변경하기
     */
    @Override
    public void changeBankBackground(@DrawableRes int id) {
        mBinding.vRepresentativeAccount.setBackgroundResource(id);
    }

    /**
     * 대표계좌 배경이미지 변경하기
     */
    @Override
    public void changeBankImage(@DrawableRes int id) {
        mBinding.ivBank.setImageResource(id);
    }

    /**
     * 대표계좌번호 변경하기
     */
    @Override
    public void changeAccountNumber(String accountNumber) {
        mBinding.tvAccountNumber.setText(accountNumber);
    }


}
