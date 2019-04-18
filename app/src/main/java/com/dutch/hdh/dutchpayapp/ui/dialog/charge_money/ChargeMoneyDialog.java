package com.dutch.hdh.dutchpayapp.ui.dialog.charge_money;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Window;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.DialogChargeMoneyBinding;

public class ChargeMoneyDialog extends Dialog implements ChargeMoneyDialogContract.View {

    private DialogChargeMoneyBinding mBinding;
    private ChargeMoneyDialogContract.Presenter mPresenter;

    public ChargeMoneyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_charge_money, null, false);
        setContentView(mBinding.getRoot());
        mPresenter = new ChargeMoneyDialogPresenter(this, getContext());

        mBinding.etChargeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.inputAmountText(mBinding.etChargeAmount.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.btChargeAccount.setOnClickListener(v->
                mPresenter.clickChargeAccount()
        );

        mBinding.btChargeCard.setOnClickListener(v->
                mPresenter.clickChargeCard()
        );

        mBinding.btCancel.setOnClickListener(v->
                mPresenter.clickCancel()
        );
    }

    @Override
    public void showHangleText(String content) {
        mBinding.tvHangleText.setText(content);
    }

    @Override
    public void changeAmountText(String content) {
        mBinding.etChargeAmount.setText(content);
    }

    @Override
    public void changeChargeAccountBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.btChargeAccount.setBackgroundResource(R.color.buttonSelect);
            mBinding.btChargeAccount.setTextColor(getContext().getResources().getColor(R.color.textSelect));
        } else {
            mBinding.btChargeAccount.setBackgroundResource(R.color.buttonDefault);
            mBinding.btChargeAccount.setTextColor(getContext().getResources().getColor(R.color.textDefault));
        }
    }

    @Override
    public void changeChargeCardBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.btChargeCard.setBackgroundResource(R.color.buttonSelect);
            mBinding.btChargeCard.setTextColor(getContext().getResources().getColor(R.color.textSelect));
        } else {
            mBinding.btChargeCard.setBackgroundResource(R.color.buttonDefault);
            mBinding.btChargeCard.setTextColor(getContext().getResources().getColor(R.color.textDefault));
        }
    }

    @Override
    public void changeInfoText(String content) {
        mBinding.tvInfoText.setText(content);
    }

    @Override
    public void removeDialog() {
        dismiss();
    }

}
