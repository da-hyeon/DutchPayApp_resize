package com.dutch.hdh.dutchpayapp.ui.wallet.addaccount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityAccountAddBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardAddBinding;

public class AddAccountActivity extends BaseActivity implements AddAccountContract.View {

    private ActivityAccountAddBinding mBinding;
    private AddAccountContract.Presenter mPresenter;
    private String mBankCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_add);
        mPresenter = new AddAccountPresenter(this, this, mBinding);
        initData();
    }


    @Override
    public void initData() {


        mBinding.tvCancel.setOnClickListener(v ->
                mPresenter.cancelClick()
        );
        mBinding.tvBankSelect.setOnClickListener(v ->
                mPresenter.getAccountSelectList()
        );

        mBinding.ivAccountAdd.setOnClickListener(v ->
                mPresenter.accountAddConfirm(mBankCode)
        );

//        mBinding.etAccountNum.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etAccountNum, 1));
        mBinding.etAccountPassword1.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etAccountPassword1, 1));
        mBinding.etAccountPassword2.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etAccountPassword2, 1));
        mBinding.etAccountPassword3.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etAccountPassword3, 1));
        mBinding.etAccountPassword4.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etAccountPassword4, 1));
    }

    @Override
    public void bankData(String bankName, String bankCode) {
        mBinding.tvBankSelect.setText(bankName);
        mBankCode = bankCode;

    }

    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
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
