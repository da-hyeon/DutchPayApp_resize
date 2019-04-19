package com.dutch.hdh.dutchpayapp.ui.receipt;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityReceiptBinding;

public class ReceiptActivity extends BaseActivity implements ReceiptContract.View{

    private ActivityReceiptBinding mBinding;
    private ReceiptContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_receipt);
        mBinding.setReceiptActivity(this);
        mPresenter = new ReceiptPresenter(this ,this , getIntent());

        initData();

        mBinding.vCancel.setOnClickListener(v->
            mPresenter.clickClose()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.setVIew();
    }

    /**
     * 날짜 변경하기
     */
    @Override
    public void changeDate(String date) {
        mBinding.tvDate.setText(date);
    }

    /**
     * 상점이름 변경하기
     */
    @Override
    public void changeStoreName(String name) {
        mBinding.tvStoreName.setText(name);
    }

    /**
     * 금액 변경하기
     */
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void changeAmount(int amount) {
        mBinding.tvAmount.setText(String.format("%,d" , amount)+ "원");
    }

    /**
     * 상점위치 변경하기
     */
    @Override
    public void changeLocatiion(String location) {
        mBinding.tvStoreLocation.setText(location);
    }

}
